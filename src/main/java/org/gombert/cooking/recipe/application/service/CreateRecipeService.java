package org.gombert.cooking.recipe.application.service;

import lombok.AllArgsConstructor;
import org.gombert.cooking.recipe.application.port.out.CreateRecipePort;
import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.*;
import org.gombert.cooking.recipe.application.port.in.CreateRecipeUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotActiveException;
import org.gombert.cooking.tenant.application.port.in.IsTenantActiveUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class CreateRecipeService implements CreateRecipeUseCase
{
    private final CreateRecipePort createRecipePort;
    private final IsTenantActiveUseCase isTenantActiveUseCase;

    @Override
    @Transactional
    public void createRecipe(final TenantId tenantId, final CreateRecipeCommand createRecipeCommand)
    {
        try
        {
            if(isTenantActiveUseCase.isTenantActive(tenantId))
            {
                final Recipe createdRecipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);

                createRecipePort.create(createdRecipe);
            }
            else
            {
                throw new TenantNotActiveException("Tenant not active");
            }
        }
        catch (RecipeCreationException exception)
        {
            throw new IllegalArgumentException(exception.getMessage());
        }
    }
}
