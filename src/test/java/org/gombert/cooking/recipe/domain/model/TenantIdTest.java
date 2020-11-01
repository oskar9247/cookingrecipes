package org.gombert.cooking.recipe.domain.model;

import java.util.*;

import org.junit.jupiter.api.*;

class TenantIdTest
{
    @Test
    public void given2Tenants_whenBothUUIDsMatch_thenItIsTheSameTenant()
    {
        var sameUUID = UUID.randomUUID();
        var tenantId1 = new TenantId(sameUUID);
        var tenantId2 = new TenantId(sameUUID);

        Assertions.assertEquals(tenantId1, tenantId2);
        Assertions.assertEquals(tenantId1.hashCode(), tenantId2.hashCode());
    }

    @Test
    public void given2Tenants_whenBothUUIDsDiffer_thenItIsNotTheSameTenant()
    {
        var uuid1 = UUID.randomUUID();
        var uuid2 = UUID.randomUUID();
        var tenantId1 = new TenantId(uuid1);
        var tenantId2 = new TenantId(uuid2);

        Assertions.assertNotEquals(tenantId1, tenantId2);
        Assertions.assertNotEquals(tenantId1.hashCode(), tenantId2.hashCode());
    }

    @Test
    public void givenTenant_whenUUIDShouldBeNull_thenExceptionWillBeThrown()
    {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new TenantId(null));
    }

}