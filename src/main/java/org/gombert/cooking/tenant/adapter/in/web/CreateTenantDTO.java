package org.gombert.cooking.tenant.adapter.in.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.gombert.cooking.tenant.domain.model.TenantId;

import java.time.LocalDateTime;

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
