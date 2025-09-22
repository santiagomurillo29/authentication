package co.com.bancolombia.r2dbc.repository.user;

import co.com.bancolombia.r2dbc.entity.user.UserEntity;
import co.com.bancolombia.r2dbc.entity.user.UserWithPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final DatabaseClient db;

    @Override
    public Mono<UserWithPassword> findUserWithCredentialsByEmailCustom(String email) {
        String sql = """
            SELECT \s
              u.id_user,
              u.name_user,
              u.last_name_user,
              u.document_user,
              u.birthday_user,
              u.address_user,
              u.phone_user,
              u.email_user,
              u.base_salary_user,
              u.id_role,
              r.name_role AS role_name,
              c.password_hash AS password_hash
            FROM users u
            JOIN role r ON r.id_role = u.id_role
            JOIN user_credentials c ON c.id_user = u.id_user
            WHERE u.email_user = :email
           \s""";

        return db.sql(sql)
                .bind("email", email)
                .map((row, meta) -> {
                    UserEntity ue = new UserEntity();
                    ue.setIdUser(row.get("id_user", Long.class));
                    ue.setNameUser(row.get("name_user", String.class));
                    ue.setLastNameUser(row.get("last_name_user", String.class));
                    ue.setDocumentUser(row.get("document_user", String.class));
                    ue.setBirthdayUser(row.get("birthday_user", LocalDate.class));
                    ue.setAddressUser(row.get("address_user", String.class));
                    ue.setPhoneUser(row.get("phone_user", String.class));
                    ue.setEmailUser(row.get("email_user", String.class));
                    ue.setBaseSalaryUser(row.get("base_salary_user", BigDecimal.class));
                    ue.setIdRole(row.get("id_role", Long.class));

                    String passwordHash = row.get("password_hash", String.class);
                    String roleName = row.get("role_name", String.class);

                    return new UserWithPassword(ue, passwordHash, roleName);
                })
                .one();
    }
}
