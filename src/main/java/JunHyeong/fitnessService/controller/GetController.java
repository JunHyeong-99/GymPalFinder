package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.SignDto;
import JunHyeong.fitnessService.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class GetController {

    private AuthService authService;


    @GetMapping("/mypageLogin")
    public String mypageLogin() {
        return "/login/mypageLogin";
    }
    @PostMapping("/mypage")
    public String mypage(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("message", authService.trainer_sign(SignDto.builder()
                .email(httpServletRequest.getParameter("email"))
                .name(httpServletRequest.getParameter("name"))
                .phoneNumber(httpServletRequest.getParameter("phoneNumber"))
                .password(httpServletRequest.getParameter("password"))
                .build()));
        return "registration/after_sign";
    }
}
