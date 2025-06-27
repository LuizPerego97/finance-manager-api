package com.luizperego.finance_manager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizperego.finance_manager.dtos.expense.CreateExpenseDto;
import com.luizperego.finance_manager.dtos.expense.InfoExpenseDto;
import com.luizperego.finance_manager.dtos.expense.UpdateExpenseDto;
import com.luizperego.finance_manager.services.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateExpenseDto dto) {
        expenseService.create(dto);
        return ResponseEntity.ok("Criado com suesso!");
    }

    @GetMapping
    public ResponseEntity<List<InfoExpenseDto>> getAll() {
        return ResponseEntity.ok(expenseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoExpenseDto> getById(@PathVariable Long id) {
        InfoExpenseDto dto = expenseService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody UpdateExpenseDto dto) {
    	if(id != dto.getId()) {
    		throw new RuntimeException("Os ids não conhecidem");
    	}
        expenseService.update(id, dto);
        return ResponseEntity.ok("Atualização realizada com sucesso!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        expenseService.delete(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }
}
