package org.gombert.cooking.recipe.domain.port.out;

import java.util.List;

import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.tenant.domain.model.TenantId;

public interface FindRecipePort
{
    Recipe findByIdAndTenantId(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException;

    List<Recipe> findRecipesFromTenant(final RecipeId recipeId) throws RecipeNotFoundException;
}
