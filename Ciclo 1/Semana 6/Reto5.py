# -*- coding: utf-8 -*-
"""Untitled2.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1nGdhBQl2r7eR9iM8I2nNI9t5gY4Ym9KD
"""

# Reto 5 

# punto A

def verificar_posicion(posible,exhibicion,elemento):
  posicionesPresentes = []
  for i in range(len(exhibicion)):
    if exhibicion[i] == elemento:
      posicionesPresentes.append(i)
  #print(posicionesPresentes)
  return posicionesPresentes




posibles_ubicaciones = [1,3,6,8]
exhibicion_ubicacion = ['TV','radio','PC','PC','PC','TV','radio','PC','PC','PC']
elemento = 'radio'
verificar_posicion(posibles_ubicaciones,exhibicion_ubicacion,elemento)

# verificar_posicion ([1,3,6,8], ['TV','radio','PC','PC','PC','TV','radio','PC','PC','PC'], 'radio')

# punto B
# debe retornar los productos que llegaron y no se encuentran en exhibición
# valores representan el numero de producto

#def entrada_sin_exhibicion(llegado,presente):
#  losTengo = []
#  noLosTengo =[]
#  for i in range(len(llegados)):
#    for j in presentes:
#      if llegados[i] == j:
#        losTengo.append(llegados[i] )
#      else:
#        noLosTengo.append(llegados[i])
#  print(noLosTengo) 

#def entrada_sin_exhibicion(llegado,presente):
#  losTengo = []
#  noLosTengo =[]
#  for i in range(len(llegados)):
#    for j in presentes:
#      if llegados[i] == j:
#        losTengo.append(llegados[i] )
#      else:
#        noLosTengo.append(llegados[i])

#  # remover duplicados
#  temp_list = []
#  for i in noLosTengo:
#    if i not in temp_list:
#      temp_list.append(i)
#  noLosTengo = temp_list 
#  print(noLosTengo)
#  #return noLosTengo

def entrada_sin_exhibicion(llegado,presente):
  noLosTengo = []
  for i in llegado:
    if i not in presente:
      noLosTengo.append(i)
  print(noLosTengo)


llegados = [3,5,7,10,15,16]
presentes = [4,10,5,8]
entrada_sin_exhibicion(llegados,presentes)
# entrada_sin_exhibicion ([3,5,7,10,15,16],[4,10,5,8])

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
    



a = [4, 27, 8, 6, 20, 23, 19, 16, 12, 7, 14, 25, 0, 13]
b = [16, 21, 2, 11, 8, 12, 25, 9, 13]
cantidad_exhibicion_sin_entrada(a,b)
# entrada_sin_exhibicion ([3,5,7,10,15,16],[4,10,5,8])

# punto D 

def productos(lista):
  temp = []
  for i in lista:
    if i not in temp:
      temp.append(i)
  lista = temp
  return lista




lista = ['TV','radio','PC','PC','PC','TV','radio','PC','PC','PC']
productos(lista)