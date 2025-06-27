package com.luizperego.finance_manager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.luizperego.finance_manager.dtos.expensetypes.CreateExpenseTypeDto;
import com.luizperego.finance_manager.dtos.expensetypes.InfoExpenseTypeDto;
import com.luizperego.finance_manager.dtos.expensetypes.UpdateExpenseTypeDto;
import com.luizperego.finance_manager.entities.ExpenseType;
import com.luizperego.finance_manager.mappers.ExpenseTypeMapper;
import com.luizperego.finance_manager.repositories.ExpenseTypeRepository;

@Service
public class ExpenseTypeService {

    private final ExpenseTypeRepository expenseTypeRepository;
    private final ExpenseTypeMapper expenseTypeMapper;

    public ExpenseTypeService(ExpenseTypeRepository expenseTypeRepository, ExpenseTypeMapper expenseTypeMapper) {
        this.expenseTypeRepository = expenseTypeRepository;
        this.expenseTypeMapper = expenseTypeMapper;
    }

    public void create(CreateExpenseTypeDto dto) {
        ExpenseType entity = expenseTypeMapper.createEntity(dto);
        expenseTypeRepository.save(entity);
    }

    public List<InfoExpenseTypeDto> findAll() {
        return expenseTypeRepository.findAll()
            .stream()
            .map(expenseTypeMapper::infoDto)
            .toList();
    }

    public InfoExpenseTypeDto findById(Long id) {
        ExpenseType entity = expenseTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Esse tipo de gasto não existe"));
        return expenseTypeMapper.infoDto(entity);
    }

    public void update(Long id, UpdateExpenseTypeDto dto) {
        expenseTypeRepository.findById(id).map(existing -> {
            ExpenseType updated = expenseTypeMapper.updateEntity(dto);
            return expenseTypeRepository.save(updated);
        }).orElseThrow(() -> new RuntimeException("Esse tipo de gasto não existe"));
    }

    public void delete(Long id) {
        if (!expenseTypeRepository.existsById(id)) {
            throw new RuntimeException("Esse tipo de gasto não existe");
        }
        expenseTypeRepository.deleteById(id);
    }
}
