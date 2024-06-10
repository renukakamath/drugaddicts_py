package com.example.drug_addicts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserViewOthersPost extends AppCompatActivity implements JsonResponse {

    ListView l1;
    String[] file, desc, date;
    SharedPreferences sh;
    String aids,phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_others_post);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = (ListView) findViewById(R.id.lvpost);
//        l1.setOnItemClickListener(this);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) UserViewOthersPost.this;
        String q = "/viewotherspost?lid="+sh.getString("log_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);


    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");
            Log.d("pearl", method);

            if (method.equalsIgnoreCase("view_details")) {
                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
//                      startActivity(new Intent(getApplicationContext(),staffhome.class));
                }
            }
            else if (method.equalsIgnoreCase("viewotherspost")) {
                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    file = new String[ja1.length()];
                    desc = new String[ja1.length()];
                    date = new String[ja1.length()];


                    for (int i = 0; i < ja1.length(); i++) {
                        file[i] = ja1.getJSONObject(i).getString("file");
                       desc[i] = ja1.getJSONObject(i).getString("description");
                        date[i] = ja1.getJSONObject(i).getString("date");

//                        value[i] = "\nFirst Name: " + fname[i] + "\nGender: " + gender[i] + "\nDOB: " + dob[i] + "\nHouse Name: " + housename[i] + "\nPlace: " + place[i] + "\nPincode: " + pincode[i] + "\nPhone: " + phone[i] + "\nEmail: " + email[i];

                    }
                    Custimage cc = new Custimage(this, file, desc);
                    l1.setAdapter(cc);
                }
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}