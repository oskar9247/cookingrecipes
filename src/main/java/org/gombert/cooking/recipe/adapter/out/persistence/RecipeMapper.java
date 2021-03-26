package org.gombert.cooking.recipe.adapter.out.persistence;

import org.gombert.cooking.recipe.domain.model.Recipe;
import org.gombert.cooking.recipe.domain.model.RecipeFactory;
import org.gombert.cooking.recipe.domain.model.RecipeIngredient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
class RecipeMapper
{
    Recipe mapFromEntity(final RecipeJPAEntity recipeJPAEntity)
    {
        return RecipeFactory.reconstitueRecipe(recipeJPAEntity);
    }

    RecipeJPAEntity mapFromDomain(final Recipe recipe)
    {
        return new RecipeJPAEntity(
                        recipe.recipeId().getId(),
                        recipe.tenantId().getId(),
                        recipe.name(),
                        recipe.description(),
                        recipe.comment(),
                        mapIngredientsFromDomain(recipe.ingredients()),
                        recipe.methods()
        );
    }

    List<RecipeIngredientJPAEntity> mapIngredientsFromDomain(final List<RecipeIngredient> recipeIngredients)
    {
        return recipeIngredients.stream().map(recipeIngredient ->
                new RecipeIngredientJPAEntity(null, recipeIngredient.ingredient(), recipeIngredient.amount(), recipeIngredient.unit())).collect(Collectors.toList());
    }
}
