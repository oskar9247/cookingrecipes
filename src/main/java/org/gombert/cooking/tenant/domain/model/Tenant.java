package org.gombert.cooking.tenant.domain.model;

import java.time.LocalDateTime;

public class Tenant
{
    TenantId id;
    Status status;
    String name;

    public Tenant(TenantId id, String name, LocalDateTime activeSince, LocalDateTime activeuntil)
    {
        this.id = id;
        this.name = name;
        status = new Status(activeSince, activeuntil);
    }

    public TenantId id()
    {
        return this.id;
    }

    public boolean isActive()
    {
        return this.status.isActive();
    }

    public void deactivate()
    {
        this.status.deactivate();
    }
}
