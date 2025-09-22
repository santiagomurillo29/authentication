package co.com.bancolombia.usecase.login;

import co.com.bancolombia.model.globalmessage.GlobalMessage;
import co.com.bancolombia.model.login.gateways.LoginPersistencePort;
import co.com.bancolombia.model.login.model.LoginRequest;
import co.com.bancolombia.model.login.model.LoginResponse;
import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.usecase.exception.BusinessException;
import co.com.bancolombia.usecase.login.usecase.LoginUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

    @Mock
    LoginPersistencePort repoLogin;

    @Mock
    UserPersistencePort repoUser;

    @InjectMocks
    LoginUseCase useCase;

    @Test
    void login_whenUserExists_thenReturnLoginResponse() {
        UserModel user = new UserModel(1L, "John", "Doe", "1000883010", LocalDate.of(1990, 1, 1), "Address 123", "3002002030", "user@example.com", BigDecimal.valueOf(3000000.00), null);
        LoginRequest request = new LoginRequest("user@example.com", "password123");
        LoginResponse response = new LoginResponse("token123", 3600L);

        given(repoUser.findUserByEmail(request.getEmail())).willReturn(Mono.just(user));
        given(repoLogin.login(request)).willReturn(Mono.just(response));

        StepVerifier.create(useCase.login(request))
                .expectNext(response)
                .verifyComplete();
    }

    @Test
    void login_whenUserNotExists_thenReturnError() {
        LoginRequest request = new LoginRequest("nonexistent@example.com", "password123");

        given(repoUser.findUserByEmail(request.getEmail())).willReturn(Mono.empty());

        StepVerifier.create(useCase.login(request))
                .expectErrorSatisfies(error -> { assertInstanceOf(BusinessException.class, error);
                        assertEquals(GlobalMessage.NOT_FOUND_EMAIL.getMessage(), error.getMessage());
                })
                .verify();
    }
}
