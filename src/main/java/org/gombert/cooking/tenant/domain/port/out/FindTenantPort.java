package org.gombert.cooking.tenant.domain.port.out;

import org.gombert.cooking.tenant.domain.model.*;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;

public interface FindTenantPort
{
    Tenant findTenantById(final TenantId tenantId) throws TenantNotFoundException;
}
