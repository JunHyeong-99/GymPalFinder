package JunHyeong.fitnessService.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PtPostDto {
    private String email;
    private String title;
    private String description;
    private int price;
}
