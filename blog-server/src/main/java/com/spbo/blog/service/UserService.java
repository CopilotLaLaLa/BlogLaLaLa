package com.spbo.blog.service;

import com.spbo.blog.dao.UsermsgDao;
import com.spbo.blog.domain.Usermsg;

import java.util.List;


public interface UserService {

    Usermsg getUser(String id);

    Usermsg userLogin(String accountNumber, String userPassword);

    boolean userSignIn(Usermsg userModel);

    boolean updateUserInfo(Usermsg userModel);

    boolean updatePassword(String newPassword, String oldPassword,String id);
}
