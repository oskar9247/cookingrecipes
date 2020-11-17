package org.gombert.cooking.recipe.application.controller;

import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler
{

    @ExceptionHandler({RecipeNotFoundException.class})
    public ResponseEntity<String> handleRecipeNotFoundException()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("recipe was not found");
    }

    @ExceptionHandler({RecipeCreationException.class})
    public ResponseEntity<String> handleRecipeCreationException(RecipeCreationException ex)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("recipe not created: " + ex);
    }

}
