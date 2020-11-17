package org.gombert.cooking.recipe.domain.model.exception;

public class RecipeNotFoundException extends Exception
{
    public RecipeNotFoundException(final String message)
    {
        super(message);
    }
}
