package org.gombert.cooking.recipe.application.service;

import org.gombert.cooking.recipe.application.port.out.GetRecipePort;
import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.recipe.application.port.in.GetRecipeUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotActiveException;
import org.gombert.cooking.tenant.application.port.in.IsTenantActiveUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class GetRecipeService implements GetRecipeUseCase
{
    private final GetRecipePort getRecipePort;
    private final IsTenantActiveUseCase isTenantActiveUseCase;

    GetRecipeService(
            @Autowired GetRecipePort getRecipePort, @Autowired IsTenantActiveUseCase isTenantActiveUseCase)
            throws RecipeRepositoryNotFound {
        if (getRecipePort == null)
            throw new RecipeRepositoryNotFound("RecipeRepository is null");

        this.getRecipePort = getRecipePort;
        this.isTenantActiveUseCase = isTenantActiveUseCase;
    }

    @Override
    public Recipe getRecipe(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException
    {
        if (isTenantActiveUseCase.isTenantActive(tenantId))
        {
            return getRecipePort.findByIdAndTenantId(tenantId, recipeId);
        }

        throw new TenantNotActiveException("Tenant not active");
    }
}
