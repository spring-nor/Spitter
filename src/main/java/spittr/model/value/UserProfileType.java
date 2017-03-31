package spittr.model.value;

import java.io.Serializable;

/**
 * Created by norman on 31/03/17.
 */

public enum UserProfileType implements Serializable {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userProfileType;

    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }
}
