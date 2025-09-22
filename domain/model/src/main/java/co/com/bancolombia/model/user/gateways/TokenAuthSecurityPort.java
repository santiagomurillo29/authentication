package co.com.bancolombia.model.user.gateways;

import reactor.core.publisher.Mono;

public interface TokenAuthSecurityPort {
    Mono<String> getSubject(String token);
}
