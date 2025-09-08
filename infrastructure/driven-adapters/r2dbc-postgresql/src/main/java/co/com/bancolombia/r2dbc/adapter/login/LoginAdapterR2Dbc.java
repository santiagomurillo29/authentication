package co.com.bancolombia.r2dbc.adapter.auth;

import co.com.bancolombia.authsecurity.jwt.provider.JwtProvider;
import co.com.bancolombia.model.auth.gateways.AuthPersistencePort;
import co.com.bancolombia.model.auth.model.AuthRequest;
import co.com.bancolombia.model.auth.model.AuthResponse;
import co.com.bancolombia.model.user.globalmessage.GlobalMessage;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.r2dbc.entity.UserEntity;
import co.com.bancolombia.r2dbc.exception.DataBaseException;
import co.com.bancolombia.r2dbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class AuthAdapterR2dbc implements AuthPersistencePort {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Mono<AuthResponse> login(AuthRequest authRequest) {
        return userRepository.findByEmailUser(authRequest.getEmail())
                .filter(user -> passwordEncoder.matches(authRequest.getPassword(), authRequest.getPassword()))
                .map(user -> new AuthResponse(jwtProvider.generateToken(user)))
                .switchIfEmpty(Mono.error(new DataBaseException(GlobalMessage.BAD_CREDENTIALS)));
    }

    @Override
    public Mono<AuthResponse> generateToken(UserModel userModel) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
