package co.com.bancolombia.r2dbc.repository.user;

import co.com.bancolombia.r2dbc.entity.user.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long>, UserRepositoryCustom {
    Mono<Boolean> existsByEmailUser(String email);
    Mono<UserEntity> findByEmailUser(String email);
}
