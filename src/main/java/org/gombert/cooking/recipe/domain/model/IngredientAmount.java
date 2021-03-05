package org.gombert.cooking.recipe.domain.model;

import lombok.*;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
class IngredientAmount extends BaseEntity
{
    final private Double amount;
    final private Unit unit;

    IngredientAmount(final double amount, final String unit)
    {
        throwExecptionIfNull(amount, "ingredient amount");

        this.amount = amount;
        this.unit = new Unit(unit);
    }
}
