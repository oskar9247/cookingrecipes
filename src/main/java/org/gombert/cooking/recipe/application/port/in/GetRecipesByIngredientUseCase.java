package org.gombert.cooking.recipe.application.port.in;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.tenant.domain.model.TenantId;

import java.util.Collection;

public interface GetRecipesByIngredientUseCase
{
    Collection<RecipeId> getRecipesByIngredient(final TenantId tenantId, String ingredient);
}
