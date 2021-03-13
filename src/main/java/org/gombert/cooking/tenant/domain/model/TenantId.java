package org.gombert.cooking.tenant.domain.model;

import java.util.UUID;

import lombok.*;
import org.gombert.cooking.recipe.domain.model.exception.RecipeCreationException;

@ToString(callSuper = false)
@EqualsAndHashCode()
public class TenantId
{
    @Getter(AccessLevel.PUBLIC)
    private UUID id;

    public TenantId(final String tenantId)
    {
        this(UUID.fromString(tenantId));
    }

    public TenantId(final TenantId tenantId) {
        this.id = tenantId.getId();
    }

    public TenantId(final UUID tenantId) throws RecipeCreationException {
        this.id = tenantId;
    }
}
