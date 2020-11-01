package org.gombert.cooking.recipe.domain.model;

import java.util.List;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class Recipe
{
    @NonNull
    @EqualsAndHashCode.Include
    private RecipeId recipeId;

    @NonNull
    private final TenantId tenantId;

    @NonNull
    private Info info;

    @NonNull
    private List<RecipeIngredient> recipeIngredients;

    @NonNull
    private List<MethodStep> method;

}
