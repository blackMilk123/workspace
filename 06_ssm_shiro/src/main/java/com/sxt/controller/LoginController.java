package com.sxt.controller;

import com.sxt.utils.ActivierUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 用户登陆控制器
 * @author LJH
 *
 */
@RequestMapping("login")
@Controller
public class  LoginController {

	/**
	 * 跳转到登陆页面
	 */
	@RequestMapping("toLogin")
	public String toLogin() {
		return "login";
	}


	/**
	 * 做登陆
	 */
	@RequestMapping("login")
	public String login(String username,String pwd,
                        HttpSession session,Model model) throws Exception {




        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);

        try {

            subject.login(token);
            ActivierUser activierUser = (ActivierUser) subject.getPrincipal();
            session.setAttribute("user",activierUser.getUser());
            return "redirect:/user/toUserManager.action";
        }catch (IncorrectCredentialsException e) {
            System.err.println("密码不正确");
            model.addAttribute("error", "密码不正确");
        } catch (UnknownAccountException e) {
            System.err.println("用户名不存在");
            model.addAttribute("error", "用户名不存在");
        }

        return "redirect:/login.jsp";

    }

}
