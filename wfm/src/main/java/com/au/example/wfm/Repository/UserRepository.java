package com.au.example.wfm.Repository;

import com.au.example.wfm.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ayhan.Ugurlu on 13/02/2018
 */
public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
}
