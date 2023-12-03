package JunHyeong.fitnessService.controller;


import JunHyeong.fitnessService.dto.LoginDto;
import JunHyeong.fitnessService.dto.PartnerPostDto;
import JunHyeong.fitnessService.dto.PtPostDto;
import JunHyeong.fitnessService.service.AuthService;
import JunHyeong.fitnessService.service.RegistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegistController {

    private AuthService authService;
    private RegistService registService;
    @GetMapping("pt-post")
    public String form_pt_post() {
        return "/post_registration/pt_registration";
    }
    @PostMapping("pt-post")
    public String add_pt_post(HttpServletRequest httpServletRequest, Model model) {
        boolean login = authService.isLogin(LoginDto.builder()
                        .email(httpServletRequest.getParameter("email"))
                        .password(httpServletRequest.getParameter("password"))
                        .build());

        if(!login) {
            model.addAttribute("message", "아이디 비밀번호가 일치하지 않습니다.");
        }

        else if(registService.pt_post_add(PtPostDto.builder()
                        .email(httpServletRequest.getParameter("email"))
                        .title(httpServletRequest.getParameter("title"))
                        .description(httpServletRequest.getParameter("description"))
                        .price(Integer.parseInt(httpServletRequest.getParameter("price")))
                        .build())) {
            model.addAttribute("message", "pt post등록에 성공했습니다.");
        }
        else {
            model.addAttribute("message", "pt post등록에 실패했습니다.");
        }

        return "state";
    }
    @GetMapping("partner-post")
    public String form_partner_post() {
        return "post_registration/partner_registration";
    }

    @PostMapping("partner-post")
    public String add_partner_post(HttpServletRequest httpServletRequest, Model model) {
        boolean login = authService.isLogin(LoginDto.builder()
                .email(httpServletRequest.getParameter("email"))
                .password(httpServletRequest.getParameter("password"))
                .build());

        if(!login) {
            model.addAttribute("message", "아이디 비밀번호가 일치하지 않습니다.");
        }

        else if(registService.partner_post_add(PartnerPostDto.builder()
                .email(httpServletRequest.getParameter("email"))
                .title(httpServletRequest.getParameter("title"))
                .description(httpServletRequest.getParameter("description"))
                .weight_sum(Integer.parseInt(httpServletRequest.getParameter("weight_sum")))
                .want_gender(httpServletRequest.getParameter("gender"))
                .build())) {
            model.addAttribute("message", "partner post등록에 성공했습니다.");
        }
        else {
            model.addAttribute("message", "partner post등록에 실패했습니다.");
        }

        return "state";
    }
}
