package JunHyeong.fitnessService.service;

import JunHyeong.fitnessService.dto.LoginDto;
import JunHyeong.fitnessService.dto.SignDto;
import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PartnerUser;
import JunHyeong.fitnessService.entity.Trainer;
import JunHyeong.fitnessService.repository.CustomerRepository;
import JunHyeong.fitnessService.repository.PartnerUserRepository;
import JunHyeong.fitnessService.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PartnerUserRepository partnerUserRepository;
    private final TrainerRepository trainerRepository;

    public boolean isLogin(LoginDto loginDto) { // 로그인 체크 함수
        Optional<Customer> customer = customerRepository.findByEmail(loginDto.getEmail());
        Optional<PartnerUser> partnerUser = partnerUserRepository.findByEmail(loginDto.getEmail());
        Optional<Trainer> trainer = trainerRepository.findByEmail(loginDto.getEmail());
        if(customer.isPresent()) {
            return Objects.equals(customer.get().getPassword(), loginDto.getPassword());
        }
        else if (partnerUser.isPresent()) {
            return Objects.equals(partnerUser.get().getPassword(), loginDto.getPassword());
        }
        else if (trainer.isPresent()) {
            return Objects.equals(trainer.get().getPassword(), loginDto.getPassword());
        }
        else return false; // 아이디가 잘못된 경우
    }

    public String trainer_sign(SignDto signDto) {
        Optional<Trainer> trainer = trainerRepository.findByEmail(signDto.getEmail());
        if (trainer.isPresent()) {
            return "이미 사용중인 이메일 입니다. 다른 이메일을 이용해 주세요";
        }
        Trainer new_trainer = Trainer.builder()
                .email(signDto.getEmail())
                .password(signDto.getPassword())
                .phoneNumber(signDto.getPhoneNumber())
                .name(signDto.getName())
                .role("ROLE_TRAINER")
                .build();
        trainerRepository.save(new_trainer);

        return "회원 가입에 성공하셨습니다.";
    }

    public String customer_sign(SignDto signDto) {
        Optional<Customer> customer = customerRepository.findByEmail(signDto.getEmail());
        if (customer.isPresent()) {
            return "이미 사용중인 이메일 입니다. 다른 이메일을 이용해 주세요";
        }
        Customer new_customer = Customer.builder()
                .email(signDto.getEmail())
                .password(signDto.getPassword())
                .phoneNumber(signDto.getPhoneNumber())
                .name(signDto.getName())
                .role("ROLE_CUSTOMER")
                .build();
        customerRepository.save(new_customer);

        return "회원 가입에 성공하셨습니다.";
    }

    public String partnerUser_sign(SignDto signDto) {
        Optional<PartnerUser> partnerUser = partnerUserRepository.findByEmail(signDto.getEmail());
        if (partnerUser.isPresent()) {
            return "이미 사용중인 이메일 입니다. 다른 이메일을 이용해 주세요";
        }
        PartnerUser new_partnerUser = PartnerUser.builder()
                .email(signDto.getEmail())
                .password(signDto.getPassword())
                .phoneNumber(signDto.getPhoneNumber())
                .name(signDto.getName())
                .role("ROLE_PARTNERUSER")
                .build();
        partnerUserRepository.save(new_partnerUser);

        return "회원 가입에 성공하셨습니다.";
    }
}
