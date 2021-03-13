package org.gombert.cooking.tenant.adapter.in.web;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
class TenantActiveDTO
{
    boolean active;
}
