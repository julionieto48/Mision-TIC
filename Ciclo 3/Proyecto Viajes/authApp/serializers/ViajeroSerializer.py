# cuenta = informacion de viajero

from authApp.models.Viajero import Viajero
from rest_framework import serializers

class ViajeroSerializer(serializers.ModelSerializer):
    class Meta:
        model = Viajero
        fields = [' idViajero', 'Usuario', 'cedula','telefono ',' fechaDeNacimiento','isVip', 
                  'direccion', 'rh_viajero','eps_viajero']