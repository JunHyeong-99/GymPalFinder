package JunHyeong.fitnessService.service;


import JunHyeong.fitnessService.dto.LoginDto;
import JunHyeong.fitnessService.dto.PartnerMatchDto;
import JunHyeong.fitnessService.dto.PtMatchDto;
import JunHyeong.fitnessService.dto.PtPostResponseDto;
import JunHyeong.fitnessService.entity.*;
import JunHyeong.fitnessService.repository.*;
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
    private final PartnerUserRepository partnerUserRepository;
    private final PartnerPostRepository partnerPostRepository;
    private final PartnerMatchingRepository partnerMatchingRepository;

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
    public boolean matchPartner(PartnerMatchDto partnerMatchDto) {
        if(!authService.isLogin(LoginDto.builder() // 로그인 실패한 경우
                .email(partnerMatchDto.getUser_id())
                .password(partnerMatchDto.getUser_pwd()).build())){
            return false;
        }
        else { // customer 구하고 trainer 구하고 matching에 넣기
            Optional<PartnerUser> partnerUser = partnerUserRepository.findByEmail(partnerMatchDto.getUser_id());
            Optional<PartnerPost> partnerPost = partnerPostRepository.findById(partnerMatchDto.getPost_id());

            // PartnerMatching은 2개를 만들어 각 user에 할당을 해주어야 한다.
            partnerMatchingRepository.save(PartnerMatching.builder()
                    .user1(partnerPost.get().getRegisterUser().getId())
                    .user2(partnerUser.get().getId())
                    .build());
            return true;
        }
    }
    
    public boolean deletePartner(Long match_id) {
        Optional<PartnerMatching> match = partnerMatchingRepository.findById(match_id);
        if (match.isPresent()) {
            partnerMatchingRepository.delete(match.get());
            return true;
        }
        else {
            return false;
        }

    }

    public boolean deletePt(Long match_id) {
        Optional<PtMatching> pt = ptMatchingRepository.findById(match_id);
        if(pt.isPresent()) {
            ptMatchingRepository.delete(pt.get());
            return true;
        }
        else {
            return false;
        }
    }
}
