package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserEmailSimpleDto;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserSimpleMapper userSimpleMapper;
    private final UserMapper userMapper;
    private final UserEmailSimpleMapper userEmailSimpleMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping("/simple")
    public List<UserSimpleDto> getAllBasicInformationAboutUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userSimpleMapper::toSimpleDto).toList();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
    }

    @GetMapping("/userEmail")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found with email " + email));
    }

    @GetMapping("/searchByName")
    public UserDto getUserByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName) {
        return userService.getUserByFirstNameAndLastName(firstName, lastName)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User with name " + firstName + " " + lastName + " was not found"));
    }
    @GetMapping("/searchByBirthDate")
    public List<UserDto> getUsersByBirthDateBefore(@RequestParam String date) {
        LocalDate birthDate = LocalDate.parse(date);
        return userService.getUsersByBirthDateBefore(birthDate).stream()
                .map(userMapper::toDto)
                .toList();
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {
        try {
            User user = userMapper.toEntity(userDto);
            userService.createUser(user);
        // Demonstracja how to use @RequestBody
        System.out.println("User with e-mail: " + userDto.email() + "passed to the request");
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot add user with email: " + userDto.email() + " with error: " + e.getMessage());
        }
        return null;
    }
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        try {
            User foundUser = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("User with ID: " + userId + " not found"));
            User updatedUser = userMapper.toUpdateEntity(userDto, foundUser);

            return userService.updateUser(updatedUser);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot update user with ID: " + userId + " with error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot delete user with ID: " + userId + " with error: " + e.getMessage());
        }
    }

  //  @GetMapping("/email")
  //  public List<UserEmailSimpleDto> getUserByEmail(@RequestParam String email) {
  //      return userService.getUserByEmailIgnoreCase(email)
  //              .stream()
  //              .map(userEmailSimpleMapper::toEmailSimpleDto)
  //              .toList();
  //  }

    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


}