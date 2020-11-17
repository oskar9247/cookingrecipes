package org.gombert.cooking.recipe.domain.port.out;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.TenantId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;

import java.util.List;
import java.util.UUID;

public interface FindRecipePort
{
    Recipe findByIdAndTenantId(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException;

    List<Recipe> findRecipesFromTenant(final RecipeId recipeId) throws RecipeNotFoundException;
}
