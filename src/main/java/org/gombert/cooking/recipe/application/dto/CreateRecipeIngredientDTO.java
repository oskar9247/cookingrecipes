package org.gombert.cooking.recipe.application.dto;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class CreateRecipeIngredientDTO
{
    @NonNull
    private String ingredient;

    @NonNull
    private Double amount;

    @NonNull
    private String unit;
}
