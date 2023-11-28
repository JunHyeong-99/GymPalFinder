package JunHyeong.fitnessService.service;


import JunHyeong.fitnessService.dto.PtPostDetailDto;
import JunHyeong.fitnessService.dto.PtPostResponseDto;
import JunHyeong.fitnessService.entity.PtPost;
import JunHyeong.fitnessService.repository.PtPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PtPostRepository ptPostRepository;
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
}
