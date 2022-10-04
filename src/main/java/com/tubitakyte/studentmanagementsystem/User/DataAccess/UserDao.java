package com.tubitakyte.studentmanagementsystem.User.DataAccess;

import com.tubitakyte.studentmanagementsystem.User.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Primary
@Repository
public interface UserDao<T extends User> extends JpaRepository<T,Integer> {
    T findByEmail(String email);

    @Query(value="select * from users u where u.username =:username", nativeQuery = true)
    T findByUsernameQuery(@Param(value = "username") String username);

    @Modifying
    @Transactional
    @Query("update User as u set u.username = ?1 where u.id = ?2")
    void changeUsernameForStudent( String username, Integer id);

    List<T> findAllByIsActiveTrue();

    List<T> findAllByIsActiveFalse();


}
