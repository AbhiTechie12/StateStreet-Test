DROP TABLE IF EXISTS course;

CREATE SEQUENCE seq_course_id
    INCREMENT BY 1;
    START WITH 1
    NO MAXIMUM;

CREATE TABLE course (
    id: BIGINT DEFAULT nextval('seq_course_id'),
    name: VARCHAR(255) NOT NULL,
    CONSTRAINT pk_course PRIMARY KEY (id)
);