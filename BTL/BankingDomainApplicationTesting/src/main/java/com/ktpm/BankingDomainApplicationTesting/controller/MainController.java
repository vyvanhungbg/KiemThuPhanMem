package com.ktpm.BankingDomainApplicationTesting.controller;

import com.ktpm.BankingDomainApplicationTesting.entity.CustomUserDetails;
import com.ktpm.BankingDomainApplicationTesting.entity.FormMoney;
import com.ktpm.BankingDomainApplicationTesting.service.UserService;
import com.ktpm.BankingDomainApplicationTesting.ultil.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        //model.addAttribute("title", "Welcome");
       // model.addAttribute("message", "This is welcome page!");
        return "bank";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {

        CustomUserDetails loginedUser = (CustomUserDetails) ((Authentication) principal).getPrincipal();


        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new com.ktpm.BankingDomainApplicationTesting.entity.User());

        return "sign-up";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model,@RequestParam(name = "error", defaultValue = "false")Boolean error) {
        if(error){
            model.addAttribute("error",error);
            model.addAttribute("mess","Tài khoản mật khẩu không chính xác !");
        }
        model.addAttribute("user", new com.ktpm.BankingDomainApplicationTesting.entity.User());
        return "login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        CustomUserDetails loginedUser = (CustomUserDetails) ((Authentication) principal).getPrincipal();
        System.out.println();
        String userInfo = WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            CustomUserDetails loginedUser = (CustomUserDetails) ((Authentication) principal).getPrincipal();


            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }

    @PostMapping("/process_register")
    public String processRegister(com.ktpm.BankingDomainApplicationTesting.entity.User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getEncrytedPassword());
        user.setEncrytedPassword(encodedPassword);

        com.ktpm.BankingDomainApplicationTesting.entity.User newUser = userService.addNewUser(user);
        if(newUser == null){
            return "register_failed";
        }
        return "register_success";
    }

    @RequestMapping(value = "/tranfer", method = RequestMethod.GET)
    public String tranfer(@ModelAttribute("hoadon") FormMoney hoadon, ModelMap modelMap,Model model,  Principal principal) {
        if (principal != null) {
            CustomUserDetails loginedUser = (CustomUserDetails) ((Authentication) principal).getPrincipal();
            model.addAttribute("name", loginedUser.getUser().getUserName());
            model.addAttribute("sdt", loginedUser.getUser().getNumberPhone());
            model.addAttribute("money", loginedUser.getUser().getMoney());
        }
        return "tranfer_money";
    }

    @PostMapping("/clickTranfer")
    public String clickTranfer(@ModelAttribute("hoadon") FormMoney hoadon, ModelMap modelMap, Model model) {
        modelMap.addAttribute("hoadon", hoadon);
        System.out.println(hoadon.getNganHang());
        String mess = userService.tranferMoney(hoadon.getSdtNguoiNhan(), hoadon.getSoTien(), hoadon.getNganHang());
        System.out.println("--------++++++++++++++++++++++++++++"+mess);
        model.addAttribute("message", mess);
        return "success_tranfer";
    }




}
