package org.gombert.cooking.application.service;

import org.gombert.cooking.tenant.domain.model.Tenant;
import org.gombert.cooking.tenant.domain.port.in.CreateTenantUseCase;
import org.gombert.cooking.tenant.domain.port.out.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTenantUseCaseAdapter implements CreateTenantUseCase
{
    final private TenantRepository tenantRepository;

    public CreateTenantUseCaseAdapter(@Autowired TenantRepository tenantRepository)
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
