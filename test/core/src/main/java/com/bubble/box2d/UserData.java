package com.bubble.box2d;

import com.bubble.enums.UserDataType;

public abstract class UserData {
    protected UserDataType userDataType;

    public UserData() {

    }

    public UserDataType getUserDataType() {
        return userDataType;
    }

}
