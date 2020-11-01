package org.gombert.cooking.recipe.domain.model;

import lombok.*;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
class Tips
{
    private String name;

    public Tips(String name)
    {
        if (name == null)
            throw new IllegalArgumentException("Tips: name can't be null");

        if (name.isEmpty())
            throw new IllegalArgumentException("Tips: name can't be empty");

        setName(name);
    }
}
