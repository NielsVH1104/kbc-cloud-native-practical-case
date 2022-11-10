package com.ezgroceries.shoppinglist.list;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface ShoppingListRepository extends Repository<ShoppingListEntity, UUID> {

    public ShoppingListEntity findByShoppingListId(UUID id);

    public List<ShoppingListEntity> findAll();

    public void save(ShoppingListEntity entity);
}
