package JunHyeong.fitnessService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignDto {
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
}
