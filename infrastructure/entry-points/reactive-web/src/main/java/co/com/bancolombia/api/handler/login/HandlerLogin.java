package co.com.bancolombia.api.handler.login;

import co.com.bancolombia.api.dto.request.login.LoginRequestDto;
import co.com.bancolombia.api.dto.request.validation.RequestValidator;
import co.com.bancolombia.api.dto.response.login.LoginResponseDto;
import co.com.bancolombia.api.mapper.login.LoginMapper;
import co.com.bancolombia.model.login.model.LoginResponse;
import co.com.bancolombia.usecase.login.usecase.api.LoginServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerLogin {

    private final LoginServicePort loginServicePort;
    private final LoginMapper loginMapper;
    private final RequestValidator validator;

    public Mono<ServerResponse> Login(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LoginRequestDto.class)
                .flatMap(validator::validate)
                .map(loginMapper::toModelLogin)
                .flatMap(loginServicePort::login)
                .map(loginMapper::toDtoLogin)
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(dto));
    }
}
