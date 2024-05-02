package jp.ac.ohara.senatyan.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
 
@Controller

@RequestMapping("/")

public class LoginController{

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

}
