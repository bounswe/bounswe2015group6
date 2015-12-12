package application.core;

import application.miscalleneous.Result;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;

/**
 * Created by Baris on 08.12.2015.
 */
public class Tag {

    @Id
    @GeneratedValue
    int id;

    @Column(name = "name")
    String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
