package org.gombert.cooking.recipe.domain.model;

import java.util.*;
import java.util.stream.*;

import org.gombert.cooking.recipe.adapter.out.persistence.*;
import org.gombert.cooking.recipe.application.port.out.GetRecipePort;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;
import org.gombert.cooking.recipe.application.port.in.CreateRecipeUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;

public class RecipeFactory
{
    public static Recipe createRecipe(final TenantId tenantId, final CreateRecipeUseCase.CreateRecipeCommand createRecipeCommand) throws RecipeCreationException {
        if (createRecipeCommand == null)
            throw new RecipeCreationException("createRecipeCommand is null");

        final var clientGeneratedRecipeId = createRecipeCommand.getClientGeneratedId();
        final var recipeInfo = new Info(createRecipeCommand.getName(), createRecipeCommand.getDescription(), createRecipeCommand.getComment());
        final var methodSteps = convertMethodStepsFromDto(createRecipeCommand.getMethodSteps());
        final var recipeIngredients = convertRecipeIngredientsFromDtoOrEmptyList(createRecipeCommand.getRecipeIngredients());

        final var recipe = new Recipe(tenantId, clientGeneratedRecipeId, recipeInfo, recipeIngredients, methodSteps);
        return recipe;
    }

    public static Recipe reconstitueRecipe(final RecipeJPAEntity recipeJPAEntity) throws RecipeCreationException {
        if (recipeJPAEntity == null)
            throw new RecipeCreationException("createRecipeCommand is null");

        final var recipeInfo = new Info(recipeJPAEntity.getName(), recipeJPAEntity.getDescription(), recipeJPAEntity.getComment());
        final var methodSteps = convertMethodStepsFromDto(recipeJPAEntity.getMethods());
        final var recipeIngredients = convertRecipeIngredientsFromRepositoryOrEmptyList(recipeJPAEntity.getRecipeIngredients());

        final var recipe = new Recipe(new TenantId(recipeJPAEntity.getTenantId()), new RecipeId(recipeJPAEntity.getRecipeId()), recipeInfo, recipeIngredients, methodSteps);
        return recipe;
    }

    private static List<MethodStep> convertMethodStepsFromDto(final List<String> methodSteps)
    {
        return notNullStreamOfCollection(methodSteps).map(x -> new MethodStep(x)).collect(Collectors.toList());
    }

    static private Stream<String> notNullStreamOfCollection(Collection<String> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }

    private static List<RecipeIngredient> convertRecipeIngredientsFromDtoOrEmptyList(final List<CreateRecipeUseCase.CreateRecipeIngredientCommand> createRecipeIngredientCommands) throws RecipeCreationException
    {
        final var recipeIngredients = new ArrayList<RecipeIngredient>();
        if (createRecipeIngredientCommands == null)
            return recipeIngredients;

        for (final var recipeIngredientCommand : createRecipeIngredientCommands)
        {
            final var recipeIngredient = new RecipeIngredient(recipeIngredientCommand.getIngredient(), recipeIngredientCommand.getAmount(), recipeIngredientCommand.getUnit());
            recipeIngredients.add(recipeIngredient);
        }
        return recipeIngredients;
    }

    private static List<RecipeIngredient> convertRecipeIngredientsFromRepositoryOrEmptyList(final List<RecipeIngredientJPAEntity> recipeIngredientJPAEntities) throws RecipeCreationException
    {
        final var reconstitutedRecipeIngredients = new ArrayList<RecipeIngredient>();
        if (recipeIngredientJPAEntities == null)
            return reconstitutedRecipeIngredients;

        for (final var recipeIngredient : recipeIngredientJPAEntities)
        {
            final var reconstitutedRecipeIngredient = new RecipeIngredient(recipeIngredient.getName(), recipeIngredient.getAmount(), recipeIngredient.getUnit());
            reconstitutedRecipeIngredients.add(reconstitutedRecipeIngredient);
        }
        return reconstitutedRecipeIngredients;
    }
}
