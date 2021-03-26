package org.gombert.cooking.tenant.application.port.out;

import org.gombert.cooking.tenant.domain.model.Tenant;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;

public interface FindTenantPort
{
    Tenant findTenantById(final TenantId tenantId) throws TenantNotFoundException;
}
