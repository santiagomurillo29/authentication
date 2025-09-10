package co.com.bancolombia.usecase.login.usecase;

import co.com.bancolombia.model.login.gateways.LoginPersistencePort;
import co.com.bancolombia.model.login.model.LoginRequest;
import co.com.bancolombia.model.login.model.LoginResponse;
import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.model.globalmessage.GlobalMessage;
import co.com.bancolombia.usecase.login.usecase.api.LoginServicePort;
import co.com.bancolombia.usecase.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class LoginUseCase implements LoginServicePort {

    private final UserPersistencePort userPersistencePort;
    private final LoginPersistencePort loginPersistencePort;

    @Override
    public Mono<LoginResponse> login(LoginRequest loginRequest) {
        return userPersistencePort.findUserByEmail(loginRequest.getEmail())
                .switchIfEmpty(Mono.error(new BusinessException(GlobalMessage.NOT_FOUND_EMAIL)))
                .flatMap(auth -> loginPersistencePort.login(loginRequest));
    }
}
