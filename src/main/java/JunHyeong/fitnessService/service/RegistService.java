package JunHyeong.fitnessService.service;

import JunHyeong.fitnessService.dto.PartnerPostDto;
import JunHyeong.fitnessService.dto.PtPostDto;
import JunHyeong.fitnessService.entity.PartnerPost;
import JunHyeong.fitnessService.entity.PartnerUser;
import JunHyeong.fitnessService.entity.PtPost;
import JunHyeong.fitnessService.entity.Trainer;
import JunHyeong.fitnessService.repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class RegistService {

    private final CustomerRepository customerRepository;
    private final TrainerRepository trainerRepository;
    private final PartnerUserRepository partnerUserRepository;
    private final PtPostRepository ptPostRepository;
    private final PartnerPostRepository partnerPostRepository;

    public boolean pt_post_add(PtPostDto ptPostDto) {
        Optional<Trainer> trainer = trainerRepository.findByEmail(ptPostDto.getEmail());

        if (trainer.isEmpty()) return false;

        PtPost ptPost = PtPost.builder()
                        .title(ptPostDto.getTitle())
                        .price(ptPostDto.getPrice())
                        .description(ptPostDto.getDescription())
                        .ptTrainer(trainer.get())
                        .build();
        ptPostRepository.save(ptPost);
        trainer.get().setMyPtPost(ptPost);
        return true;
    }

    public boolean partner_post_add(PartnerPostDto partnerPostDto) {
        Optional<PartnerUser> partnerUser = partnerUserRepository.findByEmail(partnerPostDto.getEmail());

        if (partnerUser.isEmpty()) return false;

        PartnerPost partnerPost = PartnerPost.builder()
                .title(partnerPostDto.getTitle())
                .weight_sum(partnerPostDto.getWeight_sum())
                .description(partnerPostDto.getDescription())
                .registerUser(partnerUser.get())
                .build();
        partnerPostRepository.save(partnerPost);
        partnerUser.get().setPartnerPost(partnerPost);
        return true;
    }
}
