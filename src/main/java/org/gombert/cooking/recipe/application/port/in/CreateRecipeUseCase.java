package org.gombert.cooking.recipe.application.port.in;

import java.util.List;

import lombok.*;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.tenant.domain.model.TenantId;

public interface CreateRecipeUseCase
{
    void createRecipe(final TenantId tenandId, final CreateRecipeCommand createRecipeCommand);

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    public class CreateRecipeCommand
    {
        private RecipeId clientGeneratedId;
        private String name;
        private String description;
        private String comment;
        private List<CreateRecipeIngredientCommand> recipeIngredients;
        private List<String> methodSteps;
    }

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    class CreateRecipeIngredientCommand
    {
        private String ingredient;
        private double amount;
        private String unit;
    }
}
