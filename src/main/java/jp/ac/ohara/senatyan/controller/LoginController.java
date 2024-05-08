package jp.ac.ohara.senatyan.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.ac.ohara.senatyan.model.TeacherModel;
import jp.ac.ohara.senatyan.service.Teacherservice;
 
 
@Controller

@RequestMapping("/")

public class LoginController{
	@Autowired
	Teacherservice teacherservice;

    /**

     * ログイン画面へ遷移します.

     *

     * @return login.html

     */

    @RequestMapping(path = "/login/")

// 設定ファイルでログイン失敗時にはerror=tureを渡すようにしている。

//   ⇒コンソールに「ログインに失敗しました」と表示される。(ログイン成功時には何も表示されない。)

    public String showLogin(@RequestParam(required = false) String error) {

        System.err.println("login error:" + error);

        if (error != null) {

            System.err.println("ログインに失敗しました。");

        }

        return "login";

    }
    
    @GetMapping("/login/")
    public ModelAndView login(ModelAndView model, TeacherModel teachermodel) {
    	model.addObject("teacher", teachermodel);
    	model.setViewName("login");
    	return model;
    }
    
    @PostMapping("/login/")
    public String postLogin(Model model, TeacherModel teachermodel) {
    	this.teacherservice.loadUserByUsername(teachermodel.getTid());
    	return "redirect:/";
    }

    @GetMapping("/signup/")
    public ModelAndView singup(ModelAndView model,TeacherModel teachermodel) {
    	model.addObject("TeacherModel", teachermodel);
    	model.setViewName("signup");
    	return model;
    }
    
    @PostMapping("/signup/")
    public String signup(Model model, TeacherModel teachermodel) {
    	teacherservice.save(teachermodel);
    	return "login";
    }
}
