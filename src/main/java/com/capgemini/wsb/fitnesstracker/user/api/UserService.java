package com.capgemini.wsb.fitnesstracker.user.api;
import java.util.Optional;


import java.time.LocalDate;
import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    Optional<User> getUserByEmail(String email);
    Optional<User> getUserByFirstNameAndLastName(String firstName, String lastName);
//    List<User> getUsersByBirthDateBefore(LocalDate date);
    List<User> getUsersOlderThan(LocalDate date);

    List<User> getUsersByEmailFragment(String emailFragment);


    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long userId);
}
