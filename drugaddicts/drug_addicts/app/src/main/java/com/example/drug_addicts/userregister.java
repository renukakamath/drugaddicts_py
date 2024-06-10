package com.example.drug_addicts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class userregister extends AppCompatActivity implements JsonResponse {
    EditText e1,e2,e3,e4,e5,e6,e7;
    Button b1;
    String fname,lname,place,phone,email,uname,password;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText)findViewById(R.id.fn);
        e2=(EditText)findViewById(R.id.ln);
        e3=(EditText)findViewById(R.id.pl);
        e4=(EditText)findViewById(R.id.ph);
        e5=(EditText)findViewById(R.id.e);
        e6=(EditText)findViewById(R.id.une);
        e7=(EditText)findViewById(R.id.pwd);
        b1=(Button)findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                phone=e4.getText().toString();
                email=e5.getText().toString();
                uname=e6.getText().toString();
                password=e7.getText().toString();

//                Toast.makeText(getApplicationContext(),"firstname:"+fname+"lastname:"+lname+"place:"+place+"phone:"+phone+"email:"+email+"username:"+uname+"password:"+password,Toast.LENGTH_LONG).show();
//               startActivity(new Intent(getApplicationContext(),login.class));
                if (fname.equalsIgnoreCase(""))
                {
                    e1.setError("enter firstname");
                    e1.setFocusable(true);
                }
                else if (uname.equalsIgnoreCase(""))
                {
                    e2.setError("enter lastname");
                    e2.setFocusable(true);
                }
                else if (place.equalsIgnoreCase(""))
                {
                    e3.setError("enter place");
                    e3.setFocusable(true);
                } else if (phone.equalsIgnoreCase(""))
                {
                    e4.setError("enter phone");
                    e4.setFocusable(true);
                } else if (email.equalsIgnoreCase(""))
                {
                    e5.setError("enter email");
                    e5.setFocusable(true);
                }
                else if (uname.equalsIgnoreCase(""))
                {
                    e6.setError("enter username");
                    e6.setFocusable(true);
                }
                else if (password.equalsIgnoreCase(""))
                {
                    e7.setError("enter password");
                    e7.setFocusable(true);

                }

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) userregister.this;
                    String q = "/register?firstname=" +fname+ "&lastname=" +lname+ "&place="+place+"&phone="+phone+"&email="+email+"&username=" + uname + "&password=" + password ;
                    q = q.replace(" ", "%20");
                    JR.execute(q);


            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status=jo.getString("status");
            Log.d("pearl",status);

            if(status.equalsIgnoreCase("success")){

                Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),login.class));

            }
            else if(status.equalsIgnoreCase("duplicate")){


                startActivity(new Intent(getApplicationContext(), userregister.class));
                Toast.makeText(getApplicationContext(), "Username already Exist...", Toast.LENGTH_LONG).show();

            }
            else
            {
                startActivity(new Intent(getApplicationContext(), userregister.class));

                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }



    }
}