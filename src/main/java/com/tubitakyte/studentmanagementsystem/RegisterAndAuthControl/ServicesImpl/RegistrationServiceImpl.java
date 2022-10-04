package com.tubitakyte.studentmanagementsystem.RegisterAndAuthControl.ServicesImpl;

import com.tubitakyte.studentmanagementsystem.RegisterAndAuthControl.Services.RegistrationService;
import com.tubitakyte.studentmanagementsystem.Role.DataAccess.RoleDao;
import com.tubitakyte.studentmanagementsystem.Role.entity.Role;
import com.tubitakyte.studentmanagementsystem.Role.enums.UserRole;
import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Admin;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Assistant;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Professor;
import com.tubitakyte.studentmanagementsystem.User.entity.userTypes.Student;
import com.tubitakyte.studentmanagementsystem.common.requests.AuthRequests.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationServiceImpl<T extends User> implements RegistrationService<T> {

    private final UserDao<T> userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder encoder;

    @Override
    public T register(RegistrationRequest request) {
        if (userDao.findByEmail(request.getEmail()) != null) {
            throw new IllegalStateException("MAIL IS ALREADY IN USE");
        }
        if (userDao.findByUsernameQuery(request.getUsername()) != null) {
            throw new IllegalStateException("username IS ALREADY IN USE");
        }

        String type = request.getRole().toString();
        T user = null;
        user = createCorrectUserType(user, type, request);

        String encodedPassword = encoder.encode(user.getPassword());
        user.updatePassword(encodedPassword);

        Set<String> strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();

        user.updateRole(handleRoleAssignment(strRoles, roles));
        userDao.save(user);
        if (request.getRole().toString().equals("[ROLE_STUDENT]")) {
            handleUsernameChange(user);
            return userDao.findByEmail(user.getEmail());
        }
        return user;

    }

    private void handleUsernameChange(T user) {
        T temp = userDao.findByEmail(user.getEmail());
        userDao.changeUsernameForStudent(String.valueOf(temp.getId()), temp.getId());
    }

    private T createCorrectUserType(User user, String type, RegistrationRequest request) {

        switch (type) {
            case "[ROLE_ADMIN]" -> user = new Admin(request.getFirstname(), request.getEmail(),
                    request.getLastname(), request.getPassword(), request.getUsername());
            case "[ROLE_STUDENT]" -> user = new Student(request.getFirstname(), request.getEmail(),
                    request.getLastname(), request.getPassword(), request.getUsername());
            case "[ROLE_ASSISTANT]" -> user = new Assistant(request.getFirstname(), request.getEmail(),
                    request.getLastname(), request.getPassword(), request.getUsername());
            case "[ROLE_PROFESSOR]" -> user = new Professor(request.getFirstname(), request.getEmail(),
                    request.getLastname(), request.getPassword(), request.getUsername());
        }

        return (T) user;
    }

    private Set<Role> handleRoleAssignment(Set<String> strRoles, Set<Role> roles) {

        strRoles.forEach(role -> {
            switch (role) {
                case "ROLE_ADMIN" -> {
                    Role adminRole = roleDao.findByName(UserRole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role Admin is not found."));
                    roles.add(adminRole);
                }
                case "ROLE_STUDENT" -> {
                    Role studentRole = roleDao.findByName(UserRole.ROLE_STUDENT)
                            .orElseThrow(() -> new RuntimeException("Error: Role Student is not found."));
                    roles.add(studentRole);
                }
                case "ROLE_ASSISTANT" -> {
                    Role assistantRole = roleDao.findByName(UserRole.ROLE_ASSISTANT)
                            .orElseThrow(() -> new RuntimeException("Error: Role Assistant is not found."));
                    roles.add(assistantRole);
                }
                case "ROLE_PROFESSOR" -> {
                    Role professorRole = roleDao.findByName(UserRole.ROLE_PROFESSOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role Assistant is not found."));
                    roles.add(professorRole);
                }
            }
        });

        return roles;
    }


}
