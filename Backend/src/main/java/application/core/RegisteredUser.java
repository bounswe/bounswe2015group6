package application.core;

import javax.persistence.Column;
import application.miscalleneous.Result;
import org.hibernate.type.ArrayType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by Baris on 23.12.2015.
 *
 *
 * RegisteredUser class extends User class, it will be use for registered users
 *
 */
public class RegisteredUser extends User{

    /**
     * it is date of the the login
     *
     */
    @Column(name = "loginDate")
    String loginDate;

    /**
     * log out date
     */
    @Column(name = "logoutDate")
    String logoutDate;

    /**
     * registration date
     */
    @Column(name = "regDate")
    String regDate;

}
