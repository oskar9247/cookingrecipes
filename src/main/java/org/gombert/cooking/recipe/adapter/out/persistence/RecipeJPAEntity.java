package org.gombert.cooking.recipe.adapter.out.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

@Entity
@Table(name = "recipe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeJPAEntity implements Serializable
{
    @Id
    UUID recipeId;

    @Column
    UUID tenantId;

    @Column
    String name;

    @Column
    String description;

    @Column
    String comment;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    List<RecipeIngredientJPAEntity> recipeIngredients;

    @ElementCollection
    List<String> methods;

}
