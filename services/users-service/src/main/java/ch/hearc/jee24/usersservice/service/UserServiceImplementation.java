package ch.hearc.jee24.usersservice.service;

import ch.hearc.jee24.usersservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public User create(User user) throws NoSuchAlgorithmException {
        user.setPassword(digestSHA(user.getPassword()));
        user.setId(LdapUtils.emptyLdapName());
        return repository.save(user);
    }

    @Override
    public Optional<User> get(String username) {
        Optional<User> optionalUser = Optional.empty();
        User user = repository.findByUsername(username);
        if(user != null)
            optionalUser = Optional.of(user);
        return optionalUser;
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User update(String username, User newUser) throws NoSuchAlgorithmException{
        User oldUser = repository.findByUsername(username);
        if(newUser.getPassword() != null)
            newUser.setPassword(digestSHA(newUser.getPassword()));
        newUser.setId(oldUser.getId());
        return repository.save(newUser);
    }

    @Override
    public void delete(String username) {
        User user = repository.findByUsername(username);
        repository.delete(user);
    }

    @Override
    public Boolean authenticate(User user) throws NoSuchAlgorithmException{
        String username = user.getUsername();
        String password = digestSHA(user.getPassword());
        return repository.findByUsernameAndPassword(username, password) != null;
    }

    private String digestSHA(String cleartext_password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(cleartext_password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
