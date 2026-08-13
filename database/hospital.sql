CREATE DATABASE IF NOT EXISTS hospital_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;

USE hospital_db;


CREATE TABLE patients (

    id INT AUTO_INCREMENT PRIMARY KEY,

    naam VARCHAR(100) NOT NULL,

    sen INT NOT NULL,

    shomare_meli VARCHAR(20) NOT NULL UNIQUE,

    emergency BOOLEAN DEFAULT FALSE,

    bastari BOOLEAN DEFAULT FALSE,

    bakhsh_id INT NULL

) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


CREATE TABLE departments (

    id INT AUTO_INCREMENT PRIMARY KEY,

    naam VARCHAR(100) NOT NULL,

    zarfiat INT NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

ALTER TABLE patients
    ADD CONSTRAINT fk_patients_department
    FOREIGN KEY (bakhsh_id) REFERENCES departments(id);


CREATE TABLE doctors (

    id INT AUTO_INCREMENT PRIMARY KEY,

    naam VARCHAR(100) NOT NULL,

    takhasos VARCHAR(100) NOT NULL,

    bakhsh VARCHAR(100) NOT NULL,

    saat_shoru INT NOT NULL,

    saat_payan INT NOT NULL,

    zarfiat_nobat INT NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


CREATE TABLE appointments (

    id INT AUTO_INCREMENT PRIMARY KEY,

    patient_id INT NOT NULL,

    doctor_id INT NOT NULL,

    tarikh DATE NOT NULL,

    saat INT NOT NULL,

    anjam_shode BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (patient_id)
        REFERENCES patients(id),

    FOREIGN KEY (doctor_id)
        REFERENCES doctors(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


CREATE TABLE bills (

    id INT AUTO_INCREMENT PRIMARY KEY,

    patient_id INT NOT NULL,

    noe_bil VARCHAR(100) NOT NULL,

    hazine DOUBLE NOT NULL,

    tarikh TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (patient_id)
        REFERENCES patients(id)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


CREATE TABLE finance (

    id INT AUTO_INCREMENT PRIMARY KEY,

    mablagh DOUBLE NOT NULL,

    noe VARCHAR(100) NOT NULL,

    tarikh TIMESTAMP
        DEFAULT CURRENT_TIMESTAMP
) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


INSERT INTO departments
(naam, zarfiat)
VALUES
('اورژانس', 5),
('داخلی', 10),
('جراحی', 5);


INSERT INTO doctors
(
    naam,
    takhasos,
    bakhsh,
    saat_shoru,
    saat_payan,
    zarfiat_nobat
)
VALUES
(
    'دکتر احمدی',
    'متخصص داخلی',
    'داخلی',
    8,
    14,
    10
),

(
    'دکتر رضایی',
    'جراح عمومی',
    'جراحی',
    10,
    16,
    5
),

(
    'دکتر کریمی',
    'پزشک اورژانس',
    'اورژانس',
    0,
    23,
    15
);