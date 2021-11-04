# maneja los datos de tiquetes , pases de abordaje y planes turisticos

from django.db import models
from .Usuario import Usuario

class Viajero(models.Model):
    idViajero = models.AutoField(primary_key=True)
    Usuario = models.ForeignKey(Usuario, related_name='viajero', on_delete=models.CASCADE)
    cedula_viajero = models.IntegerField()
    telefono_viajero = models.IntegerField(default=0)
    fechaDeNacimiento_viajero = models.DateTimeField()
    isVip_viajero = models.BooleanField(default=False)
    direccion_viajero = models.CharField('Direccion', max_length = 30)
    rh_viajero = models.CharField('RH', max_length = 30)
    eps_viajero = models.CharField('EPS', max_length = 30)
