from django.urls import path
from . import views

urlpatterns = [
    path('create/', views.create_form, name='create_form'),
]
