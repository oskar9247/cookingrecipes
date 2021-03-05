package org.gombert.cooking.tenant.domain.model;

import java.time.LocalDateTime;

class Status
{
    LocalDateTime activeSince;
    LocalDateTime activeUntil;

    Status(LocalDateTime activeSince, LocalDateTime activeUntil)
    {
        this.activeSince = activeSince;
        this.activeUntil = activeUntil;
    }

    boolean isActive()
    {
        return true;
    }
}
