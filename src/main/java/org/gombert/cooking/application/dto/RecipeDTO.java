package org.gombert.cooking.application.dto;

import java.util.*;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class RecipeDTO
{
    private UUID recipeId;
    private UUID tenantId;
    private String name;
    private String description;
    private String comment;
    private List<RecipeIngredientDTO> recipeIngredients;
    private List<String> methodSteps;
}