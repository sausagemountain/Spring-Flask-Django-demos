from rest_framework import viewsets, mixins
from rest_framework.decorators import api_view, action
from rest_framework.response import Response

from tutorial.demo.models import NameCat
from tutorial.demo.serializers import NameCatSerializer


class NameCatViewSet(viewsets.ModelViewSet):
    queryset = NameCat.objects.all()
    serializer_class = NameCatSerializer


random_objects = {}


class ObjectsViewSet(viewsets.ViewSet):
    def list(self, request, *args, **kwargs):
        return Response(random_objects)

    def retrieve(self, request, *args, **kwargs):
        return Response(random_objects[kwargs['num']])

    def create(self, request, *args, **kwargs):
        random_objects[kwargs['num']] = request.data
        return Response({"status": "ok"})


@api_view()
def home(request):
    return Response(f"Hello {request.query_params.get('name', 'World')}! Demonstration Successful!")


@api_view(['GET', 'POST', 'PUT', 'PATCH', 'DELETE'])
def json_test(request, *args, **kwargs):
    return Response({
        "input_param": request.query_params.get('input', ''),
        "path_param": kwargs['urlparam'],
        "request_method": request.method
    })

