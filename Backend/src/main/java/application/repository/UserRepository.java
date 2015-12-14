package application.repository;

import application.core.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Integer>{

    User findById(Integer id);
    User findByUsername(String username);
    User findByEmail(String email);
    ArrayList<User> findAll();
}
