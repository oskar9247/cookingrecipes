package org.gombert.cooking.tenant.domain.model.exception;

public class TenantNotActiveException extends RuntimeException
{
    public TenantNotActiveException(final String message)
    {
        super(message);
    }
}
