package application.repository;

import application.core.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Integer>{

    User findById(Integer id);
    User findByUsername(String username);
    User findByEmail(String email);
    ArrayList<User> findAll();

    @Query("SELECT T.profilePictureUrl" +
            "FROM User T WHERE T.id= :id")
    String getProfilePictureUrl(@Param("id") int id);
}
