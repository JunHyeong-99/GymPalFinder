package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.LoginDto;
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
    @PostMapping("/mypage") // 트레이너, 파트너 유저, 고객에 따른 나의 등록 post 관리?? //post에 email, password가 날라온다
    public String mypage(HttpServletRequest httpServletRequest, Model model) {
        boolean Login = authService.isLogin(LoginDto.builder()
                .email(httpServletRequest.getParameter("email"))
                .password(httpServletRequest.getParameter("password"))
                .build());

        if (Login) {

        }
        else {

        }
        return "registration/test";
    }
}
