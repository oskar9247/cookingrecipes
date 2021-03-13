package org.gombert.cooking.recipe.application.port.out;

import java.util.List;

import lombok.*;
import org.gombert.cooking.recipe.application.port.in.CreateRecipeUseCase;
import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.tenant.domain.model.TenantId;

public interface GetRecipePort
{
    Recipe findByIdAndTenantId(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException;
}
