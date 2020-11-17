package org.gombert.cooking.recipe.domain.model;

import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;

public class BaseEntity
{
    protected void throwExecptionIfNull(final Object toBeChecked, final String memberPrefix) throws RecipeCreationException {
        if (toBeChecked == null)
            throw new RecipeCreationException(memberPrefix + ": can't be null");
    }

    protected void throwExecptionOnCondition(final boolean condition, final String memberPrefix) throws RecipeCreationException {
        if (condition)
            throw new RecipeCreationException(memberPrefix + ": list can't be empty");
    }

    protected void throwExceptionIfEmpty(final String value, final String memberPrefix)
    {
        if (value.isEmpty())
            throw new IllegalArgumentException(memberPrefix + ": can't be empty");
    }
}
