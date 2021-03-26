package org.gombert.cooking.recipe.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
class Info extends BaseEntity
{
    private String name;
    private String description;
    private String comment;

    Info(final String name, final String description, final String comment) throws RecipeCreationException {
        throwExecptionIfNull(name, "Recipe name");
        throwExecptionIfNull(description, "Recipe description");
        throwExecptionIfNull(comment, "Recipe comment");

        this.name = name;
        this.description = description;
        this.comment = comment;
    }
}
