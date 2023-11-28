package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.PtMatchDto;
import JunHyeong.fitnessService.repository.PtMatchingRepository;
import JunHyeong.fitnessService.service.MatchingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;

@Controller
@AllArgsConstructor
public class MatchController {

    private final PtMatchingRepository ptMatchingRepository;
    private final MatchingService matchingService;
    @PostMapping("/match-pt")
    public String match_pt(HttpServletRequest httpServletRequest, Model model) {
        if (matchingService.matchPt(PtMatchDto.builder()
                .post_id(Long.parseLong(httpServletRequest.getParameter("post_id")))
                .user_id(httpServletRequest.getParameter("id"))
                .user_pwd(httpServletRequest.getParameter("password"))
                .build())) {
            model.addAttribute("message", "트레이너와 매치되셨습니다.");
        }
        else {
            model.addAttribute("message", "트레이너와 매치에 실패했습니다.");
        }
        return "state";
    }
}