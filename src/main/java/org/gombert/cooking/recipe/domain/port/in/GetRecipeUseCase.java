package org.gombert.cooking.recipe.domain.port.in;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.TenantId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;

public interface GetRecipeUseCase
{
    public Recipe getRecipe(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException;
}
