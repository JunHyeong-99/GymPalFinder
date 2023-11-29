package JunHyeong.fitnessService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerMatchDto {
    private String user_id;
    private String user_pwd;
    private Long post_id;
}