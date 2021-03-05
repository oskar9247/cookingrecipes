package org.gombert.cooking.recipe.domain.model;

import java.util.*;
import java.util.stream.Collectors;

import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.junit.jupiter.api.*;

class RecipeTest
{
    final private TenantId tenantId = new TenantId(UUID.randomUUID());
    final private RecipeId recipeId = new RecipeId(UUID.randomUUID());

    @Test
    public void givenCorrectInput_whenCreatingRecipe_thenRecipeIsCreated() throws RecipeCreationException {
        final var info = new Info("Recipe1", "recipeDescription", "Comment");
        final var ingredients = new ArrayList<RecipeIngredient>();
        ingredients.add(new RecipeIngredient("Milk", 1.0, "Liter"));
        final var method = new ArrayList<MethodStep>();
        method.add(new MethodStep("Step1"));
        final var recipe = new Recipe(tenantId, recipeId, info, ingredients, method);

        Assertions.assertEquals(recipeId, recipe.recipeId());
        Assertions.assertEquals(tenantId, recipe.tenantId());
        Assertions.assertEquals("Recipe1", recipe.name());
        Assertions.assertEquals("recipeDescription", recipe.description());
        Assertions.assertEquals("Comment", recipe.comment());
        Assertions.assertEquals(ingredients, recipe.ingredients());
        Assertions.assertEquals(method.stream().map(t -> t.getDescription()).collect(Collectors.toList()), recipe.methods());
    }

    @Test
    public void givenCorrectInputWithUTF16Encoding_whenCreatingRecipe_thenRecipeIsCreated() throws RecipeCreationException {
        final var info = new Info("â€™™", "â€™", "â€™");
        final var ingredients = new ArrayList<RecipeIngredient>();
        ingredients.add(new RecipeIngredient("â€™",1.0, "â€™"));
        final var method = new ArrayList<MethodStep>();
        method.add(new MethodStep("â€™"));
        final var recipe = new Recipe(tenantId, recipeId, info, ingredients, method);

        Assertions.assertEquals(recipeId, recipe.recipeId());
        Assertions.assertEquals(tenantId, recipe.tenantId());
        Assertions.assertEquals("â€™™", recipe.name());
        Assertions.assertEquals("â€™", recipe.description());
        Assertions.assertEquals("â€™", recipe.comment());
        Assertions.assertEquals(ingredients, recipe.ingredients());
        Assertions.assertEquals(method.stream().map(t -> t.getDescription()).collect(Collectors.toList()), recipe.methods());
    }

    @Test
    public void givenNullRecipeId_whenCreatingRecipe_thenExceptionIsThrown() throws RecipeCreationException {
        //given
        final var info = new Info("Recipe1", "recipeDescription", "Comment");
        final var ingredients = new ArrayList<RecipeIngredient>();
        ingredients.add(new RecipeIngredient( "Milk", 1.0, "Liter"));
        final var method = new ArrayList<MethodStep>();
        method.add(new MethodStep("Step1"));
        // when then
        Assertions.assertThrows(RecipeCreationException.class, () -> new Recipe(tenantId, null, info, ingredients, method));
    }

    @Test
    public void givenNullTenantId_whenCreatingRecipe_thenExceptionIsThrown() throws RecipeCreationException {
        //given
        final var info = new Info("Recipe1", "recipeDescription", "Comment");
        final var ingredients = new ArrayList<RecipeIngredient>();
        ingredients.add(new RecipeIngredient( "Milk", 1.0, "Liter"));
        final var method = new ArrayList<MethodStep>();
        method.add(new MethodStep("Step1"));
        // when then
        Assertions.assertThrows(RecipeCreationException.class, () -> new Recipe(null, recipeId, info, ingredients, method));
    }

    @Test
    public void givenNullInfo_whenCreatingRecipe_thenExceptionIsThrown() throws RecipeCreationException {
        //given
        final var ingredients = new ArrayList<RecipeIngredient>();
        ingredients.add(new RecipeIngredient("Milk", 1.0, "Liter"));
        final var method = new ArrayList<MethodStep>();
        method.add(new MethodStep("Step1"));
        // when then
        Assertions.assertThrows(RecipeCreationException.class, () -> new Recipe(tenantId, recipeId, null, ingredients, method));
    }

    @Test
    public void givenNullIngredients_whenCreatingRecipe_thenExceptionIsThrown() throws RecipeCreationException {
        //given
        final var info = new Info("Recipe1", "recipeDescription", "Comment");
        final var method = new ArrayList<MethodStep>();
        method.add(new MethodStep("Step1"));
        // when then
        Assertions.assertThrows(RecipeCreationException.class, () -> new Recipe(tenantId, recipeId, info, null, method));
    }

    @Test
    public void givenEmptyIngredients_whenCreatingRecipe_thenExceptionIsThrown() throws RecipeCreationException {
        //given
        final var info = new Info("Recipe1", "recipeDescription", "Comment");
        final var ingredients = new ArrayList<RecipeIngredient>();
        final var method = new ArrayList<MethodStep>();
        method.add(new MethodStep("Step1"));
        // when then
        Assertions.assertThrows(RecipeCreationException.class, () -> new Recipe(tenantId, recipeId, info, ingredients, method));
    }

    @Test
    public void givenNullmethods_whenCreatingRecipe_thenExceptionIsThrown() throws RecipeCreationException {
        //given
        final var info = new Info("Recipe1", "recipeDescription", "Comment");
        final var ingredients = new ArrayList<RecipeIngredient>();
        ingredients.add(new RecipeIngredient("Milk", 1.0, "Liter"));
        // when then
        Assertions.assertThrows(RecipeCreationException.class, () -> new Recipe(tenantId, recipeId, info, ingredients, null));
    }

    @Test
    public void givenEmptymethods_whenCreatingRecipe_thenExceptionIsThrown() throws RecipeCreationException {
        //given
        final var info = new Info("Recipe1", "recipeDescription", "Comment");
        final var ingredients = new ArrayList<RecipeIngredient>();
        ingredients.add(new RecipeIngredient("Milk", 1.0, "Liter"));
        final var method = new ArrayList<MethodStep>();
        // when then
        Assertions.assertThrows(RecipeCreationException.class, () -> new Recipe(tenantId, recipeId, info, ingredients, method));
    }

}