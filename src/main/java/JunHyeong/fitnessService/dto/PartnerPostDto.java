package JunHyeong.fitnessService.dto;


import JunHyeong.fitnessService.entity.PartnerUser;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartnerPostDto {
    private String email;

    private String title;

    private String description;

    private int weight_sum;

    private String want_gender;
}
