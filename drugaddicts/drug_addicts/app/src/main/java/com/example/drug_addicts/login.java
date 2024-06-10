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

import org.json.JSONArray;
import org.json.JSONObject;

public class login extends AppCompatActivity implements JsonResponse{
    EditText e1,e2;
    Button b1,b2;
   public static String uname,password,logid,usertype;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText)findViewById(R.id.un);
        e2=(EditText)findViewById(R.id.pa);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button);

//        Toast.makeText(getApplicationContext(),"IP:"+IPsetting.ip,Toast.LENGTH_LONG).show();

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),userregister.class));
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=e1.getText().toString();
                password=e2.getText().toString();
                Toast.makeText(getApplicationContext(),"username:"+uname+"password:"+password,Toast.LENGTH_LONG).show();
//                startActivity(new Intent(getApplicationContext(),login.class));
                if (uname.equalsIgnoreCase("")) {
                    e1.setError("Enter username");
                    e1.setFocusable(true);
                } else if (password.equalsIgnoreCase("")) {
                    e2.setError("Enter password");
                    e2.setFocusable(true);
                } else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) login.this;
                    String q = "/login?username=" + uname + "&password=" + password;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);

            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                logid = ja1.getJSONObject(0).getString("login_id");
                usertype = ja1.getJSONObject(0).getString("usertype");

                SharedPreferences.Editor e = sh.edit();
                e.putString("log_id", logid);
                e.commit();
                if (usertype.equals("user")) {
                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),userhome.class));
                }
//                else  if (usertype.equals("user")) {
//                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(getApplicationContext(),userhome.class));
//                }
//                else  if (usertype.equals("teacher")) {
//                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(getApplicationContext(),teacherhome.class));
//                }
            } else {
                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), login.class));
            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}