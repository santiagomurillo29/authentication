package co.com.bancolombia.model.login.gateways;

import co.com.bancolombia.model.login.model.LoginRequest;
import co.com.bancolombia.model.login.model.LoginResponse;
import reactor.core.publisher.Mono;

public interface LoginPersistencePort {
    Mono<LoginResponse> login(LoginRequest loginRequest);
}
