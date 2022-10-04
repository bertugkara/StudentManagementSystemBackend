package com.tubitakyte.studentmanagementsystem.User.Services;

import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;

import java.util.List;

public interface UserService<T extends User> {

    DataResult<T> getByEmail(String email);

    DataResult<List<T>> getAll();

}
