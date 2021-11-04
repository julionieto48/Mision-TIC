from django.contrib import admin

from .models.Usuario  import  Usuario
from .models.Viajero import Viajero 
from .models.PaseAbordaje import PaseAbordaje

admin.site.register(Usuario)
admin.site.register(Viajero)
admin.site.register(PaseAbordaje)




