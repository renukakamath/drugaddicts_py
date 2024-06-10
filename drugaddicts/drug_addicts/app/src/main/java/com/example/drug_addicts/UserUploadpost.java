package com.example.drug_addicts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UserUploadpost extends AppCompatActivity implements JsonResponse {

    EditText e1;
    ListView l1;
    ImageButton i1;
    Button b1;
    SharedPreferences sh;
    String desc;
    String[] descs,imagess;
    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;
    public static String encodedImage = "", path = "";
    private Uri mImageCaptureUri;
    byte[] byteArray = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_uploadpost);

        e1 = (EditText) findViewById(R.id.epost);
        l1 = (ListView) findViewById(R.id.lvpost);
        i1 = (ImageButton) findViewById(R.id.ibpost);
        b1 = (Button) findViewById(R.id.bpost);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


//        i1 = (ImageButton) findViewById(R.id.ibimage);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageOption();
            }
        });


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) UserUploadpost.this;
        String q = "/customerviewpost?lid=" + sh.getString("log_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desc = e1.getText().toString();

                if (desc.equalsIgnoreCase("")) {
                    e1.setError("Enter your Description");
                    e1.setFocusable(true);
                }  else {

                    sendAttach();
//                    JsonReq JR = new JsonReq();
//                    JR.json_response = (JsonResponse) customer_customized_work.this;
//                    String q = "/customer_send_customized_work?cid=" + view_arts.craftmanids + "&lid=" + sh.getString("log_id", "") + "&cdesign=" + cdesign + "&cdescription=" + cdescription;
//                    q = q.replace(" ", "%20");
//                    JR.execute(q);
                }

            }
        });
    }


    /////////////////////////////////////////////


    private void sendAttach() {
        try {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String q = "http://" + sh.getString("ip", "") + "/api/newuploadfile";

            Map<String, byte[]> aa = new HashMap<>();
            aa.put("image",byteArray);

            aa.put("lid", sh.getString("log_id", "").getBytes());
            aa.put("desc", desc.getBytes());
            Toast.makeText(getApplicationContext(),q,Toast.LENGTH_LONG).show();

            FileUploadAsync fua = new FileUploadAsync(q);
            fua.json_response = (JsonResponse) UserUploadpost.this;
            fua.execute(aa);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception upload : " + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImageOption() {

		/*Android 10+ gallery code
        android:requestLegacyExternalStorage="true"*/

        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(UserUploadpost.this);
        builder.setTitle("Take Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Capture Photo")) {
                    Intent intent = new Intent(MediaStore
                            .ACTION_IMAGE_CAPTURE);
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);

                } else if (items[item].equals("Choose from Gallery")) {
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : " + mImageCaptureUri);
            //   CropingIMG();

            Uri uri = data.getData();
            Log.d("File Uri", "File Uri: " + uri.toString());
            // Get the path
            //String path = null;
            try {
                path = FileUtils.getPath(this, uri);
                Toast.makeText(getApplicationContext(), "path : " + path, Toast.LENGTH_LONG).show();

                File fl = new File(path);
                int ln = (int) fl.length();

                InputStream inputStream = new FileInputStream(fl);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[ln];
                int bytesRead = 0;

                while ((bytesRead = inputStream.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                inputStream.close();
                byteArray = bos.toByteArray();

                Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                i1.setImageBitmap(bit);

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {

            try {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                i1.setImageBitmap(thumbnail);
                byteArray = baos.toByteArray();

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("customer_send_customized_work")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "SENDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), UserUploadpost.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }



            }
            else if(method.equalsIgnoreCase("customerviewpost")) {
                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    descs = new String[ja1.length()];
                    imagess = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        descs[i] = ja1.getJSONObject(i).getString("description");

                        imagess[i] = ja1.getJSONObject(i).getString("file");
//                        values[i] = "design: " + design[i] + "\ndesciption: " + description[i] + "\ndate: " + date[i]+ "\nstatus: " + statuss[i];

                    }
//                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, values);
//                    l.setAdapter(ar);

                    Custimage cc=new Custimage(this,imagess,descs);
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