package JunHyeong.fitnessService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponseDto {
    private Long review_id;
    private String body;
    private int point;
}
