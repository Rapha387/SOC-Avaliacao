CREATE TABLE exame (rowid bigint auto_increment primary key, nm_exame VARCHAR(255));
create table funcionario(
	rowid bigint auto_increment primary key, 
    nm_funcionario VARCHAR(255)
);

create table exame_funcionario
(
	rowid_funcionario bigint, 
    rowid_exame bigint,
    dt_exame date,
    constraint pk_exame_funcionario primary key (rowid_funcionario, rowid_exame, dt_exame),
    constraint fk_funcionario_exame_funcionario foreign key (rowid_funcionario) references funcionario (rowid),
    constraint fk_exame_exame_funcionario foreign key (rowid_exame) references exame (rowid)
);

INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');
INSERT INTO funcionario (nm_funcionario) VALUES ('Giovanna'), ('Maria Luiza'), ('Raphael'), ('Gustavo');

INSERT INTO exame_funcionario (rowid_funcionario, rowid_exame, dt_exame) values (1, 1, '2000-01-05');
INSERT INTO exame_funcionario (rowid_funcionario, rowid_exame, dt_exame) values (1, 2, '2000-01-04');
INSERT INTO exame_funcionario (rowid_funcionario, rowid_exame, dt_exame) values (2, 2, '2000-01-04');
INSERT INTO exame_funcionario (rowid_funcionario, rowid_exame, dt_exame) values (1, 1, '2000-01-04');
