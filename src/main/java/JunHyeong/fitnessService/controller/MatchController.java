package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.PartnerMatchDto;
import JunHyeong.fitnessService.dto.PtMatchDto;
import JunHyeong.fitnessService.repository.PtMatchingRepository;
import JunHyeong.fitnessService.service.MatchingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


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

    @PostMapping("/match-pt/delete")
    public String delete_pt(HttpServletRequest httpServletRequest, Model model) {
        if(matchingService.deletePt(Long.parseLong(httpServletRequest.getParameter("match_id")))) {
            model.addAttribute("message", "Pt가 삭제 되었습니다.");
        }
        else {
            model.addAttribute("message", "Pt 삭제를 실패했습니다.");
        }
        return "state";
    }
    @PostMapping("/match-partner")
    public String match_partner(HttpServletRequest httpServletRequest, Model model) {

        model.addAttribute("message", matchingService.matchPartner(PartnerMatchDto.builder()
                .post_id(Long.parseLong(httpServletRequest.getParameter("post_id")))
                .user_id(httpServletRequest.getParameter("id"))
                .user_pwd(httpServletRequest.getParameter("password"))
                .build()));
        return "state";
    }

    @PostMapping("/match-partner/delete")
    public String delete_partner(HttpServletRequest httpServletRequest, Model model) {

        if(matchingService.deletePartner(Long.parseLong(httpServletRequest.getParameter("match_id")))) {
            model.addAttribute("message", "match가 삭제 되었습니다.");
        }
        else {
            model.addAttribute("message", "match 삭제에 실패하셨습니다.");
        }
        return "state";
    }

}
