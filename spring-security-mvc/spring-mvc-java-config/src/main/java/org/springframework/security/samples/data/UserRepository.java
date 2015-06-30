package org.springframework.security.samples.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.samples.data.User;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author wvergara, created on 6/9/15.
 */

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email=?1 and u.password=?2")
    User login(String email, String password);

    User findByEmailAndPassword(String email, String password);

    User findUserByEmail(String email);

}
