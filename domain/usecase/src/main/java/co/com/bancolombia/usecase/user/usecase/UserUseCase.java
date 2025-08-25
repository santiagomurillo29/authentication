package co.com.bancolombia.usecase.user.usecase;

import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.model.user.globalmessage.GlobalMessage;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.usecase.user.exception.BusinessException;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase implements UserServicePort {

    private final UserPersistencePort userPersistencePort;

    @Override
    public Mono<UserModel> createUser(UserModel userModel) {
        return userPersistencePort.existsUserByEmail(userModel.getEmailUser())
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(new BusinessException(GlobalMessage.BAD_PARAMETER)))
                .flatMap(b -> userPersistencePort.saveUser(userModel));
    }
}
