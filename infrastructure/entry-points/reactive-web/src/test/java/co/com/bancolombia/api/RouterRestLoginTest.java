package co.com.bancolombia.api;

import co.com.bancolombia.api.dto.request.login.LoginRequestDto;
import co.com.bancolombia.api.dto.request.validation.RequestValidator;
import co.com.bancolombia.api.dto.response.login.LoginResponseDto;
import co.com.bancolombia.api.handler.login.HandlerLogin;
import co.com.bancolombia.api.mapper.login.LoginMapper;
import co.com.bancolombia.api.router.login.RouterRestLogin;
import co.com.bancolombia.model.login.model.LoginRequest;
import co.com.bancolombia.model.login.model.LoginResponse;
import co.com.bancolombia.usecase.login.usecase.api.LoginServicePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {RouterRestLogin.class, HandlerLogin.class})
@WebFluxTest
@ImportAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration.class })
@SuppressWarnings("unused")
class RouterRestLoginTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private LoginServicePort loginServicePort;

    @MockitoBean
    private LoginMapper loginMapper;

    @MockitoBean
    private RequestValidator validator;

    @Test
    void login_endpoint() {
        LoginRequestDto request = new LoginRequestDto("string@gmail.com", "123456");
        LoginRequest model = new LoginRequest("string@gmail.com", "123456");

        LoginResponse domainResponse = new LoginResponse("mocked-token", 3600L);
        LoginResponseDto dtoResponse = new LoginResponseDto("mocked-token", 3600L);

        given(validator.validate(any())).willReturn(Mono.just(request));
        given(loginMapper.toModelLogin(any())).willReturn(model);
        given(loginServicePort.login(model)).willReturn(Mono.just(domainResponse));
        given(loginMapper.toDtoLogin(domainResponse)).willReturn(dtoResponse);

        webTestClient.post().uri("/api/v1/login")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LoginResponseDto.class)
                .isEqualTo(dtoResponse);
    }
}
