package org.gombert.cooking.recipe.domain.model;

import lombok.*;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;

@Getter(AccessLevel.MODULE)
@EqualsAndHashCode
class MethodStep extends BaseEntity
{
    final private String description;

    public MethodStep(final String description) throws RecipeCreationException
    {
        throwExecptionIfNull(description, "MethodStep description");
        throwExceptionIfEmpty(description, "MethodStep description");

        this.description = description;
    }

}
