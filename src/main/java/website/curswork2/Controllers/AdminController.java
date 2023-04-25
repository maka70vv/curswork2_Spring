package website.curswork2.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import website.curswork2.models.Home;
import website.curswork2.models.Order;
import website.curswork2.models.Role;
import website.curswork2.models.User;
import website.curswork2.repositories.OrderRepository;
import website.curswork2.services.OrderService;
import website.curswork2.services.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/admin")
    public String admin(Model model){
        List<User> listUsers = userService.listAll();
        Iterable<Order> orders = orderRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("order", orders);
        return "admin";
    }
    @GetMapping("/admin/user/edit/{id}")
    public String userEdit(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/admin/order/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        orderService.deleteProduct(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }

}
