USE hospital_db;

ALTER TABLE patients
    ADD COLUMN bakhsh_id INT NULL;

ALTER TABLE patients
    ADD CONSTRAINT fk_patients_department
    FOREIGN KEY (bakhsh_id) REFERENCES departments(id);
