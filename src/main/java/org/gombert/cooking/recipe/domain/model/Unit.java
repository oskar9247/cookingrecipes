package org.gombert.cooking.recipe.domain.model;

import lombok.*;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@RequiredArgsConstructor
public class Unit
{
    @NonNull
    private String name;
}
