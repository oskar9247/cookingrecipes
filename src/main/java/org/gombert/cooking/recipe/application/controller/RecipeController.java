package org.gombert.cooking.recipe.application.controller;

import java.util.ArrayList;

import org.gombert.cooking.recipe.application.dto.CreateRecipeDTO;
import org.gombert.cooking.recipe.application.service.*;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController
{
    private final CreateRecipeUseCase recipeService;

    RecipeController(@Autowired final CreateRecipeUseCaseAdapter recipeService)
    {
        this.recipeService = recipeService;
    }

    @PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    public ResponseEntity<RecipeId> createRecipe(@RequestBody final CreateRecipeDTO createRecipeDTO)
    {
        final var RecipeIngredientsCommands = new ArrayList<CreateRecipeUseCase.CreateRecipeIngredientCommand>();
        for (var recipeIngredientDTO : createRecipeDTO.getRecipeIngredients())
        {
            final var RecipeIngredientsCommand = new CreateRecipeUseCase.CreateRecipeIngredientCommand(
                    recipeIngredientDTO.getIngredient(), recipeIngredientDTO.getAmount(), recipeIngredientDTO.getUnit()
                    );
            RecipeIngredientsCommands.add(RecipeIngredientsCommand);
        }

        final var createRecipeCommand =  new CreateRecipeUseCase.CreateRecipeCommand(
                createRecipeDTO.getClientGeneratedId(),
                createRecipeDTO.getTenantId(),
                createRecipeDTO.getName(),
                createRecipeDTO.getDescription(),
                createRecipeDTO.getComment(),
                RecipeIngredientsCommands,
                createRecipeDTO.getMethodSteps()

        );
        return ResponseEntity.ok(recipeService.createRecipe(createRecipeCommand));
    }

}
