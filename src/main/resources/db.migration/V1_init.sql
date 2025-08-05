CREATE TABLE donation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    donor_email VARCHAR(255),
    donor_full_name VARCHAR(255),
    payment_date TIMESTAMP,
    payment_amount DOUBLE,
    payment_method VARCHAR(100),
    status VARCHAR(50)
);

CREATE TABLE help (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    beneficiary_email VARCHAR(255),
    beneficiary_full_name VARCHAR(255),
    payment_date TIMESTAMP,
    payment_amount DOUBLE,
    payment_method VARCHAR(100),
    description TEXT
);

ALTER TABLE donation ADD COLUMN psp_payment_id VARCHAR(255);
