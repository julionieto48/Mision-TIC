from django.db import models
from .user import User

"""
no necesitar autenticación se puede definir con las clases bases
o estándares establecidas por Django REST. Sin embargo, tiene un pequeño detalle y es que en este
modelo se debe definir la relación entre las entidades usuario y cuenta, la relación entre estas es 1 a 1
y si bien dicha relación puede presentar un nivel de abstracción alto en la implementación

"""

class Account(models.Model):
    id = models.AutoField(primary_key=True)
    user = models.ForeignKey(User, related_name='account', on_delete=models.CASCADE)   
    # El atributo user es un atributo de tipo ForeignKey y permite realizar una referencia al modelo User, lo cual permite establecer la relación.

    balance = models.IntegerField(default=0)
    lastChangeDate = models.DateTimeField()
    isActive = models.BooleanField(default=True)