package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.SignDto;
import JunHyeong.fitnessService.entity.Gender;
import JunHyeong.fitnessService.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@AllArgsConstructor
public class SignController {

    private AuthService authService;

    @GetMapping("/sign/trainer")
    public String trainer_sign_page() {
        return "registration/trainer_user_registration";
    }

    @GetMapping("/sign/customer")
    public String customer_sign_page() {
        return "registration/pt_user_registration";
    }

    @GetMapping("/sign/partner-user")
    public String partner_user_sign_page() {
        return "registration/partner_user_registration";
    }

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
        Gender user_gender;
        if (Objects.equals(httpServletRequest.getParameter("gender"), "male"))
            user_gender = Gender.MALE;
        else user_gender = Gender.FEMALE;
        model.addAttribute("message", authService.partnerUser_sign(SignDto.builder()
                .email(httpServletRequest.getParameter("email"))
                .name(httpServletRequest.getParameter("name"))
                .phoneNumber(httpServletRequest.getParameter("phoneNumber"))
                .password(httpServletRequest.getParameter("password"))
                .gender(user_gender)
                .build()));
        return "registration/after_sign";
    }
}
