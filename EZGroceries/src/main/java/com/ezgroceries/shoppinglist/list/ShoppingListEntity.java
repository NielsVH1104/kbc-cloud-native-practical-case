package com.ezgroceries.shoppinglist.list;

import com.ezgroceries.shoppinglist.cocktails.CocktailEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="shopping_list")
public class ShoppingListEntity {

    @Id
    @Column(name = "ID")
    private Long shoppingListId;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "shoppingLists")
    private Set<CocktailEntity> coctails;

    public ShoppingListEntity(Long shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        coctails = new HashSet<>();
    }

    public Long getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(Long shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CocktailEntity> getCoctails() {
        return coctails;
    }

    public void setCoctails(Set<CocktailEntity> coctails) {
        this.coctails = coctails;
    }

    public void addCocktail(CocktailEntity cocktail){
        coctails.add(cocktail);
    }
}
