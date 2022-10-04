package com.tubitakyte.studentmanagementsystem.UserDetails.entity;

import com.tubitakyte.studentmanagementsystem.User.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
public class UserDetailsManager implements UserDetails {

    private Integer id;
    private String email;
    private String password;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
    public UserDetailsManager(Integer id, String email, String password,String username, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.username=username;
        this.authorities = authorities;
    }

    public static UserDetailsManager build(User user) {

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map( role ->
                        new SimpleGrantedAuthority(role.getName().name()) )
                .collect(Collectors.toList());


        return new UserDetailsManager(
                user.getId(),
                user.getEmail(),
                user.getPassword(), user.getUsername(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
