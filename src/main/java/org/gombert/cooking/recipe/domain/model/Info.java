package org.gombert.cooking.recipe.domain.model;

import lombok.*;

@Getter(AccessLevel.MODULE)
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@RequiredArgsConstructor
public class Info
{
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String comment;
}
