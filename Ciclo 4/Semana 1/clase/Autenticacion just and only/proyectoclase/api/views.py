

from rest_framework import status  # retorna los estados http
from rest_framework import response

from rest_framework.views import APIView
from .serializers import UserSerializer

class UserAPI(APIView):
    def post(self,request):
        serializer = UserSerializer(data = request.data)  #request.data captura la info
        if serializer.is_valid():
            user = serializer.save()
            return response(serializer.data, status = status.HTTP_201_CREATED)
        else:
            return response(serializer.errors, status = status.HTTP_400_BAD_REQUEST) 
