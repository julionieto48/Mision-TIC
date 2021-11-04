from authApp.models.account import Account
from rest_framework import serializers

 

"""
  A partir del modelo Account se creará el respectivo AccountSerializer, su objetivo principal es servir de
  auxiliar al UserSerializer.
    
"""



class AccountSerializer(serializers.ModelSerializer):
    class Meta:
        model = Account
        fields = ['balance', 'lastChangeDate', 'isActive']