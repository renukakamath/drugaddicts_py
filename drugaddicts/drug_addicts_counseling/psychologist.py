from flask import *
from database import *
import uuid

psychologist=Blueprint('psychologist',__name__)

@psychologist.route('/psychologisthome')
def psychologisthome():
	return render_template('psychologisthome.html')



@psychologist.route('/psychologistviewblog',methods=['get','post'])
def psychologistviewblog():
	data={}
	q="SELECT * FROM `blog`"
	r=select(q)
	data['view']=r
	return render_template('psychologistviewblog.html',data=data)


@psychologist.route('/psychologistviewbooking',methods=['get','post'])
def psychologistviewbooking():
	data={}
	psychologist_id=session['psychologist_id']
	q="SELECT *,psychologist.firstname as fn,psychologist.lastname as ln FROM `psychologist` inner join booking using(psychologist_id)inner join user using(user_id) where psychologist_id='%s'"%(psychologist_id)
	r=select(q)
	data['view']=r
	return render_template('psychologistviewbooking.html',data=data)


@psychologist.route('/psychologistviewuserdetails',methods=['get','post'])
def psychologistviewuserdetails():
	data={}
	bid=request.args['bid']

	q="SELECT * FROM `user` where user_id='%s'"%(bid)
	r=select(q)
	data['view']=r
	return render_template('psychologistviewuserdetails.html',data=data)


@psychologist.route('/psychologistscheduletime',methods=['get','post'])
def psychologistscheduletime():
	data={}
	bid=request.args['bid']

	q="SELECT * FROM `schedule` where booking_id='%s'"%(bid)
	r=select(q)
	data['view']=r
	if "add" in request.form:
		st=request.form['st']
		q="insert into schedule values(null,'%s','%s')"%(bid,st)
		insert(q)
		flash("time is scheduled successfully")
		return redirect(url_for('psychologist.psychologistscheduletime',bid=bid))
	return render_template('psychologistscheduletime.html',data=data)



@psychologist.route('/psychologistaddmedicineandmedition',methods=['get','post'])
def psychologistaddmedicineandmedition():
	data={}
	bid=request.args['bid']

	q="SELECT *,medicine.`details` AS medicine_details ,medicine.`file` AS medicine_file ,meditation.`file` AS meditation_file ,meditation.`details` AS meditation_details FROM `medicine` INNER JOIN `meditation` USING(booking_id) where booking_id='%s'"%(bid)
	r=select(q)
	data['view']=r
	if "add" in request.form:
		medi=request.form['medi']
		mef=request.files['mef']
		med=request.form['med']
		mf=request.files['mf']
		path="static/assets/img/"+str(uuid.uuid4())+mef.filename
		mef.save(path)
		path1="static/assets/img/"+str(uuid.uuid4())+mf.filename
		mf.save(path1)
		q="insert into medicine values(null,'%s','%s','%s')"%(bid,medi,path)
		insert(q)
		q="insert into meditation values(null,'%s','%s','%s')"%(bid,med,path1)
		insert(q)
		flash("time is scheduled successfully")
		return redirect(url_for('psychologist.psychologistaddmedicineandmedition',bid=bid))
	return render_template('psychologistaddmedicineandmedition.html',data=data)

@psychologist.route('/psychologistchat',methods=['get','post'])
def psychologistchat():
	data={}
	bid=request.args['bid']

	q="SELECT * FROM `user` where user_id='%s'"%(bid)
	r=select(q)
	data['view']=r
	return render_template('psychologistchat.html',data=data)

@psychologist.route('/psychologistchatwithuser',methods=['get','post'])
def psychologistchatwithuser():
	data={}
	lid=request.args['lid']
	logid=session['login_id']
	data['logid']=logid

	q="SELECT * FROM `chat` where (sender_id='%s' and receiver_id='%s') or (sender_id='%s' and receiver_id='%s')"%(logid,lid,lid,logid)
	r=select(q)
	data['view']=r
	if "add" in request.form:
		msg=request.form['msg']
		q="insert into chat values(null,'%s','%s','%s',curdate())"%(logid,lid,msg)
		insert(q)
		return redirect(url_for('psychologist.psychologistchatwithuser',lid=lid))
	return render_template('psychologistchatwithuser.html',data=data)

