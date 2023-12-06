package JunHyeong.fitnessService.dto;

import JunHyeong.fitnessService.entity.Customer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequestDto {
    private String body;
    private int point;
    private Long match_id;
    private Customer customer;


}
