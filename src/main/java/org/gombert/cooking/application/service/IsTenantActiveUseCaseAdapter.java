package org.gombert.cooking.application.service;

import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.TenantNotFoundException;
import org.gombert.cooking.tenant.domain.port.in.IsTenantActiveUseCase;
import org.gombert.cooking.tenant.domain.port.out.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IsTenantActiveUseCaseAdapter implements IsTenantActiveUseCase
{
    final private TenantRepository tenantRepository;

    public IsTenantActiveUseCaseAdapter(@Autowired TenantRepository tenantRepository)
    {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public boolean isTenantActive(TenantId tenantId) throws TenantNotFoundException
    {
        var tenant = tenantRepository.findTenantById(tenantId);
        return tenant.isActive();
    }
}
