package com.luizperego.finance_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.luizperego.finance_manager.dtos.expense.CreateExpenseDto;
import com.luizperego.finance_manager.dtos.expense.InfoExpenseDto;
import com.luizperego.finance_manager.dtos.expense.UpdateExpenseDto;
import com.luizperego.finance_manager.entities.Expense;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

	 ExpenseMapper INSTANCE = Mappers.getMapper(ExpenseMapper.class);

	 @Mapping(target = "id", ignore = true)
	 @Mapping(target = "type.id", source = "expenseTypeId")
	 Expense createEntity(CreateExpenseDto dto);
	    
	 @Mapping(target = "type.id", source = "expenseTypeId")
	 Expense updateEntity(UpdateExpenseDto dto);

	 @Mapping(target = "expenseTypeId", source = "type.id")
	 InfoExpenseDto infoDto(Expense entity);
}
