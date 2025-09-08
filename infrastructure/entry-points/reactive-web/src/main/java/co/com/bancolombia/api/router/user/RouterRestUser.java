package co.com.bancolombia.api.router;

import co.com.bancolombia.api.dto.request.user.UserRequestDto;
import co.com.bancolombia.api.dto.response.user.UserResponseDto;
import co.com.bancolombia.api.handler.HandlerUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRestUser {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(HandlerUser handlerUser) {
        return route(GET("/api/v1/usuarios"), handlerUser::GetUser)
                .andRoute(GET("/api/v1/usuario/correo"), handlerUser::GetUserByEmail)
                .andRoute(POST("/api/v1/usuarios"), handlerUser::CreateUser)
                .andRoute(POST("/api/v1/login"), handlerUser::CreateUser);
    }
}
