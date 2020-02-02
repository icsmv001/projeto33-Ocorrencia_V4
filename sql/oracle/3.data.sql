INSERT INTO aluno.tab_role (role_code, role_name) VALUES (aluno.seq_role.NEXTVAL, 'Analista de Sistemas');
INSERT INTO aluno.tab_role (role_code, role_name) VALUES (aluno.seq_role.NEXTVAL, 'Programador Java');
INSERT INTO aluno.tab_role (role_code, role_name) VALUES (aluno.seq_role.NEXTVAL, 'DBA');
INSERT INTO aluno.tab_role (role_code, role_name) VALUES (aluno.seq_role.NEXTVAL, 'Gerente de Projetos');

INSERT INTO aluno.tab_func (func_code, func_name, func_rmnt_val, role_code) VALUES (aluno.seq_func.NEXTVAL, 'Manuel',  3500.5 , 2);
INSERT INTO aluno.tab_func (func_code, func_name, func_rmnt_val, role_code) VALUES (aluno.seq_func.NEXTVAL, 'Joaquim', 2650.15, 3);
INSERT INTO aluno.tab_func (func_code, func_name, func_rmnt_val, role_code) VALUES (aluno.seq_func.NEXTVAL, 'Maria',   4550.85, 1);
INSERT INTO aluno.tab_func (func_code, func_name, func_rmnt_val, role_code) VALUES (aluno.seq_func.NEXTVAL, 'Luis',    1205.34, 2);
INSERT INTO aluno.tab_func (func_code, func_name, func_rmnt_val, role_code) VALUES (aluno.seq_func.NEXTVAL, 'Ricardo', 6000   , 4);

COMMIT;
