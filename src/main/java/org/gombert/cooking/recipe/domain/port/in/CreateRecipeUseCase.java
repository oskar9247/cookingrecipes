package org.gombert.cooking.recipe.domain.port.in;

import java.util.*;

import lombok.*;
import org.gombert.cooking.recipe.domain.model.*;

public interface CreateRecipeUseCase
{
    RecipeId createRecipe(final CreateRecipeCommand createRecipeCommand);

    @Getter
    @RequiredArgsConstructor
    class CreateRecipeCommand
    {
        @NonNull
        private UUID clientGeneratedId;
        @NonNull
        private TenantId tenantId;
        @NonNull
        private String name;
        @NonNull
        private String description;
        @NonNull
        private String comment;
        @NonNull
        private List<CreateRecipeIngredientCommand> recipeIngredients;
        @NonNull
        private List<String> methodSteps;
    }

    @Getter
    @RequiredArgsConstructor
    class CreateRecipeIngredientCommand
    {
        @NonNull
        private String ingredient;

        @NonNull
        private double amount;

        @NonNull
        private String unit;
    }
}
