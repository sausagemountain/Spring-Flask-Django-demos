from django.contrib import admin
from django.urls import path, include
from rest_framework import routers

from tutorial.demo.views import NameCatViewSet, ObjectsViewSet, home, json_test

router = routers.DefaultRouter()
router.register('api/demo', NameCatViewSet)


urlpatterns = [
    path('objects', ObjectsViewSet.as_view({'get': 'list'})),
    path('objects/<num>', ObjectsViewSet.as_view({'get': 'retrieve', 'post': 'create'})),
    path('admin/', admin.site.urls),
    path(r'one/<urlparam>', json_test),
    path('', home),
    path('', include(router.urls))
]
