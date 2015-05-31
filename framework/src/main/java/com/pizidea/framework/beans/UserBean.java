package com.pizidea.framework.beans;

import java.io.Serializable;

/**
 * desc your class
 * Created by yflai on 2015/5/31.
 */
public class UserBean implements Serializable{
    private String userName;
    private String userPass;
    private int userId;
    private int userAvatar;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(int userAvatar) {
        this.userAvatar = userAvatar;
    }


}
