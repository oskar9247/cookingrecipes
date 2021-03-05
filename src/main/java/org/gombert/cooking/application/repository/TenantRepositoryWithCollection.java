package org.gombert.cooking.application.repository;

import java.util.HashSet;

import org.gombert.cooking.tenant.domain.model.*;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;
import org.gombert.cooking.tenant.domain.port.out.TenantRepository;
import org.springframework.stereotype.Repository;

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
