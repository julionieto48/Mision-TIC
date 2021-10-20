/*  El código de las reservas  de Santa Marta a Cartagena para el sábado 28 de agosto y el domingo 29 de agosto */
select Numero_Ticket from ruta, ticket
where  Destino_Ruta=2 and Terminal_idTerminal = 1 and ( Fecha_de_salida_rut = '2021-08-28' or Fecha_de_salida_rut = '2021-08-29' )
group by Numero_Ticket ;


/*  El precio de un viaje de Bogotá a Cali y de Cali a Tumaco el 20 de agosto */
select distinct Precio_rut from ruta
where ((Terminal_idTerminal=3 and Destino_Ruta=4) or (Terminal_idTerminal=4 and Destino_Ruta=5))and Fecha_de_salida_rut = '2021-08-20';

/*  El equipaje que se perdió en el viaje de Bogotá a Medellín el 20 de agosto */
select  distinct idEquipaje_eq from ticket,ruta,equipaje
where (Ruta_Terminal_idTerminal=4 and Destino_Ruta=6) and Fecha_de_salida_rut;

/*Los conductores que tienen turno el 24 de agosto*/
select  distinct idEmpleado_emp from empleado
where Turno_emp = '2021-08-24';

/*Los pasajeros que tienen reserva desde Bogotá hacia Cali el 29 de agosto*/
select  distinct Nombre_pas from pasajero,ticket,ruta
where (Terminal_idTerminal = 3 and Destino_Ruta = 4) and Fecha_de_salida_rut = '2021-08-29';



/*Cuantos asientos hay disponibles en el viaje de la ruta Valledupar a Barranquilla el día 28 de agosto*/
select  distinct Sillas_disponibles_rut from ruta
where  Fecha_de_salida_rut = '2021-08-28' and (Terminal_idTerminal = 7 and Destino_Ruta =8) 