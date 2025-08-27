package co.com.bancolombia.config;

import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.r2dbc.adapter.UserAdapterR2dbc;
import co.com.bancolombia.r2dbc.health.R2dbcSafeExecutor;
import co.com.bancolombia.r2dbc.mapper.UserMapperR2dbc;
import co.com.bancolombia.r2dbc.repository.UserRepository;
import co.com.bancolombia.usecase.user.usecase.UserUseCase;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCasesConfig {

        private final R2dbcSafeExecutor r2dbcSafeExecutor;
        private final UserRepository userRepository;
        private final UserMapperR2dbc userMapperR2dbc;

        @Bean
        public UserPersistencePort userPersistencePort(){
                return new UserAdapterR2dbc(
                        userRepository,
                        userMapperR2dbc,
                        r2dbcSafeExecutor
                );
        }

        @Bean
        public UserServicePort userServicePort() { return new UserUseCase(userPersistencePort()); }
}
