DROP TABLE IF EXISTS student;

CREATE SEQUENCE seq_student_id
    INCREMENT BY 1;
    START WITH 1
    NO MAXIMUM;

CREATE TABLE student (
    id: BIGINT DEFAULT nextval('seq_student_id'),
    name: VARCHAR(255) NOT NULL,
    CONSTRAINT pk_student PRIMARY KEY (id)
);