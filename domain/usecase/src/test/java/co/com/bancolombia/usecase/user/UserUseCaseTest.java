package co.com.bancolombia.usecase.user;

import co.com.bancolombia.model.user.gateways.RolePersistencePort;
import co.com.bancolombia.model.user.gateways.TokenAuthSecurityPort;
import co.com.bancolombia.model.user.gateways.UserPersistencePort;
import co.com.bancolombia.model.globalmessage.GlobalMessage;
import co.com.bancolombia.model.user.model.RoleModel;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.usecase.exception.BusinessException;
import co.com.bancolombia.usecase.user.usecase.UserUseCase;
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
class UserUseCaseTest {

    @Mock
    UserPersistencePort repo;

    @Mock
    RolePersistencePort repoRole;

    @Mock
    TokenAuthSecurityPort tokenAuthSecurityPort;

    @InjectMocks
    UserUseCase useCase;

    private final String token = "anyToken";

    @Test
    void createUser_whenNotExists_thenSave() {
        RoleModel roleModel = new RoleModel(1L, "CUSTOMER", "description");
        UserModel model = new UserModel(1L, "string", "string", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel);

        given(tokenAuthSecurityPort.getSubject(token)).willReturn(Mono.just("user@gmail.com"));
        given(repo.existsUserByEmail(model.getEmailUser())).willReturn(Mono.just(false));
        given(repoRole.findRoleById(roleModel.getIdRole())).willReturn(Mono.just(roleModel));
        given(repo.saveUser(model)).willReturn(Mono.just(model));

        StepVerifier.create(useCase.createUser(model, token))
                .expectNext(model)
                .verifyComplete();
    }

    @Test
    void createUser_whenEmailExists_thenSave() {
        RoleModel roleModel = new RoleModel(1L, "CUSTOMER", "description");
        UserModel model = new UserModel(1L, "string", "string", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel);

        given(tokenAuthSecurityPort.getSubject(token)).willReturn(Mono.just("user@gmail.com"));
        given(repo.existsUserByEmail(model.getEmailUser())).willReturn(Mono.just(true));

        StepVerifier.create(useCase.createUser(model, token))
                .expectErrorSatisfies(error -> {
                    assertInstanceOf(BusinessException.class, error);
                    assertEquals(GlobalMessage.BAD_PARAMETER.getMessage(), error.getMessage());
                })
                .verify();
    }
}
