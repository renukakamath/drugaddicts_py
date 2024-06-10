package com.example.drug_addicts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
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

public class userviewpsychologist extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    ListView l1;
    String[] fn, ln, pl, ph, em, de, psycho_id,login_id, value;
    public static String psycho_ids,login_ids;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewpsychologist);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = (ListView) findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) userviewpsychologist.this;
        String q = "/userviewpsychologist";
        q = q.replace(" ", "%20");
        JR.execute(q);

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method=jo.getString("method");
            if(method.equalsIgnoreCase("useraddbookings")) {
            String status = jo.getString("status");
            Log.d("pearl", status);
                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "BOOKED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), viewmybooking.class));

                } else {
                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            }
            else if(method.equalsIgnoreCase("userviewpsychologist"))
            {
                String status=jo.getString("status");
                Log.d("pearl",status);

            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                psycho_id = new String[ja1.length()];
                login_id = new String[ja1.length()];
                fn = new String[ja1.length()];
                ln = new String[ja1.length()];
                pl = new String[ja1.length()];
                ph = new String[ja1.length()];
                em = new String[ja1.length()];
                de = new String[ja1.length()];
                value = new String[ja1.length()];


                for (int i = 0; i < ja1.length(); i++) {
                    psycho_id[i] = ja1.getJSONObject(i).getString("psychologist_id");
                    login_id[i] = ja1.getJSONObject(i).getString("login_id");
                    fn[i] = ja1.getJSONObject(i).getString("firstname");
                    ln[i] = ja1.getJSONObject(i).getString("lastname");
                    pl[i] = ja1.getJSONObject(i).getString("place");
                    ph[i] = ja1.getJSONObject(i).getString("phone");
                    em[i] = ja1.getJSONObject(i).getString("email");
                    de[i] = ja1.getJSONObject(i).getString("designation");

                    value[i] = "first name: " + fn[i] + "\nlast name: " + ln[i] + "\nplace :" + pl[i] + "\nphone" + ph[i] + "\nemail" + em[i] + "\ndesignation" + de[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), R.layout.custtext, value);

                l1.setAdapter(ar);
            }
            }



        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }




        @Override
        public void onItemClick (AdapterView < ? > parent, View view,int position, long id){
            psycho_ids = psycho_id[position];
            login_ids=login_id[position];
            SharedPreferences.Editor e=sh.edit();
            e.putString("receiver_id",login_ids);
            e.commit();

            final CharSequence[] items = {"Make Book", "Chat","Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(userviewpsychologist.this);
            // builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {


                    if (items[item].equals("Make Book")) {
                        JsonReq JR = new JsonReq();
                        JR.json_response = (JsonResponse) userviewpsychologist.this;
                        String q = "/useraddbookings?login_id="+sh.getString("log_id","")+"&psycho_id="+userviewpsychologist.psycho_ids;
                        q = q.replace(" ", "%20");
                        JR.execute(q);
                        Toast.makeText(getApplicationContext(), "BOOKING SUCCESS", Toast.LENGTH_LONG).show();

//                       startActivity(new Intent(getApplicationContext(), viewmybooking.class));

                    } else if (items[item].equals("view schedule time")) {
                        startActivity(new Intent(getApplicationContext(), userviewsheduletime.class));

                    }  else if (items[item].equals("Chat")) {
                        startActivity(new Intent(getApplicationContext(), ChatHere.class));

                    }else if (items[item].equals("view medicine and medition")) {
                        startActivity(new Intent(getApplicationContext(), userviewmedicineandmedition.class));

                    } else if (items[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }

            });
            builder.show();

//            final CharSequence[] items = {"booking","view schedule time","chat","view medicine and medition","Cancel"};
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(userviewpsychologist.this);
//            // builder.setTitle("Add Photo!");
//            builder.setItems(items, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int item) {
//
//
//                    if (items[item].equals("booking")) {
//                        JsonReq JR = new JsonReq();
//                        JR.json_response = (JsonResponse) userviewpsychologist.this;
//                        String q = "/useraddbookings?login_id="+sh.getString("log_id","")+"&psycho_id="+userviewpsychologist.psycho_ids;
//                        q = q.replace(" ", "%20");
//                        JR.execute(q);
//                        Toast.makeText(getApplicationContext(), "BOOKING SUCCESS", Toast.LENGTH_LONG).show();
//
////                       startActivity(new Intent(getApplicationContext(), viewmybooking.class));
//
//                    } else if (items[item].equals("view schedule time")) {
//                        startActivity(new Intent(getApplicationContext(), userviewsheduletime.class));
//
//                    }  else if (items[item].equals("chat")) {
////                        startActivity(new Intent(getApplicationContext(), userchat.class));
//
//                    }else if (items[item].equals("view medicine and medition")) {
//                        startActivity(new Intent(getApplicationContext(), userviewmedicineandmedition.class));
//
//                    } else if (items[item].equals("Cancel")) {
//                        dialog.dismiss();
//                    }
//                }
//
//            });
//            builder.show();
        }

    }
