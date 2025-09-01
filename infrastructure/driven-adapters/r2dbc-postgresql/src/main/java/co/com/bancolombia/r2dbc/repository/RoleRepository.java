package co.com.bancolombia.r2dbc.repository;

import co.com.bancolombia.r2dbc.entity.RoleEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoleRepository extends ReactiveCrudRepository<RoleEntity, Long> {
}
