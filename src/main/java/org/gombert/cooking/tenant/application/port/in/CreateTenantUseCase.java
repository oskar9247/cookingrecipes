package org.gombert.cooking.tenant.application.port.in;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.gombert.cooking.tenant.domain.model.TenantId;

import java.time.LocalDateTime;

public interface CreateTenantUseCase
{
    void create(final CreateTenantCommand createTenantCommand);

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    public class CreateTenantCommand
    {
        private TenantId clientGeneratedId;
        private String name;
        private LocalDateTime activeSince;
        private LocalDateTime activeuntil;
    }


}
