package co.com.bancolombia.r2dbc.repository.user;

import co.com.bancolombia.r2dbc.entity.user.UserWithPassword;
import reactor.core.publisher.Mono;

public interface UserRepositoryCustom {
    Mono<UserWithPassword> findUserWithCredentialsByEmailCustom(String email);
}
