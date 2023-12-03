package JunHyeong.fitnessService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerPostResponseDto {

    private Long post_id;
    private String title;
    private int weight_sum;
    private String want_gender;
}
