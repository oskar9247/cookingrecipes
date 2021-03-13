package org.gombert.cooking.recipe.adapter.in.web;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
class CreateRecipeIngredientDTO
{
    public CreateRecipeIngredientDTO(){}

    private String ingredient;
    private Double amount;
    private String unit;
}