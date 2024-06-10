package com.example.drug_addicts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class userviewmedicineandmedition extends AppCompatActivity implements JsonResponse {

    RadioButton r1,r2;
    ListView l1;
    SharedPreferences sh;
    String[] details,file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewmedicineandmedition);

        r1=(RadioButton)findViewById(R.id.radioButton1);
        r2=(RadioButton)findViewById(R.id.radioButton2);
        l1=(ListView) findViewById(R.id.lvmedi);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) userviewmedicineandmedition.this;
                String q = "/view_medicine?bid="+viewmybooking.bid;
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) userviewmedicineandmedition.this;
                String q = "/view_meditation?bid="+viewmybooking.bid;
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });

    }

    @Override
    public void response(JSONObject jo) {

        try {
            String method = jo.getString("method");
            Log.d("pearl", method);

            if (method.equalsIgnoreCase("view_medi")) {

                String status = jo.getString("status");
                Log.d("pearl", status);

                if (status.equalsIgnoreCase("success")) {
//                    l1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_LONG).show();
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    details = new String[ja1.length()];
                    file = new String[ja1.length()];


                    for (int i = 0; i < ja1.length(); i++) {
                        details[i] = ja1.getJSONObject(i).getString("details");
                        file[i] = ja1.getJSONObject(i).getString("file");

//                        value[i] = "\nFirst Name: " + fname[i] + "\nGender: " + gender[i] + "\nDOB: " + dob[i] + "\nHouse Name: " + housename[i] + "\nPlace: " + place[i] + "\nPincode: " + pincode[i] + "\nPhone: " + phone[i] + "\nEmail: " + email[i];

                    }
                    Custimage cc = new Custimage(this, file, details);
                    l1.setAdapter(cc);
                }
                else{
                    l1.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_LONG).show();
                }
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }
}