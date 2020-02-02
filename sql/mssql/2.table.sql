USE impacta;
GO

CREATE TABLE tab_role(
	role_code NUMERIC IDENTITY(1, 1) NOT NULL,
	role_name VARCHAR(20),
    CONSTRAINT pk_role PRIMARY KEY (role_code)
);
GO

CREATE TABLE tab_func (
    func_code     NUMERIC IDENTITY(1, 1) NOT NULL,
    func_name     VARCHAR(30),
    func_rmnt_val NUMERIC(10, 2),
    role_code     NUMERIC,
    CONSTRAINT pk_func PRIMARY KEY (func_code)
);
GO

ALTER TABLE tab_func
ADD CONSTRAINT fk_role_func
    FOREIGN KEY(role_code)
        REFERENCES tab_role (role_code);
GO
