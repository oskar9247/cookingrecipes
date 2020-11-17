package org.gombert.cooking.recipe.domain.model.exception;

public class RecipeRepositoryNotFound extends Exception
{
    public RecipeRepositoryNotFound(final String message)
    {
        super(message);
    }
}
