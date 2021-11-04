from django.db import models
from django import forms

from authApp.models.Viajero import Viajero

class PaseAbordaje(models.Model):

    idPaseAbordaje = models.AutoField(primary_key=True)
    numeroVuelo_abordaje = models.IntegerField(null=True)
    gate_abordaje = models.IntegerField(null=True)
    asiento_abordaje = models.IntegerField(null=True)
    origen_abordaje = models.CharField( max_length = 30, null=True)
    destino_abordaje = models.CharField( max_length = 30, null=True)
    numeroConfirmacion_abordaje = models.IntegerField(null=True)
    aerolinea_abordaje = models.CharField( max_length = 30, null=True)

    horaAbordaje_abordaje = forms.TimeField()
    horaVuelo_abordaje = forms.TimeField()
    
    grupo_abordaje = models.IntegerField(null=True)
    fechaAbordaje_viajero = models.DateTimeField(null=True)

    viajero = models.ForeignKey(Viajero, null=True, on_delete=models.CASCADE)

    def datosAbordaje(self):
        return "{} {} {} ".format(self.numeroVuelo_abordaje,self.asiento_abordaje,self.destino_abordaje)

    def __str__(self) :
        return self.datosAbordaje()
        

