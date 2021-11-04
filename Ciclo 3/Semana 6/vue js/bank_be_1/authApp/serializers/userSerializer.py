from rest_framework import serializers
from authApp.models.user import User
from authApp.models.account import Account
from authApp.serializers.accountSerializer import AccountSerializer


"""
primera parte de la definición del modelo y los campos a utilizar en la subclase Meta, es similar al
caso de AccountSerializer  La inclusión de este campo y su definición, con ayuda de
AccountSerializer, permite manejar la relación entra ambas entidades. 

create, este método crea el usuario con su respectiva
cuenta simultáneamente, esto permite que a nivel del usuario final del componente se tenga únicamente
el usuario, pero a nivel de modelos se tenga la división deseada (usuarios con datos personales y cuenta
con datos financieros).
    
"""

class UserSerializer(serializers.ModelSerializer):
    account = AccountSerializer()
    class Meta:
        model = User
        fields = ['id', 'username', 'password', 'name', 'email', 'account']

    def create(self, validated_data):
        accountData = validated_data.pop('account')
        userInstance = User.objects.create(**validated_data)
        Account.objects.create(user=userInstance, **accountData)
        return userInstance

    def to_representation(self, obj):
        user = User.objects.get(id=obj.id)
        account = Account.objects.get(user=obj.id)
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