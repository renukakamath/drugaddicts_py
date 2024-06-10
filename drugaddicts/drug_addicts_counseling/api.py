from flask import *
from database import *
import demjson
import uuid

api=Blueprint('api',__name__)


@api.route('/login')
def login():
	data={}
	username=request.args['username']
	password=request.args['password']
	q="select * from login where username='%s' and password='%s'"%(username,password)
	r=select(q)
	if r:
		data['status']="success"
		data['data']=r
	else:
		data['status']="failed"
	return demjson.encode(data)

@api.route('/register')
def register():
	data={}
	f=request.args['firstname']
	l=request.args['lastname']
	pl=request.args['place']
	ph=request.args['phone']
	e=request.args['email']
	username=request.args['username']
	password=request.args['password']
	q="insert into login values(null,'%s','%s','user')"%(username,password)
	r=insert(q)
	q="insert into user values(null,'%s','%s','%s','%s','%s','%s')"%(r,f,l,e,ph,pl)
	r=insert(q)
	data['status']="success"
	return demjson.encode(data)


@api.route('/userviewprofile')
def userviewprofile():
	data={}
	login_id=request.args['login_id']
	q="SELECT * from `user`  where  login_id='%s'"%(login_id)
	r=select(q)
	if r:
		data['status']="success"
		data['data']=r
	else:
		data['status']="failed"
	data['method']="userviewprofile"
	return demjson.encode(data)

@api.route('/userviewpsychologist')
def userviewpsychologist():
	data={}
	q="select * from psychologist"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="success"
	data['method']="userviewpsychologist"
	return demjson.encode(data)


@api.route('/useraddbookings')
def useraddbookings():
	data={}
	psycho_id=request.args['psycho_id']
	lid=request.args['login_id']
	q="insert into booking values(null,(select user_id from user where login_id='%s'),'%s',curdate(),'booked')"%(lid,psycho_id)
	res=insert(q)
	data['status']="success"
	data['method']="useraddbookings"
	return demjson.encode(data)

@api.route('/viewmybooking')
def viewmybooking():
	data={}
	# psycho_id=request.args['psycho_id']
	login_id=request.args['login_id']

	q="select * from psychologist inner join booking using (psychologist_id) where user_id=(select user_id from user where login_id='%s')"%(login_id)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="success"
	data['method']="viewmybooking"
	return demjson.encode(data)



@api.route('/userviewsheduletime')
def userviewsheduletime():
	data={}
	bid=request.args['bid']

	q="select * from schedule inner join booking using (booking_id) where booking_id='%s'"%(bid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="userviewsheduletime"
	return demjson.encode(data)

@api.route('/view_medicine')
def view_medicine():
	data={}
	bid=request.args['bid']

	q="select * from medicine where booking_id='%s'"%(bid)
	res=select(q)

	
	if res:
		data['status']="success"	
		data['data']=res
	else:
		data['status']="failed"
	data['method']="view_medi"
	return demjson.encode(data)

@api.route('/view_meditation')
def view_meditation():
	data={}
	bid=request.args['bid']

	q="select * from meditation where booking_id='%s'"%(bid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="view_medi"
	return demjson.encode(data)

@api.route('/chatdetail')
def chatdetail():
	data={}
	sender_id=request.args['sender_id']
	receiver_id=request.args['receiver_id']

	q="select * from chat where (sender_id='%s' and receiver_id='%s') or (sender_id='%s' and receiver_id='%s') " %(sender_id,receiver_id,receiver_id,sender_id)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="chatdetail"
	return demjson.encode(data)


@api.route('/chat')
def chat():
	data={}
	sender_id=request.args['sender_id']
	receiver_id=request.args['receiver_id']
	details=request.args['details']
	
	q="insert into chat values(null,'%s','%s','%s',curdate())"%(sender_id,receiver_id,details)
	r=insert(q)
	data['status']="success"
	data['method']="chat"
	return demjson.encode(data)



@api.route('/customerviewpost')
def customerviewpost():
	data={}
	lid=request.args['lid']

	q="select * from blog where user_id=(select user_id from user where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="customerviewpost"
	return demjson.encode(data)

@api.route('/viewotherspost')
def viewotherspost():
	data={}
	lid=request.args['lid']

	q="select * from blog where user_id !=(select user_id from user where login_id='%s')"%(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="viewotherspost"
	return demjson.encode(data)

@api.route('/newuploadfile',methods=['get','post'])
def newuploadfile():
	data={}
	print("HAii")
	image=request.files['image']
	path="static/uploads/"+str(uuid.uuid4())+image.filename
	image.save(path)
	lid=request.form['lid']
	desc=request.form['desc']
	q="insert into blog values(null,(select user_id from user where login_id='%s'),'%s','%s',curdate())"%(lid,path,desc)
	print(q)
	res=insert(q)
	data['status']="success"
	data['method']="newuploadfile"
	return demjson.encode(data)

