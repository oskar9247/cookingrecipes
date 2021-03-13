package org.gombert.cooking.tenant.adapter.in.web;

import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.tenant.application.port.in.*;
import org.gombert.cooking.tenant.domain.model.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class CreateTenantController
{
    private final CreateTenantUseCase createTenantUseCase;

    CreateTenantController(@Autowired final CreateTenantUseCase createTenantUseCase, @Autowired IsTenantActiveUseCase isTenantActiveUseCase)
    {
        this.createTenantUseCase = createTenantUseCase;
    }

    @PostMapping(path = "/v1/cookingrecipe/tenants/")
    @ResponseStatus(value = HttpStatus.OK)
    public void createTenant(
            @RequestBody final CreateTenantDTO createTenantDTO)
    {
        createTenantUseCase.create(CreateTenantCommandMapper.mapDtoToCommand(createTenantDTO));
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
