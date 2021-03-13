package org.gombert.cooking.tenant.application.port.in;

import java.time.LocalDateTime;

import lombok.*;
import org.gombert.cooking.tenant.domain.model.TenantId;

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
