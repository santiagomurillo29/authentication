CREATE TABLE IF NOT EXISTS role (
    id_role BIGSERIAL PRIMARY KEY,
    name_role VARCHAR(100) NOT NULL,
    description_role VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id_user BIGSERIAL PRIMARY KEY,
    name_user VARCHAR(100) NOT NULL,
    last_name_user VARCHAR(100) NOT NULL,
    document_user VARCHAR(50) NOT NULL,
    birthday_user DATE NOT NULL,
    address_user VARCHAR(200) NOT NULL,
    phone_user VARCHAR(50) NOT NULL,
    email_user VARCHAR(100) NOT NULL,
    base_salary_user NUMERIC(15,2) NOT NULL,
    id_role BIGINT NOT NULL,
    FOREIGN KEY (id_role) REFERENCES role(id_role)
);

INSERT INTO role (name_role, description_role) VALUES
('CUSTOMER', 'Customer role'),
('ADVISOR', 'Advisor role'),
('MANAGER', 'Manager role');
