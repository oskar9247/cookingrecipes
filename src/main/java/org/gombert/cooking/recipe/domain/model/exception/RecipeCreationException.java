package org.gombert.cooking.recipe.domain.model.exception;

public class RecipeCreationException extends RuntimeException
{
    public RecipeCreationException(final String message)
    {
        super(message);
    }
}
