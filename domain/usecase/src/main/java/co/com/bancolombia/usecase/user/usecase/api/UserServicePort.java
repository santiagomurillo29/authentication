package co.com.bancolombia.usecase.user.usecase.api;

import co.com.bancolombia.model.user.model.UserModel;
import reactor.core.publisher.Mono;

public interface UserServicePort {
    Mono<UserModel> createUser(UserModel userModel);
    Mono<UserModel> findUserByEmail(String emailUser);
}
