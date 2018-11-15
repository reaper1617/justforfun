package com.example.demo.repository;

import com.example.demo.entities.KickAssUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KickAssUserRepository extends CrudRepository<KickAssUser,Integer> {
//    KickAssUser findById(int id);
    KickAssUser findByName(String name);
    Iterable<KickAssUser> findAll();
    <User extends KickAssUser> User save(User user);
//    <User extends KickAssUser> User update(int id, String name, int numOfKicks);
}
