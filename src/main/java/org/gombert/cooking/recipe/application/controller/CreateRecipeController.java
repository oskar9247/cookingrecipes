package org.gombert.cooking.recipe.application.controller;

import org.gombert.cooking.recipe.application.dto.CreateRecipeDTO;
import org.gombert.cooking.recipe.domain.model.TenantId;
import org.gombert.cooking.recipe.domain.port.in.CreateRecipeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

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
