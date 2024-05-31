package ch.hearc.jee24.usersservice.repository;

import ch.hearc.jee24.usersservice.service.User;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends LdapRepository<User> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
