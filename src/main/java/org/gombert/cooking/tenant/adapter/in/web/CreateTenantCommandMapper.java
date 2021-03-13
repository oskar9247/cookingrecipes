package org.gombert.cooking.tenant.adapter.in.web;

import org.gombert.cooking.tenant.application.port.in.CreateTenantUseCase;

class CreateTenantCommandMapper
{
    static CreateTenantUseCase.CreateTenantCommand mapDtoToCommand(final CreateTenantDTO createTenantDTO)
    {
        final var createTenantCommand =  new CreateTenantUseCase.CreateTenantCommand(
               createTenantDTO.getClientGeneratedId(),
                createTenantDTO.getName(),
                createTenantDTO.getActiveSince(),
                createTenantDTO.getActiveuntil()
        );

        return createTenantCommand;
    }
}
