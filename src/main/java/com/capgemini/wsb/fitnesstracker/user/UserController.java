package com.capgemini.wsb.fitnesstracker.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/users")

public class UserController {

    @GetMapping
    public List<UserDto> getAllUsers() {
        return List.of(new UserDto(UUID.randomUUID(), "Jan Kowalski"), new UserDto(UUID.randomUUID(), "Anna Nowak"));
    }
}
