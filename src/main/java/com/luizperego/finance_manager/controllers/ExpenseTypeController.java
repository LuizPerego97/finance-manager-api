package com.luizperego.finance_manager.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luizperego.finance_manager.dtos.expensetypes.CreateExpenseTypeDto;
import com.luizperego.finance_manager.dtos.expensetypes.InfoExpenseTypeDto;
import com.luizperego.finance_manager.dtos.expensetypes.UpdateExpenseTypeDto;
import com.luizperego.finance_manager.services.ExpenseTypeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expense-type")
public class ExpenseTypeController {

    private final ExpenseTypeService expenseTypeService;

    public ExpenseTypeController(ExpenseTypeService expenseTypeService) {
        this.expenseTypeService = expenseTypeService;
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateExpenseTypeDto dto) {
        expenseTypeService.create(dto);
        return ResponseEntity.ok("Criado com suesso!");
    }

    @GetMapping
    public ResponseEntity<List<InfoExpenseTypeDto>> getAll() {
        return ResponseEntity.ok(expenseTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InfoExpenseTypeDto> getById(@PathVariable Long id) {
        InfoExpenseTypeDto dto = expenseTypeService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody UpdateExpenseTypeDto dto) {
    	if(id != dto.getId()) {
    		throw new RuntimeException("Os ids não conhecidem");
    	}
        expenseTypeService.update(id, dto);
        return ResponseEntity.ok("Atualização realizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        expenseTypeService.delete(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }
}
