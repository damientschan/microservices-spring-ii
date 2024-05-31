package ch.hearc.jee24.usersservice.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.ldap.LdapName;

@Entry(objectClasses = {"inetOrgPerson"})
@Getter @Setter
public final class User {
    public User() {}

    @Id
    @JsonIgnore
    private LdapName id;

    @Attribute(name = "cn")
    private String username;

    @Attribute(name = "ou")
    private String group;

    @Attribute(name = "userPassword")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}