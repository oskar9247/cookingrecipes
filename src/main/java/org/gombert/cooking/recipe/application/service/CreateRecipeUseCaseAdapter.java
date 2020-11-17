package org.gombert.cooking.recipe.application.service;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeFactory;
import org.gombert.cooking.recipe.domain.model.TenantId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.model.exception.RecipeRepositoryNotFound;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
class CreateRecipeUseCaseAdapter implements CreateRecipeUseCase
{
    private final RecipeRepository recipeRepository;

    CreateRecipeUseCaseAdapter(@Autowired RecipeRepository recipeRepository) throws RecipeRepositoryNotFound {
        if (recipeRepository == null)
            throw new RecipeRepositoryNotFound("RecipeRepository is null");

        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public void createRecipe(final TenantId tenandId, final CreateRecipeCommand createRecipeCommand)
    {
        try
        {
            final Recipe createdRecipe = RecipeFactory.createRecipe(tenandId, createRecipeCommand);

            recipeRepository.create(createdRecipe);
        }
        catch (RecipeCreationException exception)
        {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
