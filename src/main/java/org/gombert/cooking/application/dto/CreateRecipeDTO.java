package org.gombert.cooking.application.dto;

import java.util.*;

import lombok.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class CreateRecipeDTO
{
    private CreateRecipeDTO(){}

    private UUID clientGeneratedId;
    private String name;
    private String description;
    private String comment;
    private List<CreateRecipeIngredientDTO> recipeIngredients;
    private List<String> methodSteps;
}
