package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailSimpleDto;
import org.springframework.stereotype.Component;

//@Component
//public class UserEmailSimpleMapper {
//    UserEmailSimpleDto toEmailSimpleDto(User user) {
//        return new UserEmailSimpleDto(user.getId(), user.getEmail());
//    }
//
//    User toSimpleEmailEntity(UserEmailSimpleDto userDto) {
//        return new User(null, null, null, userDto.email());
//    }
//
//    public Object toSimpleDto(User user) {
//    }
//}
@Component
public class UserEmailSimpleMapper {

    public UserEmailSimpleDto toDto(User user) {
        return new UserEmailSimpleDto(user.getId(), user.getEmail());
    }
}