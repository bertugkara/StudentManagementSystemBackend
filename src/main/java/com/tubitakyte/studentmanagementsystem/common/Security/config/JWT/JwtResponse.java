package com.tubitakyte.studentmanagementsystem.common.Security.config.JWT;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String email;
    private String username;
    private List<String> roles;
    public JwtResponse(String token, String username,String email, int Id, List<String> roles) {
        super();
        this.token = token;
        this.username = username;
        this.id=Id;
        this.email=email;
        this.roles = roles;
      //  System.out.println("ben JWT response constructoruyum.");
    }

}