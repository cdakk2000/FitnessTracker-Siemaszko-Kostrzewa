package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    default List<User> findByEmailFragmentIgnoreCase(String emailFragment) {
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                .toList();
    }


    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);

    default List<User> findByBirthDate(LocalDate birthDate) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().equals(birthDate))
                .toList();
    }

    default List<User> findByBirthDateBefore(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(date))
                .toList();
    }

    default Optional<User> findByBirthdate(LocalDate birthdate) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().equals(birthdate))
                .findFirst();
    }

}
