package org.gombert.cooking.recipe.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
class Tips extends BaseEntity
{
    @NonNull
    @Getter(AccessLevel.MODULE)
    final private String name;

    public Tips(final String name)
    {
        throwExecptionIfNull(name, "tips");
        throwExceptionIfEmpty(name, "tips");

        this.name = name;
    }
}
