def saveWords(cadena):
  cadena = cadena + " " # anadir un espacio al final para busca rla totalidad
  palabrasToSearch = []
  substring = []
  for i in range(len(cadena)):
    #print( cadena[i] )
    if cadena[i] != " ":
      substring.append(cadena[i])
      
    elif cadena[i] == " ":
      palabrasToSearch.append(substring)
      substring = []
          
  return palabrasToSearch  


def contarPalabras(listaPalabras):
  
  listaPalabras.append(0) # anadir un cero por eso del overflow
  palabrasObjetivo =[]
  conteoRecurrencia = []
  
  i = 0
  count = 1
  maximoContador = count
  maximoCaracter = " "

  secuenciaActual = listaPalabras[0]
  secuenciaAnterior = " "

  palabrasObjetivo.append(secuenciaActual)



  while i < len(listaPalabras)-1:
    secuenciaActual = listaPalabras[i]
    if secuenciaAnterior == listaPalabras[i] :       
      count += 1
    else:
      count = 1
    
    if count >= maximoContador:     
      maximoContador = count
      maximoCaracter = listaPalabras[i]
      

    if listaPalabras[i] != listaPalabras[i+1]: # si el siguiente elemento no es igual reinicio el contador y el maximo elemento
      conteoRecurrencia.append(maximoContador)
      count = 1
      maximoContador = count
      maximoCaracter = listaPalabras[i+1]  # re asigno cual va ser el maximo caracter como un spoiler
      palabrasObjetivo.append(maximoCaracter) # guardo los caracteres los cuales llegaorn a ser los mayores y los bjetivos de la buqued apar ale conteo
    
    secuenciaAnterior = listaPalabras[i]
    
    i += 1 
  
  return list(palabrasObjetivo[:-1]) , conteoRecurrencia
   
def resultadoEnString(cadena , cantidad):
  megaString = " "
  for i in cadena:
    
    subString = ''.join([str(elem) for elem in i])
    subString = subString + " "
    
    megaString += subString
  
  megaString = megaString.strip() 

  numeros = ' '.join([str(elem) for elem in cantidad])

  return megaString,numeros


ingreso = input()

palabras = saveWords(ingreso)
lista , listaCantidad = contarPalabras(palabras)

a, b = resultadoEnString(lista,listaCantidad)
print(a) ; print(b)