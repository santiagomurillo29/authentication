package co.com.bancolombia.model.login.gateways;

import co.com.bancolombia.model.login.model.LoginRequest;
import co.com.bancolombia.model.login.model.LoginResponse;
import co.com.bancolombia.model.user.model.UserModel;
import reactor.core.publisher.Mono;

public interface LoginPersistencePort {
    Mono<LoginResponse> login(LoginRequest loginRequest);
}
