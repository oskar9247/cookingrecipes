package org.gombert.cooking.application.dto;

import java.time.LocalDateTime;

import lombok.*;
import org.gombert.cooking.tenant.domain.model.TenantId;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class CreateTenantDTO
{
    private CreateTenantDTO(){}

    private TenantId clientGeneratedId;
    private String name;
    private LocalDateTime activeSince;
    private LocalDateTime activeuntil;
}
