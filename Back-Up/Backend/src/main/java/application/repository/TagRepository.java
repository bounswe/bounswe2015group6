package application.repository;

import application.core.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TagRepository extends CrudRepository<Tag, Integer>{

    ArrayList<Tag> findAll();
    Tag findById(Integer id);
    Tag findByTagName(String tagName);
}
