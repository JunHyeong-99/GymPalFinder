package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.ReviewRequestDto;
import JunHyeong.fitnessService.dto.ReviewResponseDto;
import JunHyeong.fitnessService.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/match-pt/review")
    public String review_form(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("match_id", httpServletRequest.getParameter("match_id"));
        return "/review/review_form";
    }

    @PostMapping("/match-pt/review")
    public String review_create(HttpServletRequest httpServletRequest, Model model) {

        if(reviewService.create_review(ReviewRequestDto.builder()
                        .body(httpServletRequest.getParameter("body"))
                        .point(Integer.parseInt(httpServletRequest.getParameter("point")))
                        .match_id(Long.parseLong(httpServletRequest.getParameter("match_id")))
                        .build())) {
            model.addAttribute("message", "리뷰가 등록되었습니다.");
        }
        else {
            model.addAttribute("message", "리뷰 등록에 실패했습니다.");
        }
        return "state";
    }

    @GetMapping("/review")
    public String load_review(@RequestParam Long post_id, Model model){
        List<ReviewResponseDto> reviewResponseDtoList = reviewService.load_review(post_id);
        model.addAttribute("reviewList", reviewResponseDtoList);
        return "review/review_detail";
    }
}
