package com.sliit.slotopzi.dto.request;

import com.sliit.slotopzi.enums.UserType;

public class GetNameOfLoggedUserRequest {
    private long userId;
    private UserType userType;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
