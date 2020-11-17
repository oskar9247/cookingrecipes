package org.gombert.cooking.recipe.application.service;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.TenantId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.recipe.domain.model.exception.RecipeRepositoryNotFound;
import org.gombert.cooking.recipe.domain.port.in.GetRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
