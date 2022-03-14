insert into asignaturas (id, nombre, descripcion, curso)
 select 1, 'Matematicas', 'Matematicas de alto nivel', '2' from dual where not exists (select 1 from asignaturas where id = 1);

 insert into asignaturas (id, nombre, descripcion, curso)
 select 2, 'Lengua', 'Literatura española', '3' from dual where not exists (select 1 from asignaturas where id = 2);
 
 insert into asignaturas (id, nombre, descripcion, curso)
 select 3, 'Ingles', 'Selectividad', '4' from dual where not exists (select 1 from asignaturas where id = 3);
 