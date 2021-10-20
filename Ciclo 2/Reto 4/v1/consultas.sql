/*  El código de las reservas  de Santa Marta a Cartagena para el sábado 28 de agosto y el domingo 29 de agosto */
select  `Numero Ticket`  from ruta, ticket
where  ruta.Destino='Cartagena' and ruta.Origen= 'Santa Marta'  and ( ruta.`Fecha de Salida`= '2021-8-28' or ruta.`Fecha de Salida`= '2021-8-29' ) ; 

 
