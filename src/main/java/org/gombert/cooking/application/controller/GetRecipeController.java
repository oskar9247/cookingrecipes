package org.gombert.cooking.application.controller;

import java.util.UUID;

import org.gombert.cooking.application.dto.RecipeDTO;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.recipe.domain.port.in.GetRecipeUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GetRecipeController
{
    private final GetRecipeUseCase getRecipeUseCase;

    GetRecipeController(@Autowired final GetRecipeUseCase getRecipeUseCase)
    {
        this.getRecipeUseCase = getRecipeUseCase;
    }

    @GetMapping(path = "/v1/cookingrecipe/{tenantId}/recipes/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable("tenantId") UUID tenandId, @PathVariable("recipeId") UUID recipeId) throws RecipeNotFoundException
    {
        final var recipeDTO = GetRecipeMapper.mapModelToDTO(getRecipeUseCase.getRecipe(new TenantId(tenandId), new RecipeId(recipeId)));
        return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
    }

    @GetMapping(path = "/v1/cookingrecipe/{tenantId}/recipes/")
    public ResponseEntity<RecipeDTO> getRecipesFromTenant(@PathVariable("tenantId") UUID tenandId, @PathVariable("recipeId") UUID recipeId) throws RecipeNotFoundException
    {
        final var recipeDTO = GetRecipeMapper.mapModelToDTO(getRecipeUseCase.getRecipe(new TenantId(tenandId), new RecipeId(recipeId)));
        return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
    }

}
