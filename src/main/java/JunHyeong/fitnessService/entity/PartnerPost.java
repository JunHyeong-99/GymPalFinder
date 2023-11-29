package JunHyeong.fitnessService.entity;

import JunHyeong.fitnessService.dto.PartnerPostResponseDto;
import JunHyeong.fitnessService.dto.PtPostResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PartnerPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne(mappedBy = "partnerPost")
    private PartnerUser registerUser;

    @Column
    private String title;

    @Column
    private String description;

    @Column //3대 중량
    private int weight_sum;


    public PartnerPostResponseDto toResponsePostDto() {
        return PartnerPostResponseDto.builder()
                .post_id(this.getId())
                .weight_sum(this.getWeight_sum())
                .title(this.getTitle())
                .build();
    }

}

