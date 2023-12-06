package JunHyeong.fitnessService.controller;

import JunHyeong.fitnessService.dto.ReviewRequestDto;
import JunHyeong.fitnessService.dto.ReviewResponseDto;
import JunHyeong.fitnessService.entity.PtMatching;
import JunHyeong.fitnessService.entity.Review;
import JunHyeong.fitnessService.repository.PtMatchingRepository;
import JunHyeong.fitnessService.repository.ReviewRepository;
import JunHyeong.fitnessService.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final PtMatchingRepository ptMatchingRepository;
    private final ReviewRepository reviewRepository;

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
    public String load_trainer_review(@RequestParam Long post_id, Model model){
        List<ReviewResponseDto> reviewResponseDtoList = reviewService.load_review_by_post(post_id);
        model.addAttribute("reviewList", reviewResponseDtoList);
        return "review/review_detail";
    }

    @GetMapping("my-review-page")
    public String load_customer_review(HttpServletRequest httpServletRequest,  Model model) {

         httpServletRequest.getParameter("match_id");
        Optional<PtMatching> match = ptMatchingRepository.findById(Long.parseLong(httpServletRequest.getParameter("match_id")));
        List<Review> reviewList = reviewRepository.findByCustomer(match.get().getPtCustomer());

        if (reviewList.isEmpty()) {
            model.addAttribute("message", "등록하신 리뷰가 없습니다.");
            return "state";
        }
        else {
            List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();
            for (Review review : reviewList) {
                reviewResponseDtoList.add(review.toResponsePostDto());
            }
            model.addAttribute("reviewList", reviewResponseDtoList);
            return "review/customer_review";
        }
    }
    @PostMapping("review/delete")
    public String delete_review(HttpServletRequest httpServletRequest,Model model) {
        Optional<Review> review = reviewRepository.findById(Long.parseLong(httpServletRequest.getParameter("review_id")));
        if(review.isEmpty()) {
            model.addAttribute("message", "리뷰가 업습니다.");
            return "state";
        }
        reviewRepository.delete(review.get());
        model.addAttribute("message", "리뷰가 삭제되었습니다.");
        return "state";
    }
}
