package co.com.bancolombia.usecase.user.usecase;

import co.com.bancolombia.model.user.gateways.RolePersistencePort;
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
    private final RolePersistencePort rolePersistencePort;


    @Override
    public Mono<UserModel> createUser(UserModel userModel) {
        return userPersistencePort.existsUserByEmail(userModel.getEmailUser())
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(new BusinessException(GlobalMessage.BAD_PARAMETER)))
                .flatMap(b ->
                        rolePersistencePort.findRoleById(userModel.getRole().getIdRole())
                                .switchIfEmpty(Mono.error(new BusinessException(GlobalMessage.NOT_FOUND_ROLE)))
                                .flatMap(role -> {
                                    userModel.setRole(role);
                                    return userPersistencePort.saveUser(userModel);
                                })
                );
    }

    @Override
    public Mono<UserModel> findUserByEmail(String emailUser) {
        return userPersistencePort.existsUserByEmail(emailUser)
                .filter(Boolean::booleanValue)
                .switchIfEmpty(Mono.error(new BusinessException(GlobalMessage.NOT_FOUND_USER)))
                .flatMap(valid -> userPersistencePort.findUserByEmail(emailUser));
    }
}
