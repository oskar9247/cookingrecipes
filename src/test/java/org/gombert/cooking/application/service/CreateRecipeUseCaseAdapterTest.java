package org.gombert.cooking.application.service;

import java.util.UUID;
import java.util.stream.*;

import org.gombert.cooking.application.repository.RecipeRepositoryWithCollection;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.RecipeRepository;
import org.gombert.cooking.application.repository.TenantRepositoryWithCollection;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.junit.jupiter.api.*;

class CreateRecipeUseCaseAdapterTest
{
    @Test
    public void givenCorrectCommand_whenCreatingRecipe_RecipeIsCreatedAndStoredInRepository() throws RecipeCreationException, RecipeNotFoundException, RecipeRepositoryNotFound {
        // given
        final var recipeRepository = new RecipeRepositoryWithCollection();
        final var tenantRepository = new TenantRepositoryWithCollection();
        final var isTenantActive = new IsTenantActiveUseCaseAdapter(tenantRepository);
        final var createRecipeUseCaseAdapter = new CreateRecipeUseCaseAdapter(recipeRepository, isTenantActive);

        final var recipeId = new RecipeId(UUID.randomUUID());
        final var tenantId = new TenantId(UUID.randomUUID());
        final var methods = Stream.of("Step1", "Step2").collect(Collectors.toList());
        final var ingredients = Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList());
        var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(recipeId, "name", "description", "comment",ingredients, methods);

        // when
        createRecipeUseCaseAdapter.createRecipe(tenantId, createRecipeCommand);

        //then
        final var recipeFromRepository = recipeRepository.findByIdAndTenantId(tenantId, recipeId);
        Assertions.assertEquals(recipeId, recipeFromRepository.recipeId());
    }

    @Test
    public void givenNullRepository_whenInstantiatingService_ThrowException() throws RecipeCreationException, RecipeNotFoundException {
        // given
        final RecipeRepository recipeRepository = null;

        // when then
        Assertions.assertThrows(RecipeRepositoryNotFound.class, () -> new CreateRecipeUseCaseAdapter(recipeRepository, null));
    }
}