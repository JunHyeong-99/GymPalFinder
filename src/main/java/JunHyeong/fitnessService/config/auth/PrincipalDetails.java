package JunHyeong.fitnessService.config.auth;

import JunHyeong.fitnessService.entity.Customer;
import JunHyeong.fitnessService.entity.PartnerUser;
import JunHyeong.fitnessService.entity.Trainer;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    private Customer customer;
    private PartnerUser partnerUser;
    private Trainer trainer;



    public PrincipalDetails(Customer customer) {
        super();
        this.customer = customer;
    }
    public PrincipalDetails(PartnerUser partnerUser) {
        super();
        this.partnerUser = partnerUser;
    }

    public PrincipalDetails(Trainer trainer) {
        super();
        this.trainer = trainer;
    }

    private String checkUserType ()  {
        if (customer != null) return "Customer";
        else if (partnerUser != null) return "PartnerUser";
        else if (trainer != null) return "Trainer";
        else {
            return "null";
        }
    }
    @Override
    public String getPassword() {
        String userType = checkUserType();
        if (userType.equals("Customer")) {
            return customer.getPassword();
        }
        else if(userType.equals("PartnerUser")) {
            return partnerUser.getPassword();
        }
        else   {
            return trainer.getPassword();
        }

    }

    @Override
    public String getUsername() {
        String userType = checkUserType();
        if (userType.equals("Customer")) {
            return customer.getEmail();
        }
        else if(userType.equals("PartnerUser")) {
            return partnerUser.getEmail();
        }
        else {
            return trainer.getEmail();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 기간 만료 되었는지 확인 ex 최근 로그인이 1년이상 되었으면  휴먼 계정으로 만들려면 계산해서 false로 하면된다.
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //해당 user의 권한을 리턴하는 곳이다.  리턴 타입이 Collection이기 때문에 user.getRole() 못함.
        Collection<GrantedAuthority> collet = new ArrayList<GrantedAuthority>();
        if (customer != null) {
            collet.add(()->{ return customer.getRole();});
        }
        else if(trainer != null) {
            collet.add(()->{ return trainer.getRole();});
        }
        else {
            collet.add(()->{ return partnerUser.getRole();});
        }
        return collet;
    }



}