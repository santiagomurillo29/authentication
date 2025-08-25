CREATE TABLE IF NOT EXISTS users (
    id_user BIGSERIAL PRIMARY KEY,
    name_user VARCHAR(100) NOT NULL,
    last_name_user VARCHAR(100) NOT NULL,
    document_user VARCHAR(50) NOT NULL,
    birthday_user DATE NOT NULL,
    address_user VARCHAR(200) NOT NULL,
    phone_user VARCHAR(50) NOT NULL,
    email_user VARCHAR(100) NOT NULL,
    base_salary_user NUMERIC(15,2) NOT NULL
);
