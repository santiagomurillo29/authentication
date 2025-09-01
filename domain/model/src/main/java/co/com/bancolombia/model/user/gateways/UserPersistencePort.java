package co.com.bancolombia.model.user.gateways;

import co.com.bancolombia.model.user.model.RoleModel;
import co.com.bancolombia.model.user.model.UserModel;
import reactor.core.publisher.Mono;

public interface UserPersistencePort {
    Mono<UserModel> saveUser(UserModel userModel);
    Mono<Boolean> existsUserByEmail(String emailUser);
    Mono<UserModel> findUserByEmail(String email);
}
