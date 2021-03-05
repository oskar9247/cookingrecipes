package org.gombert.cooking.application.service;

import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.RecipeRepository;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.port.in.IsTenantActiveUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class CreateRecipeUseCaseAdapter implements CreateRecipeUseCase
{
    private final RecipeRepository recipeRepository;
    private final IsTenantActiveUseCase isTenantActiveUseCase;

    CreateRecipeUseCaseAdapter(
            @Autowired RecipeRepository recipeRepository,
            @Autowired IsTenantActiveUseCase isTenantActiveUseCase
    ) throws RecipeRepositoryNotFound {
        if (recipeRepository == null)
            throw new RecipeRepositoryNotFound("RecipeRepository is null");

        this.recipeRepository = recipeRepository;
        this.isTenantActiveUseCase = isTenantActiveUseCase;
    }

    @Override
    @Transactional
    public void createRecipe(final TenantId tenandId, final CreateRecipeCommand createRecipeCommand)
    {
        try
        {
            if(isTenantActiveUseCase.isTenantActive(tenandId))
            {
                final Recipe createdRecipe = RecipeFactory.createRecipe(tenandId, createRecipeCommand);

                recipeRepository.create(createdRecipe);
            }
        }
        catch (RecipeCreationException exception)
        {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
