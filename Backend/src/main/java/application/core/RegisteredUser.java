package application.core;

import javax.persistence.Column;

/**
 * Created by Baris on 23.12.2015.
 */
public class RegisteredUser extends User{

    @Column(name = "loginDate")
    String loginDate;

    @Column(name = "logoutDate")
    String logoutDate;

}
