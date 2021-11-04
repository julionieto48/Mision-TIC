# user = usuario es la info de credenciales 

from rest_framework import serializers
from authApp.models.Usuario import Usuario
from authApp.models.Viajero import Viajero
from authApp.serializers.ViajeroSerializer import ViajeroSerializer


"""
primera parte de la definición del modelo y los campos a utilizar en la subclase Meta, es similar al
caso de AccountSerializer  La inclusión de este campo y su definición, con ayuda de
AccountSerializer, permite manejar la relación entra ambas entidades. 

create, este método crea el usuario con su respectiva
cuenta simultáneamente, esto permite que a nivel del usuario final del componente se tenga únicamente

el usuario, pero a nivel de modelos se tenga la división deseada .
    
"""

class UsuarioSerializer(serializers.ModelSerializer):
    account = ViajeroSerializer()
    class Meta:
        model = Usuario
        fields = ['id', 'username', 'password', 'name', 'email', 'account']

    def create(self, validated_data):
        accountData = validated_data.pop('account')
        userInstance = Usuario.objects.create(**validated_data) 
        Viajero.objects.create(user=userInstance, **accountData)
        return userInstance

    def to_representation(self, obj):
        user = Usuario.objects.get(id=obj.id)
        account = Viajero.objects.get(user=obj.id)
        return {
            'id': user.id,
            'username': user.username,
            'name': user.name,
            'email': user.email,
            'account': {
                'id': account.id,
                'balance': account.balance,
                'lastChangeDate': account.lastChangeDate,
                'isActive': account.isActive
            }
        }