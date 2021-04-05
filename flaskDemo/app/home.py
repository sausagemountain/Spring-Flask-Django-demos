from app import demoApp
from flask import request


@demoApp.route('/')
def hello_world():
    name = request.args.get('name', 'World')
    return f'Hello {name}! Demonstration Successful!'


@demoApp.route('/one/<path_param>', methods=['GET', 'POST', 'PUT', 'PATCH', 'DELETE'])
def returns_json(path_param):
    return {
        'path_param': path_param,
        'input_param': request.args.get('input', None),
        'request_method': request.method
    }
