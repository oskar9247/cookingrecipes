package org.gombert.cooking.recipe.application.service;

import org.gombert.cooking.recipe.application.repository.RecipeRepositoryWithCollection;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.TenantId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.recipe.domain.model.exception.RecipeRepositoryNotFound;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CreateRecipeUseCaseAdapterTest
{
    @Test
    public void givenCorrectCommand_whenCreatingRecipe_RecipeIsCreatedAndStoredInRepository() throws RecipeCreationException, RecipeNotFoundException, RecipeRepositoryNotFound {
        // given
        final var repository = new RecipeRepositoryWithCollection();
        final var createRecipeUseCaseAdapter = new CreateRecipeUseCaseAdapter(repository);

        final var recipeId = new RecipeId(UUID.randomUUID());
        final var tenantId = new TenantId(UUID.randomUUID());
        final var methods = Stream.of("Step1", "Step2").collect(Collectors.toList());
        final var ingredients = Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList());
        var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(recipeId, "name", "description", "comment",ingredients, methods);

        // when
        createRecipeUseCaseAdapter.createRecipe(tenantId, createRecipeCommand);

        //then
        final var recipeFromRepository = repository.findByIdAndTenantId(tenantId, recipeId);
        Assertions.assertEquals(recipeId, recipeFromRepository.recipeId());
    }

    @Test
    public void givenNullRepository_whenInstantiatingService_ThrowException() throws RecipeCreationException, RecipeNotFoundException {
        // given
        final RecipeRepository repository = null;

        // when then
        Assertions.assertThrows(RecipeRepositoryNotFound.class, () -> new CreateRecipeUseCaseAdapter(repository));
    }
}