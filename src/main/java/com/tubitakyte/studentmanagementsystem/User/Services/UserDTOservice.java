package com.tubitakyte.studentmanagementsystem.User.Services;

import com.tubitakyte.studentmanagementsystem.User.UserDTO.UserDTO;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import org.springframework.context.annotation.Primary;

import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.List;

@Primary
public interface UserDTOservice<T extends UserDTO> {

    DataResult<T> getByEmail(String email);

    DataResult<T> getByUserID(int id);

    DataResult<List<T>> getAll();

    DataResult<List<T>> getAllActiveUsers();

    DataResult<List<T>> getAllDeactiveUsers();

}
