package co.com.bancolombia.authsecurity.model;

import co.com.bancolombia.authsecurity.model.auth.UserAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final transient UserAuth userAuth;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAuth.getRoles().stream()
                .map(role -> (GrantedAuthority) () -> "ROLE_" + role)
                .toList();
    }

    @Override
    public String getPassword() {
        return userAuth.getPassword();
    }

    @Override
    public String getUsername() {
        return userAuth.getUsername();
    }
}
