insert into asignaturas (id, nombre, descripcion, curso)
 select 1, 'Matematicas', 'Matematicas de alto nivel', '2' from dual where not exists (select 1 from asignaturas where id = 1);

 insert into asignaturas (id, nombre, descripcion, curso)
 select 2, 'Lengua', 'Literatura española', '3' from dual where not exists (select 1 from asignaturas where id = 2);
 
 insert into asignaturas (id, nombre, descripcion, curso)
 select 3, 'Ingles', 'Selectividad', '4' from dual where not exists (select 1 from asignaturas where id = 3);
 
 
  insert into rol (id, rol)
	select 1, 'ADMIN' from dual where not exists (select 1 from rol where id = 1);

insert into rol (id, rol)
	select 2, 'CONSULTA' from dual where not exists (select 1 from rol where id = 2);
	
	insert into usuario (username, nombre, password, rol_id)
	select 'admin', 'Alvaro', '$2a$10$5xOe75pbLcAjp0TbVWaluunrSshgYdH82YNwGd.b0Os4hAWbIEkry', 1 from dual where not exists (select 1 from usuario where username = 'admin');

insert into usuario (username, nombre, password, rol_id)
	select 'consulta', 'Empleado de NTTData', '$2a$10$5xOe75pbLcAjp0TbVWaluunrSshgYdH82YNwGd.b0Os4hAWbIEkry', 2 from dual where not exists (select 1 from usuario where username = 'consulta');
  