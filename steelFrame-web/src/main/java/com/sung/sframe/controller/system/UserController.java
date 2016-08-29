package com.sung.sframe.controller.system;

import com.sung.sframe.common.controller.BaseCRUDController;
import com.sung.sframe.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sungang on 2016/7/4.
 */

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseCRUDController<User> {


    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String to_login(Model model){
        return viewName("login");
    }

}
