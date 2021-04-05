from django.db import models


class NameCat(models.Model):
    name = models.CharField(max_length=40)
    cat = models.CharField(max_length=50)
