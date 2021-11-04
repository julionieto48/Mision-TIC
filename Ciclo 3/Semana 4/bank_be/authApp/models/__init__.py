"""
dos entidades, la entidad usuario (User) y la entidad cuenta
(Account), la entidad usuario representará los datos personales del usuario, y la entidad cuenta
representará la información bancaria del usuario. Estas dos entidades tendrán una relación de 1 a 1, es
decir, un usuario tiene una única cuenta y una cuenta solamente pertenece a un usuario. Ambas
entidades serán representadas con sus respectivos modelos,
    
"""
# Ahora que se ha finalizado la implementación de los modelos, se debe realizar una serie de pasos que permitan integrarlos al módulo y a la aplicación. ...

from .account import Account
from .user import User

from django.contrib import admin

admin.site.register(User)    # registrar en la aplicación.
admin.site.register(Account) 