from flask import Flask
from public import public
from admin import admin
from psychologist import psychologist
from api import api

app=Flask(__name__)

app.secret_key="abc"

app.register_blueprint(public)
app.register_blueprint(admin,url_prefix='/admin')
app.register_blueprint(psychologist,url_prefix='/psychologist')
app.register_blueprint(api,url_prefix='/api')

app.run(debug=True,port=5098,host="0.0.0.0")
