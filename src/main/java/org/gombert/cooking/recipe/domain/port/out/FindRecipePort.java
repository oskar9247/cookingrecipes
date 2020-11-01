package org.gombert.cooking.recipe.domain.port.out;

import java.util.List;

import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.RecipeIdNotFoundException;

public interface FindRecipePort
{
    Recipe findByIdAndTenantId(final RecipeId recipeId) throws RecipeIdNotFoundException;

    List<Recipe> findRecipesFromTenant(final RecipeId recipeId) throws RecipeIdNotFoundException;
}
