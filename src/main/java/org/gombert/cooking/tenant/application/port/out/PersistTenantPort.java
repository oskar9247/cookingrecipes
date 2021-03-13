package org.gombert.cooking.tenant.application.port.out;

import org.gombert.cooking.tenant.domain.model.Tenant;

public interface PersistTenantPort
{
    void create(final Tenant tenant);
}
