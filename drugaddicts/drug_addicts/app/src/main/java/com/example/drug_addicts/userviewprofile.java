package com.example.drug_addicts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


public class userviewprofile extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    String[] fn, ln, pl, ph, em, value;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewprofile);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = (ListView) findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) userviewprofile.this;
        String q = "/userviewprofile?login_id="+sh.getString("log_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                //feedback_id=new String[ja1.length()];
                fn = new String[ja1.length()];
                ln = new String[ja1.length()];
                pl = new String[ja1.length()];
                ph = new String[ja1.length()];
                em = new String[ja1.length()];
                value = new String[ja1.length()];


                for (int i = 0; i < ja1.length(); i++) {
                    fn[i] = ja1.getJSONObject(i).getString("firstname");
                    ln[i] = ja1.getJSONObject(i).getString("lastname");
                    pl[i] = ja1.getJSONObject(i).getString("place");
                    ph[i] = ja1.getJSONObject(i).getString("phone");
                    em[i] = ja1.getJSONObject(i).getString("email");

                    value[i] = "first name: " + fn[i] + "\nlast name: " + ln[i] + "\nplace :" + pl[i] + "\nphone : " + ph[i] + "\nemail : " + em[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);
            }


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}