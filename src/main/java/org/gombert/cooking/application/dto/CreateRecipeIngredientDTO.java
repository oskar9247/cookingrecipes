package org.gombert.cooking.application.dto;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class CreateRecipeIngredientDTO
{
    public CreateRecipeIngredientDTO(){}

    private String ingredient;
    private Double amount;
    private String unit;
}
