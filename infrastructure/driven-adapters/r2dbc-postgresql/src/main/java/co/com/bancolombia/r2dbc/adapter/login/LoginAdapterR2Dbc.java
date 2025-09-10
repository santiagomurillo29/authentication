package co.com.bancolombia.r2dbc.adapter.login;

import co.com.bancolombia.authsecurity.jwt.provider.JwtProvider;
import co.com.bancolombia.authsecurity.model.CustomUserDetails;
import co.com.bancolombia.model.login.gateways.LoginPersistencePort;
import co.com.bancolombia.model.login.model.LoginRequest;
import co.com.bancolombia.model.login.model.LoginResponse;
import co.com.bancolombia.model.globalmessage.GlobalMessage;
import co.com.bancolombia.r2dbc.adapter.user.UserAuthAdapter;
import co.com.bancolombia.r2dbc.exception.DataBaseException;
import co.com.bancolombia.r2dbc.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class LoginAdapterR2Dbc implements LoginPersistencePort {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        return userRepository.findUserWithCredentialsByEmailCustom(loginRequest.getEmail())
                .switchIfEmpty(Mono.error(new DataBaseException(GlobalMessage.BAD_CREDENTIALS)))
                .filter(userWithPassword -> passwordEncoder.matches(loginRequest.getPassword(), userWithPassword.getPasswordHash()))
                .map(userWithPassword -> {
                    UserAuthAdapter userAuth = new UserAuthAdapter(
                            userWithPassword.getUserEntity(),
                            userWithPassword.getPasswordHash(),
                            userWithPassword.getRoleName()
                    );

                    CustomUserDetails userDetails = new CustomUserDetails(userAuth);

                    String token = jwtProvider.generateToken(userDetails);
                    return new LoginResponse(token, jwtProvider.getExpirationMillis());
                });
    }
}
