package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.ResignDto;
import JunHyeong.fitnessService.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ResignController {
    private final AuthService authService;

    @GetMapping("/resign")
    private String load_resign() {
        return "resign";
    }
    @PostMapping("/resign")
    private String resign(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("message", authService.resign(ResignDto.builder()
                        .userType(httpServletRequest.getParameter("userType"))
                        .email(httpServletRequest.getParameter("email"))
                        .password(httpServletRequest.getParameter("password"))
                .build()));
        return "state";
    }

}
