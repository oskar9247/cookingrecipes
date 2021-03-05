package org.gombert.cooking.recipe.domain.model;

import java.util.UUID;

import lombok.*;

@ToString(callSuper = false)
@EqualsAndHashCode
public class RecipeId extends BaseEntity
{
    @Getter(AccessLevel.PUBLIC)
    final private UUID id;

    public RecipeId(final UUID id)
    {
        throwExecptionIfNull(id, "recipeId");

        this.id = id;
    }
}
