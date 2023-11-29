package JunHyeong.fitnessService.service;


import JunHyeong.fitnessService.dto.PartnerPostDetailDto;
import JunHyeong.fitnessService.dto.PartnerPostResponseDto;
import JunHyeong.fitnessService.dto.PtPostDetailDto;
import JunHyeong.fitnessService.dto.PtPostResponseDto;
import JunHyeong.fitnessService.entity.PartnerPost;
import JunHyeong.fitnessService.entity.PtPost;
import JunHyeong.fitnessService.repository.PartnerPostRepository;
import JunHyeong.fitnessService.repository.PtPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PtPostRepository ptPostRepository;
    private final PartnerPostRepository partnerPostRepository;

    public List<PtPostResponseDto> getResponsePtPost() {
        List<PtPostResponseDto> post_list = new ArrayList<>();
        List<PtPost> AllPtPost = ptPostRepository.findAll();
        for (PtPost ptPost : AllPtPost) {
            post_list.add(ptPost.toResponsePostDto());
        }
        return post_list;
    }

    public PtPostDetailDto getPtPostDetail(Long post_id) throws Exception {
        Optional<PtPost> ptPost = ptPostRepository.findById(post_id);
        if(ptPost.isEmpty()) throw new Exception("post가 없습니다.");
        return PtPostDetailDto.builder()
                .title(ptPost.get().getTitle())
                .price(ptPost.get().getPrice())
                .description(ptPost.get().getDescription())
                .phone_number(ptPost.get().getPtTrainer().getPhoneNumber())
                .build();
    }

    public List<PartnerPostResponseDto> getResponsePartnerPost() {
        List<PartnerPostResponseDto> post_list = new ArrayList<>();
        List<PartnerPost> AllPartnerPost = partnerPostRepository.findAll();
        for (PartnerPost partnerPost : AllPartnerPost) {
            post_list.add(partnerPost.toResponsePostDto());
        }
        return post_list;
    }

    public PartnerPostDetailDto getPartnerPostDetail(Long post_id) throws Exception {
        Optional<PartnerPost> partnerPost = partnerPostRepository.findById(post_id);
        if(partnerPost.isEmpty()) throw new Exception("post가 없습니다.");
        return PartnerPostDetailDto.builder()
                .title(partnerPost.get().getTitle())
                .weight_sum(partnerPost.get().getWeight_sum())
                .description(partnerPost.get().getDescription())
                .phone_number(partnerPost.get().getRegisterUser().getPhoneNumber())
                .build();
    }
}
