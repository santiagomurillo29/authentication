package co.com.bancolombia.r2dbc.entity;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class UserEntity {

    @Id
    @Column("id_user")
    private Long idUser;

    @Column("name_user")
    private String nameUser;

    @Column("last_name_user")
    private String lastNameUser;

    @Column("document_user")
    private String documentUser;

    @Column("birthday_user")
    private LocalDate birthdayUser;

    @Column("address_user")
    private String addressUser;

    @Column("phone_user")
    private String phoneUser;

    @Column("email_user")
    private String emailUser;

    @Column("base_salary_user")
    private BigDecimal baseSalaryUser;

    @Column("id_role")
    private Long idRole;
}
