package com.tubitakyte.studentmanagementsystem.User.ServiceImpl;


import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.Services.UserDTOservice;
import com.tubitakyte.studentmanagementsystem.User.UserDTO.UserDTO;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
@Transactional
public class UserDTOServiceImpl implements UserDTOservice<UserDTO> {

    private final UserDao<User> userDao;
     @Override
    public DataResult<UserDTO> getByEmail(String email) {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("email could not found!");
        }

         return new SuccessDataResult<>(new UserDTO(
                 user.getId(),
                 user.getFirstName(),
                 user.getEmail(),
                 user.getUsername(),
                 user.getLastName(),
                 user.getRoles()));
    }

    @Override
    public DataResult<UserDTO> getByUserID(int id) {
        Optional<User> user = userDao.findById(id);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("email could not found!");
        }

        return new SuccessDataResult<>( new UserDTO(
                user.get().getId(),
                user.get().getFirstName(),
                user.get().getEmail(),
                user.get().getUsername(),
                user.get().getLastName(),
                user.get().getRoles()
                ));
    }

    @Override
    public DataResult<List<UserDTO>> getAll() {
        List<User> optionalUser=userDao.findAll();
        if(optionalUser ==null){
            throw new UsernameNotFoundException("email could not found!");
        }
            List<UserDTO> userDTOList=new ArrayList<>();
            optionalUser.forEach((tempStudent)->{

                UserDTO userDTO = new UserDTO(tempStudent.getId(),
                        tempStudent.getFirstName(),
                        tempStudent.getEmail(),
                        tempStudent.getUsername(),
                        tempStudent.getLastName(),
                        tempStudent.getRoles()
                        );
                userDTOList.add(userDTO);
        });

        return new SuccessDataResult<>(userDTOList);
    }

    @Override
    public DataResult<List<UserDTO>> getAllActiveUsers() {
        List<User> optionalUser=userDao.findAllByIsActiveTrue();
        if(optionalUser ==null){
            throw new UsernameNotFoundException("email could not found!");
        }
        List<UserDTO> userDTOList=new ArrayList<>();
        optionalUser.forEach((tempStudent)->{

            UserDTO userDTO = new UserDTO(tempStudent.getId(),
                    tempStudent.getFirstName(),
                    tempStudent.getEmail(),
                    tempStudent.getUsername(),
                    tempStudent.getLastName(),
                    tempStudent.getRoles()
            );
            userDTOList.add(userDTO);
        });

        return new SuccessDataResult<>(userDTOList);
    }

    @Override
    public DataResult<List<UserDTO>> getAllDeactiveUsers() {
        List<User> optionalUser=userDao.findAllByIsActiveFalse();
        if(optionalUser ==null){
            throw new UsernameNotFoundException("email could not found!");
        }
        List<UserDTO> userDTOList=new ArrayList<>();
        optionalUser.forEach((tempStudent)->{

            UserDTO userDTO = new UserDTO(tempStudent.getId(),
                    tempStudent.getFirstName(),
                    tempStudent.getEmail(),
                    tempStudent.getUsername(),
                    tempStudent.getLastName(),
                    tempStudent.getRoles()
            );
            userDTOList.add(userDTO);
        });

        return new SuccessDataResult<>(userDTOList);
    }

}
