package org.gombert.cooking.recipe.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.gombert.cooking.recipe.application.port.out.*;
import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class RecipePersistenceAdapter implements GetRecipePort, CreateRecipePort
{
    private final RecipeRepository recipeRepository;
    private final RecipeMapper recipeMapper;

    @Override
    public Recipe findByIdAndTenantId(TenantId tenantId, RecipeId recipeId) throws RecipeNotFoundException
    {
        final var recipeJPAEntity = recipeRepository.findByRecipeIdAndTenantId(recipeId.getId(), tenantId.getId());
        return recipeMapper.mapFromEntity(recipeJPAEntity);
    }

    @Override
    public void create(Recipe recipe)
    {
        recipeRepository.save(recipeMapper.mapFromDomain(recipe));
    }
}
