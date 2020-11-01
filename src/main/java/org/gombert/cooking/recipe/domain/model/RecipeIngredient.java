package org.gombert.cooking.recipe.domain.model;

import lombok.*;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@RequiredArgsConstructor
public class RecipeIngredient
{
    @NonNull
    private Ingredient ingredient;

    @NonNull
    private IngredientAmount amount;

}
