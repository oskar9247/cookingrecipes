package org.gombert.cooking.recipe.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.TenantId;

import java.util.List;
import java.util.UUID;

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