package application.core;

import javax.persistence.Column;

/**
 * Created by Baris on 23.12.2015.
 *
 * RegisteredUser class extends User class, it will be use for registered users
 */
public class RegisteredUser extends User{

    @Column(name = "loginDate")
    String loginDate;

    @Column(name = "logoutDate")
    String logoutDate;

    /**
     * registration date
     */
    @Column(name = "regDate")
    String regDate;

}
