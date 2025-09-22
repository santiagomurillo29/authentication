DROP TABLE IF EXISTS user_credentials CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS role CASCADE;

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

CREATE TABLE IF NOT EXISTS user_credentials (
    id_credential BIGSERIAL PRIMARY KEY,
    id_user BIGINT NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_user) REFERENCES users(id_user)
);

INSERT INTO role (name_role, description_role) VALUES
('CUSTOMER', 'Customer role'),
('ADVISOR', 'Advisor role'),
('ADMIN', 'Admin role');

INSERT INTO users (name_user, last_name_user, document_user, birthday_user, address_user, phone_user, email_user, base_salary_user, id_role)
VALUES
('Juan', 'Pérez', '123456789', '1990-05-10', 'Calle 123', '3001234567', 'juan.perez@example.com', 1500.00, 3),
('María', 'Gómez', '987654321', '1995-08-20', 'Carrera 45', '3007654321', 'maria.gomez@example.com', 1200.00, 2),
('Pedro', 'López', '555555555', '2000-01-15', 'Avenida 10', '3015555555', 'pedro.lopez@example.com', 1500.00, 1),
('Santiago', 'Murillo', '1025883077', '2004-10-29', 'Avenida 10', '3024391156', 'smurillocano29@gmail.com', 3000.00, 1),
('Vanesa', 'Murillo', '1000513074', '2004-10-29', 'Avenida 10', '3020003232', 'vanesa@gmail.com', 1000.00, 1);

INSERT INTO user_credentials (id_user, password_hash)
VALUES
(1, '$2a$12$yQpjz/7KF0NGVIKBti0hXOak4vbLoLlU5doxFc5ub2S1YITqNNHKK'),
(2, '$2a$12$rUWZdNE1mHWxyYk/vplPmeTx1WQc/xve9VfPA7447TeODaY9jWqaG'),
(3, '$2a$12$pNh9YUYlsaS9Dzub1G/9YebaQAqqGOWul095raxwhiQZFyHv2PiIm'),
(4, '$2a$12$pNh9YUYlsaS9Dzub1G/9YebaQAqqGOWul095raxwhiQZFyHv2PiIm'),
(5, '$2a$12$pNh9YUYlsaS9Dzub1G/9YebaQAqqGOWul095raxwhiQZFyHv2PiIm');
