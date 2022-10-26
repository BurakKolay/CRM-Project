package egecoskun121.com.crm.controllers;

import egecoskun121.com.crm.model.DTO.ProductDTO;
import egecoskun121.com.crm.model.DTO.ProductInquiryDTO;
import egecoskun121.com.crm.model.entity.ProductInquiry;
import egecoskun121.com.crm.services.ProductInquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api/v1/inquiry")
public class ProductInquiryController {

    private final ProductInquiryService productInquiryService;


    public ProductInquiryController(ProductInquiryService productInquiryService) {
        this.productInquiryService = productInquiryService;
    }

    @RequestMapping(path = "/showList")
    public ModelAndView showProductInquiryList(){
        ModelAndView mav = new ModelAndView("productInquiryList");
        mav.addObject("productInquiry",productInquiryService.getAllProductInquiries());
        return mav;
    }

    @RequestMapping(path = "/addNewProductInquiry")
    public RedirectView addNewProductInquiry(@ModelAttribute ProductInquiryDTO productInquiryDTO){
        productInquiryService.saveNewProductInquiry(productInquiryDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showList");
        return redirectView;
    }

    @RequestMapping(path="/updateInquiry/{id}")
    public RedirectView updateInquiry(@PathVariable("id") Long id,@ModelAttribute ProductInquiryDTO productInquiryDTO){

        productInquiryService.updateProductInquiryById(id, productInquiryDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showList");

        return redirectView;
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id){
        ModelAndView mav= new ModelAndView("update-inquiry-form");
        ProductInquiry productInquiry = productInquiryService.getById(id);
        mav.addObject("productInquiry",productInquiry);
        return mav;
    }


    @RequestMapping(path = "/deleteInquiry")
    public RedirectView deleteProductInquiry(@RequestParam Long id){

        productInquiryService.deleteProductInquiryById(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/inquiry/showList");

        return redirectView;
    }


}
