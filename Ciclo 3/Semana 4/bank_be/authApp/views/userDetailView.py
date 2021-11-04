from django.conf import settings
from rest_framework import generics, status
from rest_framework.response import Response
from rest_framework_simplejwt.backends import TokenBackend
from rest_framework.permissions import IsAuthenticated
from authApp.models.user import User
from authApp.serializers.userSerializer import UserSerializer



"""
se definen 3 atributos: queryset, serializer_class y permission_classes  ... queryset y serializer_class) si se abstrae
de una clase de Django REST que implemente una funcionalidad, pues estos atributos indican a la clase de
Django REST, el modelo y el Serializer que debe utilizar dicha funcionalidad. 


get.   se encarga de tomar el id del usuario, y retornar su información solo si el access token


en el sistema no se permitirá a un usuario solicitar la
información de otro. Por ello, en el código se añade una verificación adicional, la cual toma el id del usuario
solicitado, y lo compara con el id del usuario al que representa el access token 

"""


class UserDetailView(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    permission_classes = (IsAuthenticated,) 
    # permission_classes, este se utiliza para indicarle a la clase de DjangoREST la necesidad de verificar que el usuario que hace la petición HTTP esté autenticado.

    def get(self, request, *args, **kwargs):
        token = request.META.get('HTTP_AUTHORIZATION')[7:]
        tokenBackend = TokenBackend(algorithm=settings.SIMPLE_JWT['ALGORITHM'])
        valid_data = tokenBackend.decode(token,verify=False)
        if valid_data['user_id'] != kwargs['pk']:
            stringResponse = {'detail':'Unauthorized Request'}
            return Response(stringResponse, status=status.HTTP_401_UNAUTHORIZED)

        return super().get(request, *args, **kwargs)
