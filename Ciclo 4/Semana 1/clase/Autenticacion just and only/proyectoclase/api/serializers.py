from rest_framework import serializers  # retorna en json deacuerod a las peticoines
from django.contrib.auth.models import User
from rest_framework.fields import CharField

class UserSerializer(serializers.Serializer):
    id = serializers.ReadOnlyField() # solo lectura
    primer_nombre = serializers.CharField()
    segundo_nombre = serializers.CharField()
    username = serializers.CharField()
    email = serializers.EmailField()
    password = serializers.CharField()

    def create(self, validated_data): # metodo de registro en bd validated data tiene la info que se envia desde el request
        instance = User()
        instance.first_name = validated_data.get('primer_nombre')
        instance.last_name = validated_data.get('segundo_nombre')
        instance.username = validated_data.get('username')
        instance.email = validated_data.get('email')
        instance.set_password(validated_data.get('password')) ; instance.save()

        return instance

    # validar corroborar la info
    def validate_username(self,data):
        users = User.objects.filter(username = data)
        if len(users) != 0:
            raise serializers.ValidationError('__el usuario ya existe__')
        else:
            return data

        