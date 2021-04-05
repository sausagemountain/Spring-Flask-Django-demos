from flask.views import MethodView
from flask import request
from app import demoApp
import uuid
from app.database import BaseModel, NameCat


class CrudView(MethodView):
    table_class = BaseModel

    def __init__(self, table_class):
        self.table_class = table_class

    def get(self, record_id=None):
        if record_id is None:
            return self.__get_all__()
        else:
            return self.__get_one__(record_id)

    def __get_all__(self):
        return {'records': [{**i} for i in self.table_class.select()]}

    def __get_one__(self, record_id):
        records = list(self.table_class.select().where(self.table_class.record_id == record_id))
        if len(records) == 1:
            record = records[0]
            return {**record}
        else:
            return {'status': f'no record with id {record_id}'}

    def post(self):
        content = {**request.json}
        record_id = str(uuid.uuid1())
        content['record_id'] = record_id
        record = self.table_class.create(**content)
        record.save()
        return {**record}

    def patch(self, record_id):
        return self.put(record_id)

    def put(self, record_id):
        content = {**request.json}
        records = list(self.table_class.select().where(self.table_class.record_id == record_id))
        if len(records) == 1:
            record = records[0]
            for k, v in content.items():
                record[k] = v
            record.save()
            return {**record}
        else:
            return {'status': f'no record with id {record_id}'}

    def delete(self, record_id):
        records = (self.table_class.select().where(self.table_class.record_id == record_id))
        if len(records) == 1:
            record = records[0]
            record.delete_instance()
            return {'status': 'ok'}
        else:
            return {'status': f'no record with id {record_id}'}


demoApp.add_url_rule('/api/demo/', view_func=CrudView.as_view('demo_api_1', table_class=NameCat))
demoApp.add_url_rule('/api/demo/<record_id>/', view_func=CrudView.as_view('demo_api_2', table_class=NameCat))
