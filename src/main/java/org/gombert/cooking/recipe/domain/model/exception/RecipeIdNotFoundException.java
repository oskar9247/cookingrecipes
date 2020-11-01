package org.gombert.cooking.recipe.domain.model.exception;

public class RecipeIdNotFoundException extends Exception
{
    public RecipeIdNotFoundException(final String message)
    {
        super(message);
    }
}
