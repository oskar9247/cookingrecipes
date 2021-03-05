package org.gombert.cooking.application.controller;

import java.util.UUID;

import org.gombert.cooking.application.dto.CreateRecipeDTO;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class CreateRecipeController
{
    private final CreateRecipeUseCase createRecipeUseCase;

    CreateRecipeController(@Autowired final CreateRecipeUseCase createRecipeUseCase)
    {
        this.createRecipeUseCase = createRecipeUseCase;
    }

    @PostMapping(path = "/v1/cookingrecipe/{tenantId}/recipes/")
    @ResponseStatus(value = HttpStatus.OK)
    public void createRecipe(
            @PathVariable("tenantId") UUID tenandId,
            @RequestBody final CreateRecipeDTO createRecipeDTO)
    {
        createRecipeUseCase.createRecipe(new TenantId(tenandId), CreateRecipeCommandMapper.mapDtoToCommand(createRecipeDTO));
    }



}
