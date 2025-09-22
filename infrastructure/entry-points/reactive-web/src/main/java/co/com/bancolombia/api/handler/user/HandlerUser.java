package co.com.bancolombia.api.handler.user;

import co.com.bancolombia.api.dto.request.user.UserRequestDto;
import co.com.bancolombia.api.dto.request.validation.RequestValidator;
import co.com.bancolombia.api.mapper.user.UserMapper;
import co.com.bancolombia.r2dbc.health.R2dbcHealthChecker;
import co.com.bancolombia.usecase.user.usecase.api.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class HandlerUser {

    private static final String TOKEN_ATTR = "token";

    private final UserServicePort userServicePort;
    private final UserMapper userMapper;
    private final RequestValidator validator;
    private final R2dbcHealthChecker healthChecker;

    public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
        String token = serverRequest.exchange().getAttribute(TOKEN_ATTR );
        return serverRequest.bodyToMono(UserRequestDto.class)
                .flatMap(validator::validate)
                .map(userMapper::toModelUser)
                .flatMap(model -> userServicePort.createUser(model, token))
                .map(userMapper::toDtoUser)
                .flatMap(response -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }

    public Mono<ServerResponse> getUserByEmail(ServerRequest serverRequest) {
        String token = serverRequest.exchange().getAttribute(TOKEN_ATTR);
        return Mono.justOrEmpty(serverRequest.queryParam("emailUser"))
                .flatMap(model -> userServicePort.findUserByEmail(model, token))
                .map(userMapper::toDtoUser)
                .flatMap(response -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response));
    }

    public Mono<ServerResponse> getHealth() {
        return healthChecker.isDatabaseUp()
                .flatMap(isUp -> {
                    if (Boolean.TRUE.equals(isUp)) {
                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("status", "UP", "database", "UP"));
                    } else {
                        return ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(Map.of("status", "DOWN", "database", "DOWN"));
                    }
                });
    }

    public Mono<ServerResponse> getLiveness() {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue("OK");
    }
}
