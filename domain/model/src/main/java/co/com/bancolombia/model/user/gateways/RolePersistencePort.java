package co.com.bancolombia.model.user.gateways.role;

import co.com.bancolombia.model.user.model.role.RoleModel;
import reactor.core.publisher.Mono;

public interface RolePersistencePort {
    Mono<RoleModel> findRoleById(Long idRole);
}
