package com.example.demo.controller;


import com.example.demo.dto.CustomerDTO;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.SellerDTO;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    public final CustomerService customerService;
    public final SellerService sellerService;
    public final ProductService productService;
    public final OrderService orderService;

    @GetMapping("/customer/goShopping")
    public String goShopping(Model model){

        List<ProductDTO> shoppingList=productService.findAll();
        model.addAttribute("shoppingList",shoppingList);

        return "/customer/shoppingList";
    }

    @GetMapping("/customer/goOrderList")
    public String goOrderList(HttpSession session,Model model){

        String myId=(String) session.getAttribute("loginId");
        int customerSeq = customerService.findByCustomerId(myId).getCustomerSeq();

        List<OrderDTO> orderList=customerService.findByCustomerSeq(customerSeq);
        model.addAttribute("orderList",orderList);

        //지금은 미봉책으로 Product 객체를 가져와서 하지만
        //DB Orders를 수정해야함 ProductName도 같이 담겨오게

        return "customer/orderList";

    }


    @GetMapping("/customer/purchase/{productSeq}") //보안 해결방법 찾기
    public String purchaseForm(@PathVariable int productSeq, ProductEntity productEntity, Model model){

        ProductDTO selectedProduct = new ProductDTO();

        selectedProduct = productService.findBySeq(productSeq);

        model.addAttribute("selectedProduct",selectedProduct);
        //여러개 선택하는 것도 생각해보기

        return "customer/purchaseForm";

    }

    @PostMapping("/purchasePro")
    public String purchasePro(@ModelAttribute OrderDTO orderDTO, HttpSession session){

        String myId=(String) session.getAttribute("loginId");

        CustomerDTO myCustomerDTO=customerService.findByCustomerId(myId);
        int sellerSeq=productService.findSellerSeqByProductSeq(orderDTO.getProductSeq());

        orderDTO.setSellerSeq(sellerSeq);
        orderDTO.setCustomerSeq(myCustomerDTO.getCustomerSeq());
        orderDTO.setProductName(productService.findByProductSeq(orderDTO.getProductSeq()));

        System.out.println("Customer Controller : "+orderDTO);

        // 시간이 부족해 Order을 Customer Product Seller와 FK로 묶는걸 구현하지 못함
        // 원래는 DB에서 Product의 productAmount가 orderAmount만큼이 빠져나가야 함
        // 연결은 각각의 seq값이 FK로 묶음

        orderService.save(orderDTO);

        //주문 후 바로 주문 리스트로 가는 것도 생각해보기
        return "customer/main";

    }




}//class
