#!/usr/bin/env python
"""Django's command-line utility for administrative tasks."""
import os
import sys


def main():
    """Run administrative tasks."""
    os.environ.setdefault('DJANGO_SETTINGS_MODULE', 'authentication.settings')
    try:
        from django.core.management import execute_from_command_line
    except ImportError as exc:
        raise ImportError(
            "Couldn't import Django. Are you sure it's installed and "
            "available on your PYTHONPATH environment variable? Did you "
            "forget to activate a virtual environment?"
        ) from exc
    execute_from_command_line(sys.argv)


if __name__ == '__main__':
    main()


# se trabajaron los modelos que trae django por defecto  
# 1. setings se configuro bd  tbn se agregaron las apps y librerias a usar
# 2. en vistas se manejo la rta  y definio metodo post (los serializers retornan info en .json)
# para eso se creo serializer.py con una clase con los campos correpsondientes email etc.. 
#  en postman s eve si retorna este objeto  y se definen los metodos de creascion y vlaidar el username (va al modelo user y mira si existe)
# en view se instancia la clase serializer 

# 3. user appi e sla funcionalidad de, en urls se importa  