from peewee import SqliteDatabase, CharField, Model
from app import demoApp

database_name = "flask-demo.db"
database = SqliteDatabase(database_name)


@demoApp.before_request
def before_request():
    database.connect()


@demoApp.after_request
def after_request(response):
    database.close()
    return response


class BaseModel(Model):
    class Meta:
        database = database


class NameCat(BaseModel):
    record_id = CharField(40, unique=True)
    name = CharField(50)
    cat = CharField(50)

    def __init__(self, record_id, name, cat, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.record_id = record_id
        self.cat = cat
        self.name = name

    def keys(self):
        return ['record_id', 'name', 'cat']

    def __getitem__(self, item):
        return vars(self).get("__data__", {}).get(item, None)

    def __setitem__(self, item, value):
        if item == 'cat':
            self.cat = value
        if item == 'name':
            self.name = value


if not database.table_exists(NameCat):
    database.create_tables([NameCat])
