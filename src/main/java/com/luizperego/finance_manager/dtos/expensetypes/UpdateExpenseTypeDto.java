package com.luizperego.finance_manager.dtos.expensetypes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateExpenseTypeDto {
	
    @NotNull(message = "O id é obrigatório.")
    private Long id;
	
    @NotBlank(message = "A descrição é obrigatória.")
    @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres.")
    private String description;

}
