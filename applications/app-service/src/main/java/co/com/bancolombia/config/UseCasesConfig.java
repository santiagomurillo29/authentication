package co.com.bancolombia.config;

import co.com.bancolombia.model.login.gateways.LoginPersistencePort;
import co.com.bancolombia.model.user.gateways.RolePersistencePort;
import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.usecase.login.usecase.LoginUseCase;
import co.com.bancolombia.usecase.login.usecase.api.LoginServicePort;
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
        private final LoginPersistencePort loginPersistencePort;

        @Bean
        public UserServicePort userServicePort() { return new UserUseCase(userPersistencePort, rolePersistencePort); }

        @Bean
        LoginServicePort loginServicePort() { return new LoginUseCase(userPersistencePort, loginPersistencePort);
        }
}
