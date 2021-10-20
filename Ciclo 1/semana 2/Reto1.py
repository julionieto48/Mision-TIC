# a numero primer semestre
# b numero segundo semestre
# c numero tercer semestre


def calcularCantidad(a):
  # ecuaciones b = 2*a - 4  y c = (a+b)/(5) 
  a = int(a)
  b = int(2*a + 4) 
  c = int( (a+b)/(5) ) 
  print(a,b,c)
  return c

def aperturaGrupos(c):
  if 0 <= c <= 20 :
    print("uno")
  elif 21 <= c <= 30 :
    print("dos")
  elif 31 <= c <= 50 :
    print("tres")
  else:
    print("cuatro")

 

def prediccionEstudiantes(a):
  C = calcularCantidad(a)

  aperturaGrupos(C)

a = input("numero estudiantes primer semestre") # ingreso vlaor inicial numero estudiantes primer semestre
prediccionEstudiantes(a)
