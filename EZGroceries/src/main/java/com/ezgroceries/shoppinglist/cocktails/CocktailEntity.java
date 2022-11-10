package com.ezgroceries.shoppinglist.cocktails;

import com.ezgroceries.shoppinglist.converter.StringSetConverter;
import com.ezgroceries.shoppinglist.list.ShoppingListEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="cocktail")
public class CocktailEntity {

    @Id
    @Column(name = "ID")
    private UUID cocktailId;

    @Column(name="id_drink")
    private String idDrink;

    @Column(name="name")
    private String name;

    @Column(name="ingredients")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    @ManyToMany
    @JoinTable(
            name = "cocktail_shopping_list",
            joinColumns = @JoinColumn(name = "cocktail_id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_list_id"))
    private Set<ShoppingListEntity> shoppingLists;

    public CocktailEntity(){
        ingredients = new HashSet<>();
    };

    public CocktailEntity(UUID cocktailId, String idDrink, String name) {
        this.cocktailId = cocktailId;
        this.idDrink = idDrink;
        this.name = name;
        ingredients=new HashSet<>();
        shoppingLists = new HashSet<>();
    }

    public void addIngredient(String ingredient){
        ingredients.add(ingredient);
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<ShoppingListEntity> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(Set<ShoppingListEntity> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }
}
