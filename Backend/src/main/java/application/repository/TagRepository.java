package application.repository;

import application.core.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Repository of Tags
 */
public interface TagRepository extends CrudRepository<Tag, Integer>{

    /**
     *
     * @return
     */
    ArrayList<Tag> findAll();

    /**
     *
     * @param id
     * @return
     */
    Tag findById(Integer id);

    /**
     *
     * @param tagName
     * @return
     */
    Tag findByTagName(String tagName);
}
