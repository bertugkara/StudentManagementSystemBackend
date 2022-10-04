package com.tubitakyte.studentmanagementsystem.RegisterAndAuthControl.Services;

import com.tubitakyte.studentmanagementsystem.User.entity.User;
import com.tubitakyte.studentmanagementsystem.common.requests.AuthRequests.RegistrationRequest;


public interface RegistrationService<T extends User> {
    T register(RegistrationRequest request);

}
