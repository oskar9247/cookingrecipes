package org.gombert.cooking.recipe.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;
import org.gombert.cooking.tenant.domain.model.TenantId;

import java.util.List;
import java.util.stream.Collectors;

@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Recipe extends BaseEntity
{
    @EqualsAndHashCode.Include
    private TenantId tenantId;
    @EqualsAndHashCode.Include
    private RecipeId recipeId;
    private Info info;
    private List<RecipeIngredient> ingredients;
    private List<MethodStep> method;

    Recipe(final TenantId tenantId, final RecipeId recipeId, final Info info, final List<RecipeIngredient> ingredients, final List<MethodStep> methods) throws RecipeCreationException {
        throwExecptionIfNull(tenantId, "TenantId");
        throwExecptionIfNull(recipeId, "RecipeId");
        throwExecptionIfNull(info, "Info");
        throwExecptionIfNull(ingredients, "Ingredients");
        throwExecptionIfNull(methods, "methods");

        throwExecptionOnCondition(ingredients.isEmpty(), "ingredients");
        throwExecptionOnCondition(methods.isEmpty(), "methods");

        setTenantId(tenantId);
        setRecipeId(recipeId);
        setInfo(info);
        setIngredients(ingredients);
        setMethod(methods);
    }

    public RecipeId recipeId()
    {
        return this.recipeId;
    }

    public TenantId tenantId()
    {
        return this.tenantId;
    }

    public String name()
    {
        return info.getName();
    }

    public String comment()
    {
        return info.getComment();
    }

    public String description()
    {
        return info.getDescription();
    }

    public List<String> methods() {
        return method.stream().map(t -> t.getDescription()).collect(Collectors.toList());
    }

    public List<RecipeIngredient> ingredients()
    {
        return ingredients;
    }
}
