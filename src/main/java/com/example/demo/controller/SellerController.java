package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.SellerDTO;
import com.example.demo.service.ProductService;
import com.example.demo.service.SellerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
public class SellerController {

    public final SellerService sellerService;
    public final ProductService productService;

    @GetMapping("/seller/goProductList")
    public String goSellerProductList(HttpSession session, Model model){

        String sellerId=(String) session.getAttribute("loginId");

        int sellerSeq = sellerService.findSellerSeqBySellerId(sellerId);
        List<ProductDTO> myProductList=sellerService.findMyProduct(sellerSeq);

        model.addAttribute("myProductList",myProductList);

        return "seller/productList";

    }

    @GetMapping("/seller/goAddProduct")
    public String goAddSellerProduct(){

        return "seller/addProduct";

    }

    @GetMapping("/goSellerMain")
    public String goSellerMain(){

        return "seller/main";

    }

    @PostMapping("/seller/addProductPro")
    public String addProductPro (HttpSession session,ProductDTO productDTO, Model model){

        String sellerId= (String) session.getAttribute("loginId");
        int sellerSeq = sellerService.findSellerSeqBySellerId(sellerId);

        productDTO.setSellerSeq(sellerSeq);

        sellerService.addProductPro(productDTO);

        return "redirect:/seller/goProductList";
    }

    @GetMapping("/seller/update/{productSeq}")
    public String updateForm(@PathVariable int productSeq, Model model){

        ProductDTO updateProduct = productService.findBySeq(productSeq);
        model.addAttribute("updateProduct",updateProduct);

        return "/seller/updateProduct";

    }

    @PostMapping("/seller/updateProductPro")
    public String updateProductPro(@ModelAttribute ProductDTO productDTO,
                                   HttpSession session){

        String sellerId= (String) session.getAttribute("loginId");
        productDTO.setSellerSeq(sellerService.findSellerSeqBySellerId(sellerId));

        productService.update(productDTO);


        return "redirect:/seller/goProductList";
    }



    @GetMapping("/seller/delete/{productSeq}")
    public String deleteByProductSeq(@PathVariable int productSeq){

        productService.deleteBySeq(productSeq);

        return "redirect:/seller/goProductList";

    }

}//class
