package com.ezgroceries.shoppinglist.users;

import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface UserRepository extends Repository<User, UUID> {

    public User findByUsername(String username);

    public void save(User user);
}
