package org.gombert.cooking.tenant.domain.model.exception;

public class TenantNotFoundException extends RuntimeException
{
    public TenantNotFoundException(final String message)
    {
        super(message);
    }
}
