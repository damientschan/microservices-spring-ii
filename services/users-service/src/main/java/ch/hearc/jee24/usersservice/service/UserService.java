package ch.hearc.jee24.usersservice.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user) throws NoSuchAlgorithmException;
    Optional<User> get(String username);
    List<User> getAll();
    User update(String username, User user) throws NoSuchAlgorithmException;
    void delete(String username);
    Boolean authenticate(User user) throws NoSuchAlgorithmException;
}
