package application.repository;

import application.core.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Repository to keep all users
 */
public interface UserRepository extends CrudRepository<User, Integer>{

    /**
     * find user by its id
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * find user by its username
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * find user by its Email
     * @param email
     * @return
     */
    User findByEmail(String email);

    ArrayList<User> findByUserNameContaining(String userName);

    /**
     *
     * @param facebookId
     * @return
     */



    User findByFacebookId(String facebookId);

    /**
     *
     * @param twitterId
     * @return
     */
    User findByTwitterId(String twitterId);

    /**
     *
     * @param googleId
     * @return
     */
    User findByGoogleId(String googleId);


    /**
     * returns all users
     * @return
     */
    ArrayList<User> findAll();
}
