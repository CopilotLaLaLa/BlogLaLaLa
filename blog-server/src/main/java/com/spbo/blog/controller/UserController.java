package com.spbo.blog.controller;

import com.spbo.blog.service.UserService;
import com.spbo.blog.domain.Usermsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign")
    public boolean signIn(@RequestBody  Usermsg Usermsg) {
        System.out.println(Usermsg);
        if (Usermsg.getUserhig() == null || "".equals(Usermsg.getUserhig())) {
            Usermsg.setUserhig("http://localhost:8081/image?imageName=defuser.png");
            Usermsg.setUsername(Usermsg.getIduser());
        }
        if (userService.userSignIn(Usermsg)) {
            return true;
        }
        return false;
    }

    @RequestMapping("/sLogin")
    public boolean login(@RequestParam("ciduser")  String iduser,
                          @RequestParam("cuserpsw")  String userpsw,
                          HttpServletResponse response) {
        Usermsg userModel = userService.userLogin(iduser, userpsw);
        System.out.println("登录：" + userModel);
        if (null == userModel) {
            return false;
        }
        Cookie cookie = new Cookie("UserId", String.valueOf(userModel.getIduser()));
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        return true;
    }

    @RequestMapping("/logout")
    public boolean logout(@CookieValue("UserId")
                            String UserId, HttpServletResponse response) {
        Cookie cookie = new Cookie("UserId", UserId);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return true;
    }


    @GetMapping("/info")
    public Usermsg getOneUser(@CookieValue("UserId")  String id) {
        if ("".equals(id) || id==null){
            Usermsg fMsg = new Usermsg();
            fMsg.setUserroot(false);
            return fMsg;
        }else {
            userService.getUser(id);
            return userService.getUser(id);
        }
    }

    @PostMapping("/upInfo")
    public boolean updateUserPublicInfo(@CookieValue("UserId") String id,
                                        @RequestBody  Usermsg userModel) {
        userModel.setIduser(id);
        if (userService.updateUserInfo(userModel)) {
            return true;
        }
        return false;
    }


    @GetMapping("/password")
    public boolean updateUserPassword(@CookieValue("UserId")  String id,
                                       @RequestParam("oldPassword")  String oldPassword,
                                       @RequestParam("newPassword")  String newPassword) {
        if (userService.updatePassword(newPassword,oldPassword,id)) {
            return true;
        }
        return false;
    }



}
