package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.SignDto;
import JunHyeong.fitnessService.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class SignController {

    private AuthService authService;
    @PostMapping("/sign/trainer")
    public String trainer_sign(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("message", authService.trainer_sign(SignDto.builder()
                .email(httpServletRequest.getParameter("email"))
                .name(httpServletRequest.getParameter("name"))
                .phoneNumber(httpServletRequest.getParameter("phoneNumber"))
                .password(httpServletRequest.getParameter("password"))
                .build()));
        return "registration/after_sign";
    }

    @PostMapping("/sign/customer")
    public String customer_sign(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("message", authService.customer_sign(SignDto.builder()
                .email(httpServletRequest.getParameter("email"))
                .name(httpServletRequest.getParameter("name"))
                .phoneNumber(httpServletRequest.getParameter("phoneNumber"))
                .password(httpServletRequest.getParameter("password"))
                .build()));
        return "registration/after_sign";
    }
    @PostMapping("/sign/partner-user")
    public String partner_user_sign(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("message", authService.partnerUser_sign(SignDto.builder()
                .email(httpServletRequest.getParameter("email"))
                .name(httpServletRequest.getParameter("name"))
                .phoneNumber(httpServletRequest.getParameter("phoneNumber"))
                .password(httpServletRequest.getParameter("password"))
                .build()));
        return "registration/after_sign";
    }
}
