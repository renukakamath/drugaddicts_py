package com.example.drug_addicts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

public class Userviewbooking extends AppCompatActivity implements AdapterView.OnItemClickListener,JsonResponse {

    ListView l1;
    String[] fn, ln, pl, ph, em, de, psycho_id,login_id, value;
    public static String psycho_ids,login_ids;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewbooking);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = (ListView) findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Userviewbooking.this;
        String q = "/userviewpsychologist";
        q = q.replace(" ", "%20");
        JR.execute(q);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void response(JSONObject jo) {

    }
}