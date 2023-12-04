package JunHyeong.fitnessService.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ReviewListDto {
    private List<ReviewResponseDto> reviewList;
}
