package org.gombert.cooking.recipe.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

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
