package com.tubitakyte.studentmanagementsystem.User.entity;


import com.tubitakyte.studentmanagementsystem.Role.entity.Role;
import com.tubitakyte.studentmanagementsystem.common.BaseEntity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
@NoArgsConstructor
@Getter
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class User extends BaseEntity {
    @NotBlank
    private String firstName;
    @Column( unique = true)
    @Email
    @NotBlank
    private String email;

    @Column( unique = true)
    @NotBlank
    private String username;

    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    private boolean isActive= true; //DEFAULT VALUE

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String email, String lastName, String password,String username) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.password = password;
        this.username=username;
    }

    public void updateStatusToTrue(){
        this.isActive=true;
    }

    public void updateStatusToFalse(){
        this.isActive=false;
    }

    public void updatePassword(String password){
        this.password=password;
    }

    public void updateRole(Set<Role> roles){
        this.roles=roles;
    }
}