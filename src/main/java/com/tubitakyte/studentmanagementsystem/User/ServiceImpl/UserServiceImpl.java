package com.tubitakyte.studentmanagementsystem.User.ServiceImpl;

import com.tubitakyte.studentmanagementsystem.User.DataAccess.UserDao;
import com.tubitakyte.studentmanagementsystem.User.Services.UserService;
import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.utilities.DataResult;
import com.tubitakyte.studentmanagementsystem.utilities.ErrorDataResult;
import com.tubitakyte.studentmanagementsystem.utilities.Result;
import com.tubitakyte.studentmanagementsystem.utilities.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService<User> {

    //Dependency Injection
    private final UserDao<User> userDao;

    @Override
    public DataResult<User> getByEmail(String email) {
        User user=userDao.findByEmail(email);
        if(user!=null){
            return new DataResult<>(user,true,"User Listed");
        }
         return new ErrorDataResult<>("No user Found");
    }

    @Override
    public DataResult<List<User>> getAll() {
        List<User> userList=userDao.findAll();
        if(!userList.isEmpty()){
            return new DataResult<>(userList,true,"User Listed");
        }
        return new ErrorDataResult<>("No user Found");
    }


    public Result makeUserActive (int id){

        Optional<User> user = userDao.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("No user found");
        }
        user.get().updateStatusToTrue();
        userDao.save(user.get());

        return new SuccessResult("Success!");
    }
    public Result makeUserPassive (int id){

        Optional<User> user = userDao.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("No user found");
        }
        user.get().updateStatusToFalse();
        userDao.save(user.get());

        return new SuccessResult("Success!");
    }

}
