package org.gombert.cooking.recipe.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
class Ingredient extends BaseEntity
{
    final private String name;

    public Ingredient(final String name) throws RecipeCreationException {
        throwExecptionIfNull(name, "ingredient name");
        throwExceptionIfEmpty(name, "ingredient name");

        this.name = name;
    }
}
