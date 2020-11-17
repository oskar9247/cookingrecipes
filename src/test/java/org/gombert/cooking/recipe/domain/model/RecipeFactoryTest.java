package org.gombert.cooking.recipe.domain.model;

import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RecipeFactoryTest
{
    final private RecipeId recipeId = new RecipeId(UUID.randomUUID());
    final private TenantId tenantId = new TenantId(UUID.randomUUID());

    @Test
    public void givenCorrectInput_whenCreatingRecipe_thenRecipeIsCreated() throws RecipeCreationException {
        // given
        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(
                recipeId,
                "Recipe1",
                "recipeDescription",
                "Comment",
                Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList()),
                Stream.of("Step1", "Step2").collect(Collectors.toList())
        );

        // when
        final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);

        // then
        Assertions.assertEquals(recipeId, recipe.recipeId());
        Assertions.assertEquals(tenantId, recipe.tenantId());
        Assertions.assertEquals("Recipe1", recipe.name());
        Assertions.assertEquals("recipeDescription", recipe.description());
        Assertions.assertEquals("Comment", recipe.comment());
        Assertions.assertEquals(Stream.of(new RecipeIngredient("Milk", 1.0, "Liter")).collect(Collectors.toList()), recipe.ingredients());
        Assertions.assertEquals(Stream.of("Step1", "Step2").collect(Collectors.toList()), recipe.methods());
    }

    @Test
    public void givenNullTenantId_whenCreatingRecipe_thenThrowException() throws RecipeCreationException {
        // given
        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(
                recipeId,
                "Recipe1",
                "recipeDescription",
                "Comment",
                Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList()),
                Stream.of("Step1", "Step2").collect(Collectors.toList())
        );

        try {
            final var recipe = RecipeFactory.createRecipe(null, createRecipeCommand);
            Assertions.fail("Exception expected");
        }
        catch (RecipeCreationException ex)
        {
            Assertions.assertEquals("TenantId: can't be null", ex.getMessage());
        }
        catch (Exception ex)
        {
            Assertions.fail("Wrong Exception" + ex.toString());
        }
    }

    @Test
    public void givenNullCommend_whenCreatingRecipe_thenThrowException() throws RecipeCreationException {
        try {
            final var recipe = RecipeFactory.createRecipe(tenantId, null);
            Assertions.fail("Exception expected");
        }
        catch (RecipeCreationException ex)
        {
            Assertions.assertEquals("createRecipeCommand is null", ex.getMessage());
        }
        catch (Exception ex)
        {
            Assertions.fail("Wrong Exception" + ex.toString());
        }
    }

    @Test
    public void givenNullRecipeId_whenCreatingRecipe_thenThrowException() throws RecipeCreationException {
        // given

        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(
                null,
                "Recipe1",
                "recipeDescription",
                "Comment",
                Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList()),
                Stream.of("Step1", "Step2").collect(Collectors.toList())
        );

        // when then
        try {
            final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);
            Assertions.fail("Exception expected");
        }
        catch (RecipeCreationException ex)
        {
            Assertions.assertEquals("RecipeId: can't be null", ex.getMessage());
        }
        catch (Exception ex)
        {
            Assertions.fail("Wrong Exception" + ex.toString());
        }
    }

    @Test
    public void givenNullRecipeName_whenCreatingRecipe_thenThrowException() throws RecipeCreationException {
        // given

        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(
                recipeId,
                null,
                "recipeDescription",
                "Comment",
                Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList()),
                Stream.of("Step1", "Step2").collect(Collectors.toList())
        );

        // when then
        try {
            final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);
            Assertions.fail("Exception expected");
        }
        catch (RecipeCreationException ex)
        {
            Assertions.assertEquals("Recipe name: can't be null", ex.getMessage());
        }
        catch (Exception ex)
        {
            Assertions.fail("Wrong Exception" + ex.toString());
        }
    }

    @Test
    public void givenNullRecipeDescription_whenCreatingRecipe_thenThrowException() throws RecipeCreationException {
        // given

        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(
                recipeId,
                "Recipe1",
                null,
                "Comment",
                Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList()),
                Stream.of("Step1", "Step2").collect(Collectors.toList())
        );

        // when then
        try {
            final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);
            Assertions.fail("Exception expected");
        }
        catch (RecipeCreationException ex)
        {
            Assertions.assertEquals("Recipe description: can't be null", ex.getMessage());
        }
        catch (Exception ex)
        {
            Assertions.fail("Wrong Exception" + ex.toString());
        }
    }

    @Test
    public void givenNullRecipeComment_whenCreatingRecipe_thenThrowException() throws RecipeCreationException {
        // given

        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(
                recipeId,
                "Recipe1",
                "recipeDescription",
                null,
                Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList()),
                Stream.of("Step1", "Step2").collect(Collectors.toList())
        );

        // when then
        try {
            final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);
            Assertions.fail("Exception expected");
        }
        catch (RecipeCreationException ex)
        {
            Assertions.assertEquals("Recipe comment: can't be null", ex.getMessage());
        }
        catch (Exception ex)
        {
            Assertions.fail("Wrong Exception" + ex.toString());
        }
    }

    @Test
    public void givenNullRecipeIngredients_whenCreatingRecipe_thenThrowException() throws RecipeCreationException {
        // given

        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(
                recipeId,
                "Recipe1",
                "recipeDescription",
                "comment",
                null,
                Stream.of("Step1", "Step2").collect(Collectors.toList())
        );

        // when then
        try {
            final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);
            Assertions.fail("Exception expected");
        }
        catch (RecipeCreationException ex)
        {
            Assertions.assertEquals("ingredients: list can't be empty", ex.getMessage());
        }
        catch (Exception ex)
        {
            Assertions.fail("Wrong Exception" + ex.toString());
        }
    }

    @Test
    public void givenNullMethods_whenCreatingRecipe_thenThrowException() throws RecipeCreationException {
        // given

        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(
                recipeId,
                "Recipe1",
                "recipeDescription",
                "comment",
                Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList()),
                null
        );

        // when then
        try {
            final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);
            Assertions.fail("Exception expected");
        }
        catch (RecipeCreationException ex)
        {
            Assertions.assertEquals("methods: list can't be empty", ex.getMessage());
        }
        catch (Exception ex)
        {
            Assertions.fail("Wrong Exception" + ex.toString());
        }
    }
}