package org.gombert.cooking.recipe.domain.model;

import lombok.*;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
class Unit extends BaseEntity
{
    final private String name;

    public Unit(final String name)
    {
        throwExecptionIfNull(name, "unit");
        throwExceptionIfEmpty(name, "unit");

        this.name = name;
    }
}
