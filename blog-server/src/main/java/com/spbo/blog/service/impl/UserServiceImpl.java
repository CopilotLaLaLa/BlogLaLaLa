package com.spbo.blog.service.impl;

import com.spbo.blog.domain.Usermsg;
import com.spbo.blog.dao.UsermsgDao;
import com.spbo.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UsermsgDao UsermsgDao;


    public Usermsg getUser(String id){
        return UsermsgDao.selectByPrimaryKey(id);
    }

    public Usermsg userLogin(String accountNumber, String userPassword){
        return UsermsgDao.userLogin(accountNumber,userPassword);
    }

    public boolean userSignIn(Usermsg userModel){
        int n=0;
        try {
            n=UsermsgDao.insert(userModel);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return n == 1;
    }

    public boolean updateUserInfo(Usermsg userModel){
        return UsermsgDao.updateByPrimaryKeySelective(userModel)==1;
    }

    public boolean updatePassword(String newPassword, String oldPassword,String id){
        return UsermsgDao.updatePassword(newPassword,oldPassword,id)==1;
    }
}
