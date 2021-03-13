package org.gombert.cooking.recipe.adapter.out.persistence;

import java.util.*;
import java.util.stream.Collectors;

import org.gombert.cooking.recipe.domain.model.*;
import org.springframework.stereotype.Component;

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
