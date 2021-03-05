package org.gombert.cooking.tenant.domain.port.in;

import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;

public interface IsTenantActiveUseCase
{
    public boolean isTenantActive(TenantId tenantId) throws TenantNotFoundException;
}
