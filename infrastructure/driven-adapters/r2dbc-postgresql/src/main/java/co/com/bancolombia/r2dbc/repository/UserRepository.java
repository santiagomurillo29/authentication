package co.com.bancolombia.r2dbc.repository;

import co.com.bancolombia.r2dbc.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, String> {
    Mono<Boolean> existsByEmailUser(String email);
}
