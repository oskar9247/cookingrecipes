package org.gombert.cooking.recipe.adapter.in.web;

import java.util.UUID;

import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.recipe.application.port.in.GetRecipeUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class GetRecipeController
{
    private final GetRecipeUseCase getRecipeUseCase;

    GetRecipeController(@Autowired final GetRecipeUseCase getRecipeUseCase)
    {
        this.getRecipeUseCase = getRecipeUseCase;
    }

    @GetMapping(path = "/v1/cookingrecipe/{tenantId}/recipes/{recipeId}/")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable("tenantId") UUID tenantId, @PathVariable("recipeId") UUID recipeId) throws RecipeNotFoundException
    {
        final var recipeDTO = GetRecipeMapper.mapModelToDTO(getRecipeUseCase.getRecipe(new TenantId(tenantId), new RecipeId(recipeId)));
        return ResponseEntity.status(HttpStatus.OK).body(recipeDTO);
    }

    @ExceptionHandler({RecipeNotFoundException.class})
    public ResponseEntity<String> handleRecipeNotFoundException()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("recipe was not found");
    }

    @ExceptionHandler({TenantNotFoundException.class})
    public ResponseEntity<String> handleTenantNotFoundException()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tenant was not found");
    }

    @ExceptionHandler({TenantNotActiveException.class})
    public ResponseEntity<String> handleTenantNotActiveException(TenantNotActiveException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("tenant not active" + ex);
    }

}
