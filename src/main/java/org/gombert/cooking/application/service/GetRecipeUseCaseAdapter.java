package org.gombert.cooking.application.service;

import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.recipe.domain.port.in.GetRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.RecipeRepository;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetRecipeUseCaseAdapter implements GetRecipeUseCase
{
    private final RecipeRepository recipeRepository;

    GetRecipeUseCaseAdapter(@Autowired RecipeRepository recipeRepository) throws RecipeRepositoryNotFound {
        if (recipeRepository == null)
            throw new RecipeRepositoryNotFound("RecipeRepository is null");

        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe getRecipe(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException {
        return recipeRepository.findByIdAndTenantId(tenantId, recipeId);
    }
}
