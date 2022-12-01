package com.ezgroceries.shoppinglist;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface ShoppingListRepository extends Repository<ShoppingListEntity, UUID> {

    ShoppingListEntity findByShoppingListId(UUID id);

    List<ShoppingListEntity> findAll();

    void save(ShoppingListEntity entity);
}
