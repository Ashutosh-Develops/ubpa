package com.github.ashutosh.ubpa.dto.response;

import com.github.ashutosh.ubpa.entity.User;
import lombok.*;

@AllArgsConstructor
public class UserResponseBody {

    private long userId;

    public UserResponseBody() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static UserResponseBody generateResponseBodyFromUser(User user){

        UserResponseBody userResponseBody=new UserResponseBody();
        userResponseBody.setUserId(user.getId());
        return userResponseBody;
    }
}
