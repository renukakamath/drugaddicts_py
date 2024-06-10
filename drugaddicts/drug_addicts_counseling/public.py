from flask import *
from database import *

public=Blueprint('public',__name__)

@public.route('/')
def home():
	return render_template('index.html')

@public.route('/login',methods=['get','post'])
def login():
	if "login" in request.form:
		uname=request.form['un']
		pwd=request.form['pa']
		q="select * from login where username='%s' and password='%s'"%(uname,pwd)
		res=select(q)
		print(res)
		if res:
			session['login_id']=res[0]['login_id']
			if res[0]['usertype']=="admin":
				flash("login successfully")

				return redirect(url_for('admin.adminhome'))


			elif res[0]['usertype']=="psychologist":
				q1="select * from psychologist where login_id='%s'"%(res[0]['login_id'])
				res1=select(q1)
				session['psychologist_id']=res1[0]['psychologist_id']
				flash("login successfully")
				return redirect(url_for('psychologist.psychologisthome'))

			elif res[0]['usertype']=="farmer":
				q1="select * from farmers where login_id='%s'"%(res[0]['login_id'])
				res1=select(q1)
				session['farmerid']=res1[0]['farmer_id']
				flash("login successfully")
				return redirect(url_for('farmer.farmerhome'))

			
	return render_template('login.html')


@public.route('/farmerregister',methods=['get','post'])
def farmerregister():
	if "register" in request.form:
		f=request.form['fname']
		l=request.form['lname']
		pl=request.form['place']
		pin=request.form['pin']
		g=request.form['gen']
		d=request.form['dob']
		ph=request.form['phone']
		em=request.form['email']
		l=request.form['lat']
		lo=request.form['lon']
		uname=request.form['uname']
		pwd=request.form['pwd']

		ql="insert into login values(null,'%s','%s','farmer')"%(uname,pwd)
		rl=insert(ql)
		qs="insert into farmers values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(rl,f,l,pl,pin,g,d,ph,em,l,lo)
		insert(qs)
		flash("register successfully")
		return redirect(url_for('public.farmerregister'))
		
	return render_template('farmerregister.html')