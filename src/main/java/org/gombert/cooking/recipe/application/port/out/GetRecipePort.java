package org.gombert.cooking.recipe.application.port.out;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.tenant.domain.model.TenantId;

public interface GetRecipePort
{
    Recipe findByIdAndTenantId(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException;
}
