package org.gombert.cooking.application.controller;

import org.gombert.cooking.application.dto.CreateTenantDTO;
import org.gombert.cooking.tenant.domain.port.in.CreateTenantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TenantController
{
    private final CreateTenantUseCase createTenantUseCase;

    TenantController(@Autowired final CreateTenantUseCase createTenantUseCase)
    {
        this.createTenantUseCase = createTenantUseCase;
    }

    @PostMapping(path = "/v1/cookingrecipe/tenants/")
    @ResponseStatus(value = HttpStatus.OK)
    public void createRecipe(
            @RequestBody final CreateTenantDTO createTenantDTO)
    {
        createTenantUseCase.create(CreateTenantCommandMapper.mapDtoToCommand(createTenantDTO));
    }
}
