package org.gombert.cooking.recipe.application.port.in;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.tenant.domain.model.TenantId;

public interface GetRecipeUseCase
{
    public Recipe getRecipe(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException;
}
