package org.gombert.cooking.recipe.domain.model;

import java.util.UUID;

import lombok.*;

@Getter(AccessLevel.PRIVATE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
public class TenantId
{
    @NonNull
    private UUID id;

    public TenantId(UUID id)
    {
        if (id == null)
            throw new IllegalArgumentException("TenantId: id can't be null");

        setId(id);
    }
}
