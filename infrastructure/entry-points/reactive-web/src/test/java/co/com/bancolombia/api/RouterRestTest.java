package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.request.UserRequestDto;
import co.com.bancolombia.api.dto.request.validation.RequestValidator;
import co.com.bancolombia.api.dto.response.UserResponseDto;
import co.com.bancolombia.api.handler.HandlerUser;
import co.com.bancolombia.api.mapper.UserMapper;
import co.com.bancolombia.api.router.RouterRestUser;
import co.com.bancolombia.model.user.model.UserModel;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {RouterRestUser.class, HandlerUser.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserServicePort servicePort;

    @MockitoBean
    private RequestValidator validator;

    @MockitoBean
    private UserMapper mapper;

    @Test
    void createUser_endpoint() {
        UserRequestDto request = new UserRequestDto("string", "string", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00));
        UserModel model = new UserModel(1L, "string", "string", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00));
        UserResponseDto response = new UserResponseDto(1L, "string", "string", "1000883010", LocalDate.of(2004, 10, 1), "Cra 78", "3002002030", "string@gmail.com", BigDecimal.valueOf(3000000.00));

        given(validator.validate(any())).willReturn(Mono.just(request));
        given(mapper.toModelUser(any())).willReturn(model);
        given(servicePort.createUser(any())).willReturn(Mono.just(model));
        given(mapper.toDtoUser(any())).willReturn(response);

        webTestClient.post().uri("/api/v1/usuarios")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserResponseDto.class)
                .isEqualTo(response);
    }
}
