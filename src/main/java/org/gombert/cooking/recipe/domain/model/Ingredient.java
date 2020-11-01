package org.gombert.cooking.recipe.domain.model;

import lombok.*;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Ingredient
{
    private String name;

    public Ingredient(final String name)
    {
        if (name == null)
            throw new IllegalArgumentException("Ingredient: name can't be null");

        if (name.isEmpty())
            throw new IllegalArgumentException("Ingredient: name can't be empty");

        setName(name);
    }
}
