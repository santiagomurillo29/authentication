package co.com.bancolombia.model.user.gateways.user;

import co.com.bancolombia.model.user.model.user.UserModel;
import reactor.core.publisher.Mono;

public interface UserPersistencePort {
    Mono<UserModel> saveUser(UserModel userModel);
    Mono<Boolean> existsUserByEmail(String emailUser);
    Mono<UserModel> findUserByEmail(String email);
}
