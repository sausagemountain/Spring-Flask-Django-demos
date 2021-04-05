from flask import request
from app import demoApp

objects_repo = dict()


@demoApp.route('/objects')
@demoApp.route('/objects/<record_id>')
def objects_get(record_id=None):
    if record_id is not None:
        return objects_repo.get(record_id, {'status': f'no object with id {record_id}'})
    else:
        return objects_repo


@demoApp.route('/objects/<record_id>', methods=['POST'])
def objects_post(record_id):
    objects_repo[record_id] = request.json
    return {'status': 'ok'}
