package org.gombert.cooking.tenant.adapter.in.web;

import java.time.LocalDateTime;

import lombok.*;
import org.gombert.cooking.tenant.domain.model.TenantId;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
class CreateTenantDTO
{
    private CreateTenantDTO(){}

    private TenantId clientGeneratedId;
    private String name;
    private LocalDateTime activeSince;
    private LocalDateTime activeuntil;
}
