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

import java.util.Objects;
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

    public String matchPt(PtMatchDto ptMatchDto) {
        if(!authService.isLogin(LoginDto.builder() // 로그인 실패한 경우
                        .email(ptMatchDto.getUser_id())
                .password(ptMatchDto.getUser_pwd()).build())){
            return "아이디, 비밀번호가 일치하지 않습니다.";
        }
        else { // customer 구하고 trainer 구하고 matching에 넣기
            Optional<Customer> customer = customerRepository.findByEmail(ptMatchDto.getUser_id());
            Optional<PtPost> ptPost = ptPostRepository.findById(ptMatchDto.getPost_id());
            if(ptPost.isEmpty() || customer.isEmpty()) return "존재하지 않는 post입니다.";
            Optional<PtMatching> originMatch = ptMatchingRepository.findByTrainerAndCoustomer(ptPost.get().getPtTrainer(), customer.get());
            if(originMatch.isPresent()) {
                return "이미 등록된 match입니다.";
            }
            ptMatchingRepository.save(PtMatching.builder()
                    .ptCustomer(customer.get())
                    .ptTrainer(ptPost.get().getPtTrainer())
                    .build());
            return "pt등록에 성공하셨습니다.";
        }
    }
    public String matchPartner(PartnerMatchDto partnerMatchDto) {
        if(!authService.isLogin(LoginDto.builder() // 로그인 실패한 경우
                .email(partnerMatchDto.getUser_id())
                .password(partnerMatchDto.getUser_pwd()).build())){
            return "아이디, 비밀번호가 일치하지 않습니다.";
        }
        else { // customer 구하고 trainer 구하고 matching에 넣기
            Optional<PartnerUser> partnerUser = partnerUserRepository.findByEmail(partnerMatchDto.getUser_id());
            Optional<PartnerPost> partnerPost = partnerPostRepository.findById(partnerMatchDto.getPost_id());
            if(partnerUser.isEmpty() || partnerPost.isEmpty()) return "post가 존재하지 않습니다.";
            PartnerUser registerUser = partnerPost.get().getRegisterUser();
            if (Objects.equals(partnerUser.get().getId(), registerUser.getId())) {
                return "자기 자신은 match하실 수 없습니다.";
            }
            Optional<PartnerMatching> originMatch1 = partnerMatchingRepository.findByUser1AndUser2(partnerUser.get().getId(), registerUser.getId());
            Optional<PartnerMatching> originMatch2 = partnerMatchingRepository.findByUser1AndUser2(registerUser.getId(), partnerUser.get().getId());
            if (originMatch1.isPresent() || originMatch2.isPresent()) {
                return "이미 등록된 match입니다.";
            }
            // PartnerMatching은 2개를 만들어 각 user에 할당을 해주어야 한다.
            partnerMatchingRepository.save(PartnerMatching.builder()
                    .user1(registerUser.getId())
                    .user2(partnerUser.get().getId())
                    .build());
            return "매칭이 등록되었습니다.";
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
