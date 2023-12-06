package JunHyeong.fitnessService.service;


import JunHyeong.fitnessService.dto.MypageDto;
import JunHyeong.fitnessService.dto.ReviewRequestDto;
import JunHyeong.fitnessService.entity.*;
import JunHyeong.fitnessService.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {
    // 파이너 유저면 내 파트너 유저 목록
    private final TrainerRepository trainerRepository;
    private final CustomerRepository customerRepository;
    private final PartnerUserRepository partnerUserRepository;
    private final PartnerMatchingRepository partnerMatchingRepository;
    private final ReviewRepository reviewRepository;
    public List<MypageDto> trainer_mypage(String email) {
        List<MypageDto> mypageList = new ArrayList<>();
        Optional<Trainer> trainer = trainerRepository.findByEmail(email);
        List<PtMatching> myPtList = trainer.get().getMyPtList();
        for (PtMatching myPt : myPtList) {
            mypageList.add(MypageDto.builder()
                            .match_id(myPt.getId())
                            .match_username(myPt.getPtCustomer().getName())
                            .phone_number(myPt.getPtCustomer().getPhoneNumber())
                    .build());
        }
        return mypageList;
    }

    public List<MypageDto> customer_mypage(String email) {
        List<MypageDto> mypageList = new ArrayList<>();
        Optional<Customer> customer = customerRepository.findByEmail(email);
        List<PtMatching> myTrainerList = customer.get().getMyTrainerList();
        for (PtMatching myPt : myTrainerList) {
            mypageList.add(MypageDto.builder()
                    .match_id(myPt.getId())
                    .match_username(myPt.getPtTrainer().getName())
                    .phone_number(myPt.getPtTrainer().getPhoneNumber())
                    .build());
        }
        return mypageList;
    }

    public List<MypageDto> partner_mypage(String email) {
        List<MypageDto> mypageList = new ArrayList<>();
        Optional<PartnerUser> myUser = partnerUserRepository.findByEmail(email);
        List<PartnerMatching> matchingList = new ArrayList<>();

        matchingList.addAll(partnerMatchingRepository.findAllByUser1(myUser.get().getId()));
        matchingList.addAll(partnerMatchingRepository.findAllByUser2(myUser.get().getId()));
        for (PartnerMatching myMatching : matchingList) {
            Optional<PartnerUser> partner = partnerUserRepository.findById(myMatching.getUser2());
            mypageList.add(MypageDto.builder()
                    .match_id(myMatching.getId())
                    .match_username(partner.get().getName())
                    .phone_number(partner.get().getPhoneNumber())
                    .build());
        }
        return mypageList;
    }
}
