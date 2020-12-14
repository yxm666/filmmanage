package yxm.filmmanage.controller;

import yxm.filmmanage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import yxm.filmmanage.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
@SessionAttributes({"userId","userName"})        //Model里对应的值保存到session中
public class UserController {
    @Autowired
    private UserService userService;

    // 跳转到登陆界面
    @RequestMapping("/login.do")
    public String login(){
        return "register";
    }

    @RequestMapping("/register.do")
    public String register(Model model, User user){
        // 根据登陆信息查询用户
        User u = userService.register(user);
        // 如果用户存在，登陆成功跳转到影片浏览,因为进入系统第一显示的是影片
        if(u != null){
            model.addAttribute("userId",u.getUserId());
            model.addAttribute("userName",u.getUserName());
            return "redirect:/film/film.do";
        }
        else {
            model.addAttribute("error","用户名或密码错误!");
            return "register";
        }
    }

    /**
     * 查看用户界面
     * @param model
     * @return
     */
    @RequestMapping("/user.do")
    public String findAll(Model model,User user){
        System.out.printf("/user.do?user="+user+"\n");
        //调用的是多条件的查询的方法，如果不传查询条件，则查询出所有
        List<User> list = userService.findExample(user);
        System.out.printf("list=="+list+"\n");
        model.addAttribute("list",list);
        return "user";
    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    @ResponseBody
    public User findById(Integer id){
        return userService.findById(id);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping("/update.do")
    @ResponseBody
    public User update(User user) {
        return userService.update(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public int delete(Integer id){
        return userService.delete(id);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/add.do")
    @ResponseBody
    public int add(User user) {
        return userService.add(user);
    }

    /**
     * 多条件查询用户
     * @param user
     * @return
     */
    @RequestMapping("/findExample.do")
    @ResponseBody
    public List<User> findExample(User user){
        return userService.findExample(user);
    }


}
