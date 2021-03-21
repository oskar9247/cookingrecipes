package org.gombert.cooking.recipe.application.service;

import lombok.AllArgsConstructor;
import org.gombert.cooking.recipe.application.port.in.GetRecipesByIngredientUseCase;
import org.gombert.cooking.recipe.application.port.out.GetRecipesByIngredientPort;
import org.gombert.cooking.recipe.domain.model.RecipeId;
import org.gombert.cooking.tenant.application.port.in.IsTenantActiveUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotActiveException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetRecipesByIngredientService implements GetRecipesByIngredientUseCase
{
    private final GetRecipesByIngredientPort getRecipesByIngredientPort;
    private final IsTenantActiveUseCase isTenantActiveUseCase;

    @Override
    public Collection<RecipeId> getRecipesByIngredient(TenantId tenantId, String ingredient) {
        if (isTenantActiveUseCase.isTenantActive(tenantId))
        {
            return getRecipesByIngredientPort.getRecipesByIngredient(tenantId, ingredient)
                    .stream().map(recipe -> recipe.recipeId()).collect(Collectors.toList());
        }

        throw new TenantNotActiveException("Tenant not active");
    }
}
