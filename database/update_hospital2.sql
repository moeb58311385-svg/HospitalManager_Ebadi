USE hospital_db;

CREATE TABLE daily_income (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tarikh DATE NOT NULL UNIQUE,
    mablagh_kol DOUBLE NOT NULL DEFAULT 0
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE staff (
    id INT AUTO_INCREMENT PRIMARY KEY,
    naam VARCHAR(100) NOT NULL,
    semat VARCHAR(100) NOT NULL,
    bakhsh_id INT NULL,
    FOREIGN KEY (bakhsh_id) REFERENCES departments(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

INSERT INTO staff (naam, semat, bakhsh_id)
VALUES
('زهرا محمدی', 'پرستار', 1),
('علی رضایی', 'پرستار', 2),
('سارا احمدی', 'کارمند اداری', NULL);