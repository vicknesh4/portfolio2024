from django.db import models

class Users(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100)
    email = models.CharField(max_length=100)
    password = models.CharField(max_length=100)
    statut = models.IntegerField(null= False)
    hours = models.IntegerField(null = False)

    def __str__(self):
        return self.name

class Hours(models.Model):
    id = models.AutoField(primary_key=True)
    id_user = models.IntegerField(null= False)
    date = models.DateTimeField("date de l'heure")
    id_instructor = models.IntegerField(null = False)
    duration = models.IntegerField(null = False)

    def __str__(self):
        return self.name
