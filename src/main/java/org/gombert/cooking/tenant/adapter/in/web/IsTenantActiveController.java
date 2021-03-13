package org.gombert.cooking.tenant.adapter.in.web;

import java.util.UUID;

import org.gombert.cooking.tenant.domain.model.TenantId;
import org.gombert.cooking.tenant.domain.model.exception.*;
import org.gombert.cooking.tenant.application.port.in.IsTenantActiveUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class IsTenantActiveController
{
    private final IsTenantActiveUseCase isTenantActiveUseCase;

    IsTenantActiveController(@Autowired IsTenantActiveUseCase isTenantActiveUseCase)
    {
        this.isTenantActiveUseCase = isTenantActiveUseCase;
    }

    @GetMapping(path = "/v1/cookingrecipe/tenants/{tenantId}/isTenantActive")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<TenantActiveDTO> isTenantActive(@PathVariable("tenantId") UUID tenantId)
    {
        var tenantIsActive = isTenantActiveUseCase.isTenantActive(new TenantId(tenantId));
        var tenantActiveDTO = new TenantActiveDTO(tenantIsActive);
        return ResponseEntity.status(HttpStatus.OK).body(tenantActiveDTO);
    }

    @ExceptionHandler({TenantNotFoundException.class})
    public ResponseEntity<String> handleTenantNotFoundException()
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tenant was not found");
    }

    @ExceptionHandler({TenantNotActiveException.class})
    public ResponseEntity<String> handleTenantNotActiveException(TenantNotActiveException ex)
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("tenant not active" + ex);
    }
}
