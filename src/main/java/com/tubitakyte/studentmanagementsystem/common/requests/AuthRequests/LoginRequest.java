package com.tubitakyte.studentmanagementsystem.common.requests.AuthRequests;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class LoginRequest {
    private  String username;
    private  String password;
}