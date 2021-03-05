package org.gombert.cooking.application.controller;

import org.gombert.cooking.application.dto.CreateTenantDTO;
import org.gombert.cooking.tenant.domain.port.in.CreateTenantUseCase;

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
