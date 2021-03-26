package org.gombert.cooking.recipe.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
public class RecipeIngredient extends BaseEntity
{
    private Ingredient ingredient;

    private IngredientAmount amount;

    RecipeIngredient(final String ingredient, final double amount, final String unit) throws RecipeCreationException {
        this.ingredient = new Ingredient(ingredient);
        this.amount = new IngredientAmount(amount, unit);
    }

    public String ingredient()
    {
        return ingredient.getName();
    }

    public double amount()
    {
        return amount.getAmount();
    }

    public String unit()
    {
        return amount.getUnit().getName();
    }
}
