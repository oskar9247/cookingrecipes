package org.gombert.cooking.tenant.domain.model;

import java.time.LocalDateTime;

class Status
{
    LocalDateTime activeSince;
    LocalDateTime activeUntil;
    boolean deactivated = false;

    Status(LocalDateTime activeSince, LocalDateTime activeUntil)
    {
        this.activeSince = activeSince;
        this.activeUntil = activeUntil;
    }

    void deactivate()
    {
       this.deactivated = true;
    }

    void activate(LocalDateTime activeUntil)
    {
        this.deactivated = false;
        this.activeUntil = activeUntil;
    }

    boolean isActive()
    {
        return  activeUntil.isAfter(LocalDateTime.now());
    }
}
