from rest_framework import status, views
from rest_framework.response import Response
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from authApp.serializers.userSerializer import UserSerializer


"""

UserCreateView  abstrae de APIView

cuando una vista recibe una petición HTTP, esta
se encarga de ejecutar una función u otra de acuerdo con el método HTTP de la petición

la función post se encarga de tomar los datos de la petición HTTP, verificar que siguen
el formato que el UserSerializer requiere, y crear el usuario en el sistema. Una vez lo crea, inicia la sesión y
retorna el access token y el refresh token al usuario.

"""

class UserCreateView(views.APIView):
    def post(self, request, *args, **kwargs):
        serializer = UserSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        tokenData = {"username":request.data["username"],
        "password":request.data["password"]}
        tokenSerializer = TokenObtainPairSerializer(data=tokenData)
        tokenSerializer.is_valid(raise_exception=True)
        return Response(tokenSerializer.validated_data, status=status.HTTP_201_CREATED)