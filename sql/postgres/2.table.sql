\c impacta

CREATE SEQUENCE seq_role INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE seq_func INCREMENT BY 1 START WITH 1;

CREATE TABLE tab_role (
    role_code NUMERIC(10) PRIMARY KEY DEFAULT NEXTVAL('seq_role'),
    role_name VARCHAR(20)
);

CREATE TABLE tab_func (
    func_code     NUMERIC(10) PRIMARY KEY DEFAULT NEXTVAL('seq_func'),
    func_name     VARCHAR(30),
    func_rmnt_val NUMERIC(12, 2),
    role_code     NUMERIC(10)
);

ALTER TABLE tab_func
ADD CONSTRAINT fk_role_func
    FOREIGN KEY (role_code)
        REFERENCES tab_role (role_code);

GRANT ALL ON seq_role TO aluno;
GRANT ALL ON seq_func TO aluno;
GRANT ALL ON tab_role TO aluno;
GRANT ALL ON tab_func TO aluno;
