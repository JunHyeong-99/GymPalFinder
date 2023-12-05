package JunHyeong.fitnessService.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResignDto {

    private String email;
    private String password;
    private String userType;
}
