package com.example.demo.controller;

import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.SellerDTO;
import com.example.demo.service.CustomerService;
import com.example.demo.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final CustomerService customerService;
    private final SellerService sellerService;

    @GetMapping("/customer/save")
    public String customerSave(){
        return "/user/customersave";
    }

    @GetMapping("/seller/save")
    public String sellerSave(){
        return "/user/sellersave";
    }

    @PostMapping("/customer/signin")
    public String customerSignin(@ModelAttribute CustomerDTO customerDTO){

        customerService.signin(customerDTO);

        return "redirect:/";

    }

    @PostMapping("/seller/signin")
    public String sellerSignin(@ModelAttribute SellerDTO sellerDTO){

        sellerService.signin(sellerDTO);

        return"redirect:/";
    }



    @PostMapping("/login")
    public String login(@RequestParam("userFlag") int userFlag,
                        @RequestParam("inputId") String inputId,
                        @RequestParam("inputPw") String inputPw,
                        HttpSession session,
                        Model model){

        if (userFlag==0){ //구매자

            CustomerDTO userDTO= new CustomerDTO();
            userDTO.setCustomerId(inputId);
            userDTO.setCustomerPw(inputPw);

            CustomerDTO loginResult = customerService.login(userDTO);

            if (loginResult!=null){

                //구매자 로그인 성공
                session.setAttribute("loginId",loginResult.getCustomerId());
                return "customer/main";

            } else{
                //구매자 로그인 실패

                model.addAttribute("message","로그인 실패");
                model.addAttribute("searchUrl", "/");

                return "message";

            }

        }else if(userFlag==1){ //판매자

            SellerDTO userDTO = new SellerDTO();
            userDTO.setSellerId(inputId);
            userDTO.setSellerPw(inputPw);

            SellerDTO loginResult = sellerService.login(userDTO);

            if (loginResult!=null){
                //판매자 로그인 성공

                session.setAttribute("loginId",loginResult.getSellerId());
                return "seller/main";

                //th if문 userFlag로 같은 화면을 두개로 나눠서 보게하는 방법

            } else{
                //판매자 로그인 실패

                model.addAttribute("message","로그인 실패");
                model.addAttribute("searchUrl", "/");

                return "message";

            }

        }else{

            System.out.println("login else");
            return "/";

        }

    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }




}//class
