package org.gombert.cooking.recipe.application.port.out;

import org.gombert.cooking.recipe.domain.model.Recipe;

public interface CreateRecipePort
{
    void create(final Recipe recipe);
}
