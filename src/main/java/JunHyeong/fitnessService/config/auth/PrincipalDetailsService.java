package JunHyeong.fitnessService.config.auth;

import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PartnerUser;
import JunHyeong.fitnessService.entity.Trainer;
import JunHyeong.fitnessService.repository.CustomerRepository;
import JunHyeong.fitnessService.repository.PartnerUserRepository;
import JunHyeong.fitnessService.repository.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;
    private PartnerUserRepository partnerUserRepository;
    private TrainerRepository trainerRepository;



    // 시큐리티 session = Authentication(내부 UserDetails)에 들어간다.
    @Override //username form에서 받은 변수로 잘 받아야 값이 잘 들어온다.
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        Optional<Trainer> trainer = trainerRepository.findByEmail(email);
        Optional<PartnerUser> partnerUser = partnerUserRepository.findByEmail(email);

        if(customer.isPresent()) {
            return new PrincipalDetails(customer.get());
        } else if (trainer.isPresent()) {
            return new PrincipalDetails(trainer.get());
        } else if (partnerUser.isPresent()) {
            return new PrincipalDetails(partnerUser.get());
        }
        else {
            return null;
        }
    }
}