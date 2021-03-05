package org.gombert.cooking.application.controller;

import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerExceptionHandler
{

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

}
