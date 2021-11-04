from django.shortcuts import render

from django.http import HttpResponse


def home(request):
    return render(request,'home.html')

def login(request):
    return render(request,'login.html')
    

def register(request):
    return render(request,'register.html')

def welcome(request):
    return render(request,'welcome.html')




# paleta de color https://color.adobe.com/es/search?q=travel  

#9AC7D9
#205929
#5AA646
#D9B779
