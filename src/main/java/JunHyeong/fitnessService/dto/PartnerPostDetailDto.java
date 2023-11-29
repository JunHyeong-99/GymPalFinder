package JunHyeong.fitnessService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerPostDetailDto {
    private String title;
    private String description;
    private int weight_sum;
    private String phone_number;
}

