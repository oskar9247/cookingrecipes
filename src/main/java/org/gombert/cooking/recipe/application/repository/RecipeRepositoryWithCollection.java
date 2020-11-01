package org.gombert.cooking.recipe.application.repository;

import java.util.*;

import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.RecipeIdNotFoundException;
import org.gombert.cooking.recipe.domain.port.out.*;
import org.springframework.stereotype.Component;

@Component
public class RecipeRepositoryWithCollection implements PersistRecipePort, FindRecipePort
{
    final HashSet<Recipe> recipes = new HashSet<>();

    @Override
    public void create(final Recipe recipe)
    {
        recipes.add(recipe);
    }

    @Override
    public Recipe findByIdAndTenantId(RecipeId recipeId) throws RecipeIdNotFoundException
    {
        return recipes.stream().filter(t -> t.getRecipeId().equals(recipeId)).findAny().orElseThrow(() -> new RecipeIdNotFoundException("RecipeId not found: " + recipeId.toString()));
    }

    @Override
    public List<Recipe> findRecipesFromTenant(TenantId tenantId) throws RecipeIdNotFoundException
    {
        return recipes.stream().filter(t -> t.getTenantId().equals(tenantId)).(() -> new RecipeIdNotFoundException("TenantId not found: " + tenantId.toString()));
    }
}
