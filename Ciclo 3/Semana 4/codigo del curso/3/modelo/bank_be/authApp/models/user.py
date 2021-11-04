from django.db import models
from django.contrib.auth.models import AbstractBaseUser, PermissionsMixin, BaseUserManager
from django.contrib.auth.hashers import make_password



"""
UserManager, esta clase es un mecanismo que provee Django REST para administrar la
manera en la que se crean los usuarios en el sistema de autenticación

métodos para la creación de usuarios y super-usuarios, pero para este
caso el componente únicamente manejara usuarios

permite a Djang oREST
identificar las credenciales de los usuarios y brindarles un mecanismo de seguridad adecuado

"""

class UserManager(BaseUserManager):
    def create_user(self, username, password=None):
        """
        Creates and saves a user with the given username and password.
        """
        if not username:
            raise ValueError('Users must have an username')
        user = self.model(username=username)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, username, password):
        """
        Creates and saves a superuser with the given username and password.
        """
        user = self.create_user(
        username=username,
        password=password,
        )
        user.is_admin = True
        user.save(using=self._db)
        return user


"""
AbstractBaseUser y PermissionsMixin, estas clases permiten crear un modelo de usuario básico con
autenticación.

"""

class User(AbstractBaseUser, PermissionsMixin):
    id = models.BigAutoField(primary_key=True)
    username = models.CharField('Username', max_length = 15, unique=True)
    password = models.CharField('Password', max_length = 256)
    name = models.CharField('Name', max_length = 30)
    email = models.EmailField('Email', max_length = 100)

    def save(self, **kwargs):
        some_salt = 'mMUj0DrIK6vgtdIYepkIxN'
        self.password = make_password(self.password, some_salt)
        super().save(**kwargs)  # Debido a que se está trabajando con un campo sensible como es el como es el password, se debe sobrescribir el método de guardado (save) 
 

    # asociar el UserManager al modelo User
    objects = UserManager()
    USERNAME_FIELD = 'username'

