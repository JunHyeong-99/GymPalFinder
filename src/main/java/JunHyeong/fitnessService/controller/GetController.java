package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.*;
import JunHyeong.fitnessService.service.AuthService;
import JunHyeong.fitnessService.service.MypageService;
import JunHyeong.fitnessService.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class GetController {

    private final AuthService authService;
    private final PostService postService;
    private final MypageService mypageService;


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
            String role;
            role = httpServletRequest.getParameter("role");
            if (Objects.equals(role, "ROLE_TRAINER")) {
                List<MypageDto> mypageDto = mypageService.trainer_mypage(httpServletRequest.getParameter("email"));
                model.addAttribute("matchList", mypageDto);
                return "mypage/trainer_mypage";
            }
            else if (Objects.equals(role, "ROLE_CUSTOMER")) {
                List<MypageDto> mypageDto = mypageService.customer_mypage(httpServletRequest.getParameter("email"));
                model.addAttribute("matchList", mypageDto);
                return "mypage/customer_mypage";
            }
            else if (Objects.equals(role, "ROLE_PARTNERUSER")){
                List<MypageDto> mypageDto = mypageService.partner_mypage(httpServletRequest.getParameter("email"));
                model.addAttribute("matchList", mypageDto);
                return "mypage/partner_mypage";
            }
            else {
                model.addAttribute("message", "ROLE을 찾을 수 없습니다..");
                return "state";
            }
        }
        else {
            model.addAttribute("message", "아이디 비밀번호가 일치하지 않습니다.");
            return "state";
        }
    }
    @GetMapping("trainer-post-list")
    public String trainer_list(Model model) { // model에 다 넣어주기
        model.addAttribute("postList", postService.getResponsePtPost());
        return "get_service/get_trainer_list";
    }

    @GetMapping("pt-post-detali")
    public String pt_post_detail(@RequestParam Long post_id, Model model) throws Exception {
        PtPostDetailDto postDetailDto = postService.getPtPostDetail(post_id);
        model.addAttribute("title", postDetailDto.getTitle());
        model.addAttribute("price", postDetailDto.getPrice());
        model.addAttribute("description", postDetailDto.getDescription());
        model.addAttribute("phone_number", postDetailDto.getPhone_number());
        model.addAttribute("post_id", post_id);
        return "get_service/pt_post_Detail";
    }
    @GetMapping("partner-post-list")
    public String partner_list(Model model) { // model에 다 넣어주기
        model.addAttribute("postList", postService.getResponsePartnerPost());
        return "get_service/get_partner_list";
    }

    @GetMapping("partner-post-list/male")
    public String partner_list_male(Model model) { // model에 다 넣어주기
        model.addAttribute("postList", postService.getResponseMalePartnerPost());
        return "get_service/get_partner_list";
    }

    @GetMapping("partner-post-list/female")
    public String partner_list_female(Model model) { // model에 다 넣어주기
        model.addAttribute("postList", postService.getResponseFemalePartnerPost());
        return "get_service/get_partner_list";
    }

    @GetMapping("partner-post-detali")
    public String partner_post_detail(@RequestParam Long post_id, Model model) throws Exception {
        PartnerPostDetailDto partnerPostDetail = postService.getPartnerPostDetail(post_id);
        model.addAttribute("title", partnerPostDetail.getTitle());
        model.addAttribute("weight_sum", partnerPostDetail.getWeight_sum());
        model.addAttribute("description", partnerPostDetail.getDescription());
        model.addAttribute("phone_number", partnerPostDetail.getPhone_number());
        model.addAttribute("post_id", post_id);
        return "get_service/partner_post_Detail";
    }
}
