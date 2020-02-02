CREATE TABLE aluno.tab_role (
    role_code NUMBER,
    role_name VARCHAR2(20 CHAR)
);

ALTER TABLE aluno.tab_role
ADD CONSTRAINT pk_role
    PRIMARY KEY (role_code);

CREATE TABLE aluno.tab_func (
    func_code     NUMBER,
    func_name     VARCHAR2(30 CHAR),
    func_rmnt_val NUMBER(10, 2),
    role_code     NUMBER
);

ALTER TABLE aluno.tab_func
ADD CONSTRAINT pk_func
    PRIMARY KEY (func_code);

ALTER TABLE aluno.tab_func
ADD CONSTRAINT fk_role_func
    FOREIGN KEY (role_code)
        REFERENCES aluno.tab_role (role_code);

CREATE SEQUENCE aluno.seq_role INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE aluno.seq_func INCREMENT BY 1 START WITH 1;
