package JunHyeong.fitnessService.service;


import JunHyeong.fitnessService.dto.ReviewDto;
import JunHyeong.fitnessService.entity.PtMatching;
import JunHyeong.fitnessService.entity.Review;
import JunHyeong.fitnessService.repository.PtMatchingRepository;
import JunHyeong.fitnessService.repository.ReviewRepository;
import JunHyeong.fitnessService.repository.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PtMatchingRepository ptMatchingRepository;
    public boolean create_review(ReviewDto reviewDto) {
        Optional<PtMatching> matching = ptMatchingRepository.findById(reviewDto.getMatch_id());
        if(matching.isEmpty()) return false;
        else {
            reviewRepository.save(Review.builder()
                    .body(reviewDto.getBody())
                    .point(reviewDto.getPoint())
                    .trainer(matching.get().getPtTrainer())
                    .build());
            return true;
        }
    }
}
