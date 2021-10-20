
# Reto 5 

# punto A

def verificar_posicion(posible,exhibicion,elemento):
  posicionesPresentes = []
  for i in range(len(exhibicion)):
    if exhibicion[i] == elemento:
      posicionesPresentes.append(i)
  #print(posicionesPresentes)
  return posicionesPresentes

posibles_ubicaciones = input()
exhibicion_ubicacion = input()
elemento = input()
verificar_posicion(posibles_ubicaciones,exhibicion_ubicacion,elemento)

#_______________________________________________________________________________
# punto B

def entrada_sin_exhibicion(llegado,presente):
  noLosTengo = []
  for i in llegado:
    if i not in presente:
      noLosTengo.append(i)
  return noLosTengo


llegados = input()
presentes = input()
entrada_sin_exhibicion(llegados,presentes)

#_______________________________________________________________________________
# punto C

def cantidad_exhibicion_sin_entrada(llegado,presente):
  noLosTengo = []
  for i in llegado:
    if i not in presente:
      noLosTengo.append(i)
  #print(noLosTengo)  # que compare el número de productos que llegaron y no se encuentran en exhibición  

  novedades = []     #  número de productos que se encuentran en exhibición y que no se encuentra en los productos que llegaron
  for i in presente :
    if i not in llegado:
      novedades.append(i)
  #print(novedades)
    
  menor = 0
  if len(novedades) < len(noLosTengo):
    menor = len(novedades)
  elif len(noLosTengo) < len(novedades):
    menor = len(noLosTengo)
  
  return menor
 
a = input()
b = input()
cantidad_exhibicion_sin_entrada(a,b)
#_______________________________________________________________________________
# punto D 

def productos(lista):
  temp = []
  for i in lista:
    if i not in temp:
      temp.append(i)
  lista = temp
  return lista

lista = input()
productos(lista)