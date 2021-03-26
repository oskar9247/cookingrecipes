package org.gombert.cooking.tenant.application.service;

import org.gombert.cooking.tenant.application.port.in.CreateTenantUseCase;
import org.gombert.cooking.tenant.application.port.out.TenantRepository;
import org.gombert.cooking.tenant.domain.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CreateTenantService implements CreateTenantUseCase
{
    final private TenantRepository tenantRepository;

    public CreateTenantService(@Autowired TenantRepository tenantRepository)
    {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public void create(final CreateTenantCommand createTenantCommand)
    {
        var createdTenant = new Tenant(
                createTenantCommand.getClientGeneratedId(),
                createTenantCommand.getName(),
                createTenantCommand.getActiveSince(),
                createTenantCommand.getActiveuntil());
        tenantRepository.create(createdTenant);
    }
}
