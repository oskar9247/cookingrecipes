package org.gombert.cooking.recipe.domain.port.out;

import org.gombert.cooking.recipe.domain.model.Recipe;

public interface PersistRecipePort
{
    void create(final Recipe recipe);
}
