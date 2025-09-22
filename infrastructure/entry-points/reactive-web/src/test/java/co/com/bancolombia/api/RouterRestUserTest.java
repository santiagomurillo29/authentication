package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.request.user.UserRequestDto;
import co.com.bancolombia.api.dto.request.validation.RequestValidator;
import co.com.bancolombia.api.dto.response.user.UserResponseDto;
import co.com.bancolombia.api.handler.user.HandlerUser;
import co.com.bancolombia.api.mapper.user.UserMapper;
import co.com.bancolombia.api.router.user.RouterRestUser;
import co.com.bancolombia.model.user.model.RoleModel;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.r2dbc.health.R2dbcHealthChecker;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRestUser.class, HandlerUser.class})
@WebFluxTest
@ImportAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration.class })
@SuppressWarnings("unused")
class RouterRestUserTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserServicePort servicePort;

    @MockitoBean
    private RequestValidator validator;

    @MockitoBean
    private UserMapper mapper;

    @MockitoBean
    private R2dbcHealthChecker r2dbcHealthChecker;

    @Test
    void createUser_endpoint() {
        RoleModel roleModel = new RoleModel(1L, "CUSTOMER", "Customer description");
        UserRequestDto request = new UserRequestDto("nameUser", "lastNameUser", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel.getIdRole());
        UserModel model = new UserModel(1L, "nameUser", "lastNameUser", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel);
        UserResponseDto response = new UserResponseDto(1L, "nameUser", "lastNameUser", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel.getIdRole(), roleModel.getNameRole());

        given(validator.validate(any())).willReturn(Mono.just(request));
        given(mapper.toModelUser(any())).willReturn(model);
        given(servicePort.createUser(any(UserModel.class), any())).willReturn(Mono.just(model));
        given(mapper.toDtoUser(any())).willReturn(response);

        webTestClient.post().uri("/api/v1/usuarios")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponseDto.class)
                .isEqualTo(response);
    }

    @Test
    void getUserByEmail_endpoint() {
        RoleModel roleModel = new RoleModel(1L, "CUSTOMER", "Customer description");
        UserModel model = new UserModel(1L, "nameUser", "lastNameUser", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel);
        UserResponseDto response = new UserResponseDto(1L, "nameUser", "lastNameUser", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00), roleModel.getIdRole(), roleModel.getNameRole());

        given(servicePort.findUserByEmail(anyString(), any())).willReturn(Mono.just(model));
        given(mapper.toDtoUser(model)).willReturn(response);

        webTestClient.get().uri(uriBuilder ->
                        uriBuilder.path("/api/v1/usuario/correo")
                                .queryParam("emailUser", "string@gmail.com")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(UserResponseDto.class)
                .isEqualTo(response);
    }

    @Test
    void shouldReturnDownWhenDatabaseIsDown() {
        when(r2dbcHealthChecker.isDatabaseUp()).thenReturn(Mono.just(false));

        webTestClient.get()
                .uri("/health")
                .exchange()
                .expectStatus().is5xxServerError()
                .expectHeader().contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .expectBody(Map.class)
                .isEqualTo(Map.of("status", "DOWN", "database", "DOWN"));
    }

    @Test
    void shouldReturnOkForLiveness() {
        webTestClient.get()
                .uri("/liveness")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(String.valueOf(MediaType.TEXT_PLAIN))
                .expectBody(String.class)
                .isEqualTo("OK");
    }
}
