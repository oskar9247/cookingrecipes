package org.gombert.cooking.tenant.adapter.in.web;

import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.tenant.application.port.in.CreateTenantUseCase;
import org.gombert.cooking.tenant.application.port.in.IsTenantActiveUseCase;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotActiveException;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
