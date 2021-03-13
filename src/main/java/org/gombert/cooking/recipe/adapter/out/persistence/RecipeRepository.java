package org.gombert.cooking.recipe.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<RecipeJPAEntity, UUID>
{
    RecipeJPAEntity findByRecipeIdAndTenantId(final UUID recipeId, final UUID tenantId);
}
