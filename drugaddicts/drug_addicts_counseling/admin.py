from flask import *
from database import *

admin=Blueprint('admin',__name__)

@admin.route('/adminhome')
def adminhome():
	return render_template('adminhome.html')

@admin.route('/adminmanagepsychologist',methods=['get','post'])
def adminmanagepsychologist():
	data={}
	q="select * from psychologist"
	r=select(q)
	data['view']=r
	if "register" in request.form:
		fna=request.form['f']
		lna=request.form['l']
		pla=request.form['pl']
		pho=request.form['ph']
		em=request.form['e']
		de=request.form['desg']
		uname=request.form['uname']
		pwd=request.form['p']
		q="select * from login where username='%s'"%(uname)
		res=select(q)
		if res:
			flash("username is already exist")
		else:
			ql="insert into login values(null,'%s','%s','psychologist')"%(uname,pwd)
			id=insert(ql)
			qs="insert into psychologist values(null,'%s','%s','%s','%s','%s','%s','%s')"%(id,fna,lna,em,pho,pla,de)
			insert(qs)
			print(qs)
			flash("added successfully")
			return redirect(url_for('admin.adminmanagepsychologist'))
	if "action" in request.args:
		action=request.args['action']
		tid=request.args['tid']
	else:
		action=None
	if "update" in request.form:
		fna=request.form['f']
		lna=request.form['l']
		pla=request.form['pl']
		pho=request.form['ph']
		em=request.form['e']
		de=request.form['desg']
		
		q="update psychologist set  firstname='%s',lastname='%s',place='%s',phone='%s',email='%s',designation='%s' where psychologist_id='%s'"%(fna,lna,pla,pho,em,de,tid)
		r=update(q)
		
		flash("update successfully")
		return redirect(url_for('admin.adminmanagepsychologist'))
	if action=="update":
		q="select * from  psychologist where psychologist_id='%s'"%(tid)
		r=select(q)
		data['updates']=r
	if action=="delete":
		q="delete from psychologist  where psychologist_id='%s'"%(tid)
		update(q)
		
		flash("delete successfully")

		return redirect(url_for('admin.adminmanagepsychologist'))
	
	return render_template('adminmanagepsychologist.html',data=data)




@admin.route('/adminviewblog',methods=['get','post'])
def adminviewblog():
	data={}
	q="SELECT * FROM `blog`"
	r=select(q)
	data['view']=r
	return render_template('adminviewblog.html',data=data)



@admin.route('/adminviewcomplaint',methods=['get','post'])
def adminviewcomplaint():
	data={}
	q="SELECT * FROM complaint INNER JOIN user using (user_id)"
	r=select(q)
	data['complaints']=r
	return render_template('adminviewcomplaint.html',data=data)

@admin.route('/adminsendreply',methods=['get','post'])
def adminsendreply():
	if "send" in request.form:
		r=request.form['r']
		cid=request.args['cid']
		q="update complaint set reply='%s' where complaint_id='%s'"%(r,cid)
		delete(q)
		flash("send successfully")
		return redirect(url_for('admin.adminviewcomplaint'))

	return render_template('adminsendreply.html')


@admin.route('/adminviewuser',methods=['get','post'])
def adminviewuser():
	data={}
	q="SELECT * FROM `user`"
	r=select(q)
	data['view']=r
	return render_template('adminviewuser.html',data=data)



