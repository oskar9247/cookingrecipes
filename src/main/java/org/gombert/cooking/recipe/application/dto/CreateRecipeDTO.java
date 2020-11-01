package org.gombert.cooking.recipe.application.dto;

import java.util.*;

import lombok.*;
import org.gombert.cooking.recipe.domain.model.*;

@Getter(AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class CreateRecipeDTO
{
    @NonNull
    private UUID clientGeneratedId;
    @NonNull
    private TenantId tenantId;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String comment;
    @NonNull
    private List<CreateRecipeIngredientDTO> recipeIngredients;
    @NonNull
    private List<String> methodSteps;
}
