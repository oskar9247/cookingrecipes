package org.gombert.cooking.recipe.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;

import java.util.UUID;

@ToString(callSuper = false)
@EqualsAndHashCode()
public class TenantId extends BaseEntity
{
    @Getter(AccessLevel.PUBLIC)
    private UUID id;

    TenantId(final String tenantId)
    {
        throwExecptionIfNull(tenantId, "tenantId");
        this.id = UUID.fromString(tenantId);
    }

    public TenantId(final TenantId tenantId) throws RecipeCreationException {
        throwExecptionIfNull(tenantId, "tenantId");
        this.id = tenantId.getId();
    }

    public TenantId(final UUID tenantId) throws RecipeCreationException {
        throwExecptionIfNull(tenantId, "tenantId");
        this.id = tenantId;
    }
}
