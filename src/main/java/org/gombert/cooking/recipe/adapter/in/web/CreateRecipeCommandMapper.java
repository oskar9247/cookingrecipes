package org.gombert.cooking.recipe.adapter.in.web;

import java.util.*;
import java.util.stream.*;

import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.application.port.in.CreateRecipeUseCase;

class CreateRecipeCommandMapper
{
    static CreateRecipeUseCase.CreateRecipeCommand mapDtoToCommand(final CreateRecipeDTO createRecipeDTO)
    {
        final var recipeIngredientsCommands = notNullStreamOfCollection(createRecipeDTO.getRecipeIngredients()).map(dto -> new CreateRecipeUseCase.CreateRecipeIngredientCommand(dto.getIngredient(),dto.getAmount(), dto.getUnit())).collect(Collectors.toList());

        final var createRecipeCommand =  new CreateRecipeUseCase.CreateRecipeCommand(
                new RecipeId(createRecipeDTO.getClientGeneratedId()),
                createRecipeDTO.getName(),
                createRecipeDTO.getDescription(),
                createRecipeDTO.getComment(),
                recipeIngredientsCommands,
                createRecipeDTO.getMethodSteps()
        );

        return createRecipeCommand;
    }

    static private Stream<CreateRecipeIngredientDTO> notNullStreamOfCollection(Collection<CreateRecipeIngredientDTO> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }
}
