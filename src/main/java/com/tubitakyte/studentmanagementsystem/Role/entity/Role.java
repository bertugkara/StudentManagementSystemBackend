package com.tubitakyte.studentmanagementsystem.Role.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tubitakyte.studentmanagementsystem.Role.enums.UserRole;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="roles")
@NoArgsConstructor
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private UserRole name;

    public Role(UserRole roleName) {
        super() ;
        this.name = roleName;
    }
}
