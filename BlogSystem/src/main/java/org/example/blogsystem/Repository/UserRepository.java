package org.example.blogsystem.Repository;

import org.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserId (Integer id);

    @Query("select u from User u where u.registrationDate between ?1 and ?2")
    List<User> getUsersRegisteredByDate(LocalDate date1, LocalDate date2);

}
