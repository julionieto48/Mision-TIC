def contadorDeVotosCentroDemocratico(p,n,urna):

  p = list(p)
  n = list(n)
  urna = list(urna)
  registroDeVotos = []
  str1 = ""

  
  anterior = 0
  acumulado = 0
  flagNulo = 0
  letraAnterior = ''
  for i in range(0,len(urna)):

    
    
    if (urna[i] in p) and (urna[i] not in n):
      selector = 1             
    elif (urna[i] in n) and (urna[i] not in p):
      selector = -1 
    elif (urna[i] in p) and (urna[i] in n):
      selector = 0             #si hay dos elementos en p  y en n
      flagNulo = 1             #activa bandera de nulidad
    elif urna[i] not in p  or urna[i] not in n  : 
      selector = 0            # cuando no esta en lo posibles candidatos o voto nulo
      flagNulo = 1 

    
    anterior = acumulado
    acumulado =  acumulado + selector

    if flagNulo == 0:
      if acumulado > 0 :
        letra = 'P'
        registroDeVotos.append(letra)
      elif acumulado < 0:
        letra = 'N'
        registroDeVotos.append(letra)
      elif acumulado  == 0:
         letra = 'I'
         registroDeVotos.append(letra)
      
      
    elif flagNulo == 1:
      registroDeVotos.append(letraAnterior)
      flagNulo = 0

    letraAnterior = letra
    #print(letraAnterior)
     
    

    #print(selector,anterior,acumulado)
    
    
     
    
    
    
  return str1.join(registroDeVotos)

    
   
p =input()
n =input()
urna =input()
print(contadorDeVotosCentroDemocratico(p,n,urna))

