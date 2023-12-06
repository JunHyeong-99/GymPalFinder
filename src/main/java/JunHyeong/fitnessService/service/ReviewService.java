package JunHyeong.fitnessService.service;


import JunHyeong.fitnessService.dto.ReviewRequestDto;
import JunHyeong.fitnessService.dto.ReviewResponseDto;
import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PtMatching;
import JunHyeong.fitnessService.entity.PtPost;
import JunHyeong.fitnessService.entity.Review;
import JunHyeong.fitnessService.repository.PtMatchingRepository;
import JunHyeong.fitnessService.repository.PtPostRepository;
import JunHyeong.fitnessService.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PtMatchingRepository ptMatchingRepository;
    private final PtPostRepository ptPostRepository;
    public boolean create_review(ReviewRequestDto reviewRequestDto) {
        Optional<PtMatching> matching = ptMatchingRepository.findById(reviewRequestDto.getMatch_id());
        if(matching.isEmpty()) return false;
        else {
            Customer ptCustomer = matching.get().getPtCustomer();
            reviewRepository.save(Review.builder()
                    .body(reviewRequestDto.getBody())
                    .point(reviewRequestDto.getPoint())
                    .trainer(matching.get().getPtTrainer())
                    .customer(ptCustomer)
                    .build());
            return true;
        }
    }

    public List<ReviewResponseDto> load_review_by_post(Long post_id) {
        Optional<PtPost> ptPost = ptPostRepository.findById(post_id); // 트레이너 구해서 관련된 post 가져오기
        List<Review> reviewList = ptPost.get().getPtTrainer().getReview();
        List<ReviewResponseDto> reviewResponseDtoList = new ArrayList<>();
        for (Review review : reviewList) {
            reviewResponseDtoList.add(review.toResponsePostDto());
        }
        return reviewResponseDtoList;
    }

}
