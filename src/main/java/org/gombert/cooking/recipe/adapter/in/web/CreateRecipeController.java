package org.gombert.cooking.recipe.adapter.in.web;

import java.util.UUID;

import lombok.AllArgsConstructor;
import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.recipe.application.port.in.CreateRecipeUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
class CreateRecipeController
{
    private final CreateRecipeUseCase createRecipeUseCase;

    @PostMapping(path = "/v1/cookingrecipe/{tenantId}/recipes/")
    @ResponseStatus(value = HttpStatus.OK)
    public void createRecipe(
            @PathVariable("tenantId") UUID tenandId,
            @RequestBody final CreateRecipeDTO createRecipeDTO)
    {
        createRecipeUseCase.createRecipe(new TenantId(tenandId), CreateRecipeCommandMapper.mapDtoToCommand(createRecipeDTO));
    }

    @ExceptionHandler({TenantNotFoundException.class})
    public ResponseEntity<String> handleTenantNotFoundException()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tenant was not found");
    }

    @ExceptionHandler({RecipeCreationException.class})
    public ResponseEntity<String> handleRecipeCreationException(RecipeCreationException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("recipe not created: " + ex);
    }

    @ExceptionHandler({TenantNotActiveException.class})
    public ResponseEntity<String> handleTenantNotActiveException(TenantNotActiveException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("tenant not active" + ex);
    }



}
