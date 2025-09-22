package co.com.bancolombia.r2dbc.helper;

import co.com.bancolombia.model.globalmessage.GlobalMessage;
import co.com.bancolombia.model.user.model.RoleModel;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.r2dbc.adapter.role.RoleAdapterR2dbc;
import co.com.bancolombia.r2dbc.adapter.user.UserAdapterR2dbc;
import co.com.bancolombia.r2dbc.entity.user.UserEntity;
import co.com.bancolombia.r2dbc.exception.DataBaseException;
import co.com.bancolombia.r2dbc.health.R2dbcSafeExecutor;
import co.com.bancolombia.r2dbc.mapper.user.UserMapperR2dbc;
import co.com.bancolombia.r2dbc.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static reactor.test.StepVerifier.create;

@ExtendWith(MockitoExtension.class)
class UserRepositoryAdapterTest {

    @Mock private UserRepository userRepository;
    @Mock private UserMapperR2dbc userMapperR2dbc;
    @Mock private R2dbcSafeExecutor safeExecutor;
    @Mock private RoleAdapterR2dbc roleAdapterR2dbc;
    private UserAdapterR2dbc adapter;

    @BeforeEach
    void setUp() {
        adapter = new UserAdapterR2dbc(
                userRepository,
                userMapperR2dbc,
                safeExecutor,
                roleAdapterR2dbc
        );
    }

    @Test
    @SuppressWarnings("unchecked")
    void saveUser_persistsAndMaps(){
        RoleModel roleModel = new RoleModel(1L, "CUSTOMER", "description");
        UserModel model = new UserModel(1L, "string", "string", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel);
        UserEntity entity = new UserEntity(1L, "string", "string", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel.getIdRole());

        when(userMapperR2dbc.toEntityUser(model)).thenReturn(entity);
        when(userRepository.save(entity)).thenReturn(Mono.just(entity));
        when(userMapperR2dbc.toModelUser(entity)).thenReturn(model);

        when(safeExecutor.executeMono(any()))
                .thenAnswer(invocation -> ((Supplier<Mono<UserModel>>) invocation.getArgument(0)).get());

        when(roleAdapterR2dbc.findRoleById(roleModel.getIdRole()))
                .thenReturn(Mono.just(roleModel));

        StepVerifier.create(adapter.saveUser(model))
                .expectNextMatches(result ->
                        result.getIdUser().equals(1L) &&
                                result.getRole() != null &&
                                result.getRole().getIdRole().equals(1L)
                )
                .verifyComplete();
    }

    @Test
    void saveUser_whenDatabaseIsDown_thenThrowDataBaseException() {
        RoleModel roleModel = new RoleModel(1L, "CUSTOMER", "description");
        UserModel model = new UserModel(1L, "string", "string", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel);

        when(safeExecutor.executeMono(any()))
                .thenReturn(Mono.error(new DataBaseException(GlobalMessage.DATABASE_ERROR)));

        create(adapter.saveUser(model))
                .expectErrorSatisfies(error -> {
                    assertInstanceOf(DataBaseException.class, error);
                    assertEquals(GlobalMessage.DATABASE_ERROR.getMessage(), error.getMessage());
                })
                .verify();
    }
}
