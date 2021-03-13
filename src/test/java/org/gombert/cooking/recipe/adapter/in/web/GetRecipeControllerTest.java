package org.gombert.cooking.recipe.adapter.in.web;

import java.util.UUID;
import java.util.stream.*;

import org.gombert.cooking.recipe.application.port.in.*;
import org.gombert.cooking.recipe.domain.model.*;
import org.gombert.cooking.recipe.domain.model.exception.RecipeNotFoundException;
import org.gombert.cooking.tenant.domain.model.TenantId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GetRecipeControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetRecipeUseCase mockedGetRecipeUseCase;

    final private RecipeId recipeId = new RecipeId(UUID.fromString("6f651a72-698c-4892-bd7b-8e553e6acfdc"));
    final private TenantId tenantId = new TenantId(UUID.fromString("d45768c7-4efe-49fa-b0b9-d8382f90f593"));

    @Test
    public void givenCorrectContent_whenGetRecipe_thenReturnRecipeDTO() throws Exception
    {
        final var ingredients = Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk", 1.0, "Liter")).collect(Collectors.toList());
        final var methods = Stream.of("Step1", "Step2").collect(Collectors.toList());
        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(recipeId, "RecipeName", "RecipeDesctiption", "RecipeComment", ingredients, methods);
        final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);
        Mockito.when(mockedGetRecipeUseCase.getRecipe(any(TenantId.class), any(RecipeId.class))).thenReturn(recipe);
        mockMvc.perform(get("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/6f651a72-698c-4892-bd7b-8e553e6acfdc")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        {
                            "recipeId":"6f651a72-698c-4892-bd7b-8e553e6acfdc",
                            "tenantId":"d45768c7-4efe-49fa-b0b9-d8382f90f593",
                            "name":"RecipeName",
                            "description":"RecipeDesctiption",
                            "comment":"RecipeComment",
                            "recipeIngredients":[{"ingredient":"Milk", "amount":1.0, "unit":"Liter"}],
                            "methodSteps":["Step1", "Step2"]
                        }
                        """));
    }

    @Test
    public void givenCorrectContentWithSpecialChars_whenGetRecipe_thenReturnRecipeDTO() throws Exception
    {
        final var ingredients = Stream.of(new CreateRecipeUseCase.CreateRecipeIngredientCommand("Milk@", 1.0, "Liter^°")).collect(Collectors.toList());
        final var methods = Stream.of("Step1â€™™", "Step2").collect(Collectors.toList());
        final var createRecipeCommand = new CreateRecipeUseCase.CreateRecipeCommand(recipeId, "RecipeName@", "RecipeDesctiptionâ€™™", "RecipeComment", ingredients, methods);
        final var recipe = RecipeFactory.createRecipe(tenantId, createRecipeCommand);
        Mockito.when(mockedGetRecipeUseCase.getRecipe(any(TenantId.class), any(RecipeId.class))).thenReturn(recipe);
        mockMvc.perform(get("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/6f651a72-698c-4892-bd7b-8e553e6acfdc")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                        {
                            "recipeId":"6f651a72-698c-4892-bd7b-8e553e6acfdc",
                            "tenantId":"d45768c7-4efe-49fa-b0b9-d8382f90f593",
                            "name":"RecipeName@",
                            "description":"RecipeDesctiptionâ€™™",
                            "comment":"RecipeComment",
                            "recipeIngredients":[{"ingredient":"Milk@", "amount":1.0, "unit":"Liter^°"}],
                            "methodSteps":["Step1â€™™", "Step2"]
                        }
                        """));
    }

    @Test
    public void givenNotValidTenantId_whenGetRecipe_thenReturnBadRequest400() throws Exception
    {
        mockMvc.perform(get("/v1/cookingrecipe/blaTenanId/recipes/6f651a72-698c-4892-bd7b-8e553e6acfdc")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenNotValidRecipeId_whenGetRecipe_thenReturnBadRequest400() throws Exception
    {
        mockMvc.perform(get("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/blaRecipeId")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenUnknownRecipeId_whenGetRecipe_thenReturnBadRequest400() throws Exception
    {
        Mockito.when(mockedGetRecipeUseCase.getRecipe(any(TenantId.class), any(RecipeId.class))).thenThrow(new RecipeNotFoundException(""));
        mockMvc.perform(get("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/6f651a72-698c-4892-bd7b-8e553e6acfdc")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenUnknownTenandId_whenGetRecipe_thenReturnBadRequest400() throws Exception
    {
        Mockito.when(mockedGetRecipeUseCase.getRecipe(any(TenantId.class), any(RecipeId.class))).thenThrow(new RecipeNotFoundException(""));
        mockMvc.perform(get("/v1/cookingrecipe/d45768c7-4efe-49fa-b0b9-d8382f90f593/recipes/6f651a72-698c-4892-bd7b-8e553e6acfdc")
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }
}