from flask import Flask

demoApp = Flask(__name__)

from . import database
from . import home
from . import controller
from . import object_storage

if __name__ == '__main__':
    demoApp.run()
