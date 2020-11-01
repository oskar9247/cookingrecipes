package org.gombert.cooking.recipe.application.service;

import java.util.*;
import java.util.stream.Collectors;

import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.gombert.cooking.recipe.domain.port.out.PersistRecipePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRecipeUseCaseAdapter implements CreateRecipeUseCase
{
    private final PersistRecipePort persistRecipePort;

    CreateRecipeUseCaseAdapter(@Autowired PersistRecipePort persistRecipePort)
    {
        this.persistRecipePort = persistRecipePort;
    }

    @Override
    @Transactional
    public RecipeId createRecipe(final CreateRecipeCommand createRecipeCommand)
    {
        final var clientGeneratedRecipeId = new RecipeId(createRecipeCommand.getClientGeneratedId());
        final var recipeInfo = new Info(createRecipeCommand.getName(), createRecipeCommand.getDescription(), createRecipeCommand.getComment());
        final var methodSteps = convertMethodStepsFromDto(createRecipeCommand.getMethodSteps());
        final var recipeIngredients = convertRecipeIngredientsFromDto(createRecipeCommand.getRecipeIngredients());

        final var recipe = new Recipe(clientGeneratedRecipeId, createRecipeCommand.getTenantId(),
                recipeInfo, recipeIngredients,
                methodSteps);

        persistRecipePort.create(recipe);

        return recipe.getRecipeId();
    }

    private List<MethodStep> convertMethodStepsFromDto(final List<String> methodSteps)
    {
        return methodSteps.stream().map(x -> new MethodStep(x)).collect(Collectors.toList());
    }

    private List<RecipeIngredient> convertRecipeIngredientsFromDto(final List<CreateRecipeIngredientCommand> createRecipeIngredientCommands)
    {
        final var recipeIngredients = new ArrayList<RecipeIngredient>();

        for (final var recipeIngredientCommand : createRecipeIngredientCommands)
        {
            final var ingredient = new Ingredient(recipeIngredientCommand.getIngredient());
            final var unit = new Unit(recipeIngredientCommand.getUnit());
            final var ingredientAmount = new IngredientAmount(recipeIngredientCommand.getAmount(), unit);
            final var recipeIngredient = new RecipeIngredient(ingredient, ingredientAmount);

            recipeIngredients.add(recipeIngredient);
        }

        return recipeIngredients;
    }
}
