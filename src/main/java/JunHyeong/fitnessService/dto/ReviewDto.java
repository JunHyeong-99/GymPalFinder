package JunHyeong.fitnessService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
    private String body;
    private int point;
    private Long match_id;


}
