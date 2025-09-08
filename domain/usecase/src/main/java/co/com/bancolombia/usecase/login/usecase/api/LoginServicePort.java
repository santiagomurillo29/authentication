package co.com.bancolombia.usecase.auth.usecase.api;

import co.com.bancolombia.model.login.model.LoginRequest;
import co.com.bancolombia.model.login.model.LoginResponse;
import reactor.core.publisher.Mono;

public interface LoginServicePort {
    Mono<LoginResponse> login(LoginRequest loginRequest);
}
