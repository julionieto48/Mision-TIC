# -*- coding: utf-8 -*-
"""Untitled2.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1egW1QJUbZm7Qhl5gE1pQW7Heow2b4K23
"""

def marvel(bruja):
  #upm
  capMarvel = (bruja * 2) + 4
  thanos = (capMarvel + bruja)/5

  nivel = 0
  if thanos >= 0 and thanos <= 20:
    nivel = 1  
  elif thanos >= 21 and thanos <= 30:
    nivel = 2  
  elif thanos >= 31 and thanos <= 50:
    nivel = 3 
  elif thanos > 50:
    nivel = 4  

  print(bruja,capMarvel,thanos)
  print(nivel)

entrada = 210
marvel(entrada)