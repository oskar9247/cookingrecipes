package org.gombert.cooking.recipe.application.port.out;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.tenant.domain.model.TenantId;

import java.util.Collection;

public interface GetRecipesByIngredientPort
{
    Collection<Recipe> getRecipesByIngredient(final TenantId tenantId, String ingredient);
}
