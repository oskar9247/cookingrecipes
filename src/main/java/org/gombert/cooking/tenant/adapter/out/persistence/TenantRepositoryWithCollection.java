package org.gombert.cooking.tenant.adapter.out.persistence;

import org.gombert.cooking.tenant.application.port.out.TenantRepository;
import org.gombert.cooking.tenant.domain.model.Tenant;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public class TenantRepositoryWithCollection implements TenantRepository
{
    final HashSet<Tenant> tenants = new HashSet<>();

    @Override
    public Tenant findTenantById(TenantId tenantId) throws TenantNotFoundException
    {
        return tenants.stream()
                .filter(tenant -> tenant.id().equals(tenantId))
                .findAny()
                .orElseThrow(() -> new TenantNotFoundException("TenantId not found: " + tenantId.toString()));
    }

    @Override
    public void create(Tenant tenant)
    {
        tenants.add(tenant);
    }
}
