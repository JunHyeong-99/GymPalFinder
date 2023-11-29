package JunHyeong.fitnessService.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MypageDto {
    private Long match_id;
    private String match_username;
    private String phone_number;
}
