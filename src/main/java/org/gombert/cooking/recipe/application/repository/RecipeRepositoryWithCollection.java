package org.gombert.cooking.recipe.application.repository;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.recipe.domain.model.TenantId;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.recipe.domain.port.out.RecipeRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Component
public class RecipeRepositoryWithCollection implements RecipeRepository
{
    final HashSet<Recipe> recipes = new HashSet<>();

    @Override
    public void create(final Recipe recipe)
    {
        recipes.add(recipe);
    }

    @Override
    public Recipe findByIdAndTenantId(final TenantId tenantId, final RecipeId recipeId) throws RecipeNotFoundException
    {
        Predicate<Recipe> equalsRecipeId = recipe -> recipe.recipeId().equals(recipeId);
        Predicate<Recipe> equalsTenantId = recipe -> recipe.tenantId().equals(tenantId);

        return recipes.stream()
                .filter(equalsRecipeId.and(equalsTenantId))
                .findAny()
                .orElseThrow(() -> new RecipeNotFoundException("RecipeId not found: " + recipeId.toString()));
    }

    @Override
    public List<Recipe> findRecipesFromTenant(RecipeId recipeId) throws RecipeNotFoundException {
        return null;
    }

//    @Override
//    public List<Recipe> findRecipesFromTenant(TenantId tenantId) throws RecipeIdNotFoundException
//    {
//        //return recipes.stream().filter(t -> t.getTenantId().equals(tenantId)).(() -> new RecipeIdNotFoundException("TenantId not found: " + tenantId.toString()));
//    }
}
