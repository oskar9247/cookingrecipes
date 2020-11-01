package org.gombert.cooking.recipe.domain.model;

import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@ToString(callSuper = false)
public class RecipeId
{
    @Getter
    private UUID id = UUID.randomUUID();

    RecipeId()
    {
        this.id = UUID.randomUUID();
    }
}
