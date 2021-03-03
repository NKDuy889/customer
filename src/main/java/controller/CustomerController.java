package controller;

import model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.customer.CustomerService;
import service.customer.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private ICustomerService customerService = new CustomerService();
    @GetMapping("")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("view");
        List<Customer> customers = customerService.findAll();
        mav.addObject("list",customers);
        return mav;
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showFormEdit(@PathVariable Integer id, ModelMap modelMap){
        Customer cus  = customerService.findById(id);
        modelMap.addAttribute("student", cus);
        return "edit";
    }
    @PostMapping("/edit/{id}")
    public ModelAndView editStudent(@PathVariable int id, @ModelAttribute Customer customer) {
        customer.setId(id);
        customerService.update(customer, id);
        return new ModelAndView("view", "list", customerService.findAll());
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id, @ModelAttribute Customer customer) {
        List<Customer> customers = customerService.findAll();
        customers.remove(id);
        return new ModelAndView("redirect:/customers");
    }
    @GetMapping("/create")
    public ModelAndView formCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("s", new Customer());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView create(@ModelAttribute Customer customer){
        int id = customerService.findAll().size() + 1;
        customer.setId(id);
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("s", new Customer());
        return new ModelAndView("redirect:/customers");
    }

}
