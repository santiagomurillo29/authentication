package co.com.bancolombia.r2dbc.adapter;

import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.r2dbc.health.R2dbcSafeExecutor;
import co.com.bancolombia.r2dbc.mapper.UserMapperR2dbc;
import co.com.bancolombia.r2dbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserAdapterR2dbc implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserMapperR2dbc userMapperR2dbc;
    private final R2dbcSafeExecutor r2dbcSafeExecutor;

    @Override
    public Mono<UserModel> saveUser(UserModel userModel) {
        return r2dbcSafeExecutor.executeMono(() ->
                userRepository.save(userMapperR2dbc.toEntityUser(userModel))
                        .doOnSubscribe(sub -> log.info("Saving user: {}", userModel))
                        .map(userMapperR2dbc::toModelUser)
                        .doOnSuccess(saved -> log.info("user saved successfully: {}", saved))
                        .doOnError(e -> log.error("Error saving user: {}", e.getMessage()))
                );
    }

    @Override
    public Mono<Boolean> existsUserByEmail(String emailUser) {
        return r2dbcSafeExecutor.executeMono(() ->
                userRepository.existsByEmailUser(emailUser)
                        .doOnSubscribe(sub -> log.info("Checking existence of email: {}", emailUser))
                        .doOnSuccess(found -> log.info("The email does exist: {}", found))
                        .doOnError(e -> log.error("Error checking if the email exists by name {}: {}", emailUser, e.getMessage()))
        );
    }
}
