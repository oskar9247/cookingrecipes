package org.gombert.cooking.recipe.adapter.in.web;

import java.util.*;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
class CreateRecipeDTO
{
    private CreateRecipeDTO(){}

    private UUID clientGeneratedId;
    private String name;
    private String description;
    private String comment;
    private List<CreateRecipeIngredientDTO> recipeIngredients;
    private List<String> methodSteps;
}
