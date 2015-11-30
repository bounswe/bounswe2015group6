package application.repository;

import application.core.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by User on 30.11.2015.
 */
public interface TopicRepository extends CrudRepository<Topic, Integer> {

    ArrayList<Topic> findAll();
    Topic findById(int id);
    Topic findByTitle(String title);
}
