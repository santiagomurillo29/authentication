package co.com.bancolombia.usecase.user.usecase;

import co.com.bancolombia.model.globalmessage.GlobalMessage;
import co.com.bancolombia.model.user.gateways.RolePersistencePort;
import co.com.bancolombia.model.user.gateways.TokenAuthSecurityPort;
import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.usecase.exception.BusinessException;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import reactor.core.publisher.Mono;

public class UserUseCase implements UserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final RolePersistencePort rolePersistencePort;
    private final TokenAuthSecurityPort tokenAuthSecurityPort;

    public UserUseCase(UserPersistencePort userPersistencePort, RolePersistencePort rolePersistencePort, TokenAuthSecurityPort tokenAuthSecurityPort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.tokenAuthSecurityPort = tokenAuthSecurityPort;
    }

    @Override
    public Mono<UserModel> createUser(UserModel userModel, String token) {
        return tokenAuthSecurityPort.getSubject(token)
                .flatMap(subject ->
                                userPersistencePort.existsUserByEmail(userModel.getEmailUser())
                                        .filter(exists -> !exists)
                                        .switchIfEmpty(Mono.error(new BusinessException(GlobalMessage.BAD_PARAMETER)))
                                        .flatMap(b ->
                                                rolePersistencePort.findRoleById(userModel.getRole().getIdRole())
                                                        .switchIfEmpty(Mono.error(new BusinessException(GlobalMessage.NOT_FOUND_ROLE)))
                                                        .flatMap(role -> {
                                                            userModel.setRole(role);
                                                            return userPersistencePort.saveUser(userModel);
                                                        })
                                        )
                );
    }

    @Override
    public Mono<UserModel> findUserByEmail(String emailUser, String token) {
        return tokenAuthSecurityPort.getSubject(token)
                .flatMap(subject ->
                                userPersistencePort.existsUserByEmail(emailUser)
                                        .filter(Boolean::booleanValue)
                                        .switchIfEmpty(Mono.error(new BusinessException(GlobalMessage.NOT_FOUND_USER)))
                                        .flatMap(valid -> userPersistencePort.findUserByEmail(emailUser))
                );

    }
}
