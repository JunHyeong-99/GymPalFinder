package JunHyeong.fitnessService.service;


import JunHyeong.fitnessService.dto.LoginDto;
import JunHyeong.fitnessService.dto.PtMatchDto;
import JunHyeong.fitnessService.dto.PtPostResponseDto;
import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PtMatching;
import JunHyeong.fitnessService.entity.PtPost;
import JunHyeong.fitnessService.repository.CustomerRepository;
import JunHyeong.fitnessService.repository.PtMatchingRepository;
import JunHyeong.fitnessService.repository.PtPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingService {

    private final CustomerRepository customerRepository;
    private final AuthService authService;
    private final PtMatchingRepository ptMatchingRepository;
    private final PtPostRepository ptPostRepository;

    public boolean matchPt(PtMatchDto ptMatchDto) {
        if(!authService.isLogin(LoginDto.builder() // 로그인 실패한 경우
                        .email(ptMatchDto.getUser_id())
                .password(ptMatchDto.getUser_pwd()).build())){
            return false;
        }
        else { // customer 구하고 trainer 구하고 matching에 넣기
            Optional<Customer> customer = customerRepository.findByEmail(ptMatchDto.getUser_id());
            Optional<PtPost> ptPost = ptPostRepository.findById(ptMatchDto.getPost_id());

            ptMatchingRepository.save(PtMatching.builder()
                    .ptCustomer(customer.get())
                    .ptTrainer(ptPost.get().getPtTrainer())
                    .build());
            return true;
        }
    }
}
