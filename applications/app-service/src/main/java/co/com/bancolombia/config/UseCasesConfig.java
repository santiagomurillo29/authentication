package co.com.bancolombia.config;

import co.com.bancolombia.model.user.gateways.RolePersistencePort;
import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.r2dbc.adapter.RoleAdapterR2dbc;
import co.com.bancolombia.r2dbc.adapter.UserAdapterR2dbc;
import co.com.bancolombia.r2dbc.health.R2dbcSafeExecutor;
import co.com.bancolombia.r2dbc.mapper.RoleMapperR2dbc;
import co.com.bancolombia.r2dbc.mapper.UserMapperR2dbc;
import co.com.bancolombia.r2dbc.repository.RoleRepository;
import co.com.bancolombia.r2dbc.repository.UserRepository;
import co.com.bancolombia.usecase.user.usecase.UserUseCase;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCasesConfig {

        private final UserPersistencePort userPersistencePort;
        private final RolePersistencePort rolePersistencePort;


        @Bean
        public UserServicePort userServicePort() { return new UserUseCase(userPersistencePort, rolePersistencePort); }
}
