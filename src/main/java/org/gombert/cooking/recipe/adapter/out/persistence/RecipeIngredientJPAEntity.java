package org.gombert.cooking.recipe.adapter.out.persistence;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "recipe_ingredient")
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientJPAEntity
{
    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column
    double amount;

    @Column
    String unit;
}
