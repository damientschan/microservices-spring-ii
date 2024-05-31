package ch.hearc.jee24.usersservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@Configuration
@EnableLdapRepositories(basePackages = "ch.he-arc.**")
public class UsersServiceConfiguration {

}
