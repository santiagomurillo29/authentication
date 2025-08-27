package co.com.bancolombia.config;

import co.com.bancolombia.r2dbc.health.R2dbcSafeExecutor;
import co.com.bancolombia.r2dbc.mapper.UserMapperR2dbc;
import co.com.bancolombia.r2dbc.repository.UserRepository;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UseCasesConfigTest {

    @MockitoBean
    private R2dbcSafeExecutor r2dbcSafeExecutor;
    @MockitoBean
    private UserRepository userRepository;
    @MockitoBean
    private UserMapperR2dbc userMapperR2dbc;

    @Autowired
    private UserServicePort userServicePort;

    @Test
    void shouldLoadUserServicePortBean() {
        assertThat(userServicePort).isNotNull();
    }
}