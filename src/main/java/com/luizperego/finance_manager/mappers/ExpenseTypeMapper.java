package com.luizperego.finance_manager.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.luizperego.finance_manager.dtos.expensetypes.CreateExpenseTypeDto;
import com.luizperego.finance_manager.dtos.expensetypes.InfoExpenseTypeDto;
import com.luizperego.finance_manager.dtos.expensetypes.UpdateExpenseTypeDto;
import com.luizperego.finance_manager.entities.ExpenseType;

@Mapper(componentModel = "spring")
public interface ExpenseTypeMapper {

    ExpenseTypeMapper INSTANCE = Mappers.getMapper(ExpenseTypeMapper.class);

    @Mapping(target = "id", ignore = true)
    ExpenseType createEntity(CreateExpenseTypeDto dto);
    
    ExpenseType updateEntity(UpdateExpenseTypeDto dto);

    InfoExpenseTypeDto infoDto(ExpenseType entity);
}
