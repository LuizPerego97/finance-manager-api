package com.luizperego.finance_manager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luizperego.finance_manager.dtos.expense.CreateExpenseDto;
import com.luizperego.finance_manager.dtos.expense.InfoExpenseDto;
import com.luizperego.finance_manager.dtos.expense.UpdateExpenseDto;
import com.luizperego.finance_manager.entities.Expense;
import com.luizperego.finance_manager.entities.ExpenseType;
import com.luizperego.finance_manager.mappers.ExpenseMapper;
import com.luizperego.finance_manager.repositories.ExpenseRepository;
import com.luizperego.finance_manager.repositories.ExpenseTypeRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseTypeRepository expenseTypeRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseTypeRepository expenseTypeRepository, ExpenseMapper expenseMapper) {
        this.expenseRepository = expenseRepository;
        this.expenseTypeRepository = expenseTypeRepository;
        this.expenseMapper = expenseMapper;
    }

    public void create(CreateExpenseDto dto) {
        Expense entity = expenseMapper.createEntity(dto);
        ExpenseType type = expenseTypeRepository.findById(dto.getExpenseTypeId())
            .orElseThrow(() -> new RuntimeException("Tipo de gasto não encontrado"));

        entity.setType(type);
        expenseRepository.save(entity);
    }


    public List<InfoExpenseDto> findAll() {
        return expenseRepository.findAll()
                .stream()
                .map(expenseMapper::infoDto)
                .toList();
    }

    public InfoExpenseDto findById(Long id) {
        Expense entity = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum gasto registrado com esse id"));
        return expenseMapper.infoDto(entity);
    }

    public void update(Long id, UpdateExpenseDto dto) {
        expenseRepository.findById(id).map(existing -> {
            Expense updated = expenseMapper.updateEntity(dto);
            ExpenseType type = expenseTypeRepository.findById(dto.getExpenseTypeId())
                .orElseThrow(() -> new RuntimeException("Tipo de gasto não encontrado"));
            updated.setType(type);

            return expenseRepository.save(updated);
        }).orElseThrow(() -> new RuntimeException("Nenhum gasto registrado com esse id"));
    }


    public void delete(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new RuntimeException("Nenhum gasto registrado com esse id");
        }
        expenseRepository.deleteById(id);
    }
}
