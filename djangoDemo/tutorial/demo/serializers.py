from rest_framework import serializers
from tutorial.demo.models import NameCat


class NameCatSerializer(serializers.ModelSerializer):
    class Meta:
        model = NameCat
        fields = ['id', 'name', 'cat']
