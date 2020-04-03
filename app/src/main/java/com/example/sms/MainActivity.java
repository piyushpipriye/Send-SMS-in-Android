package com.example.sms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText et1,et2;
public  final  static  int SMS_REQUEST_CODE =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},SMS_REQUEST_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_DENIED){
            Toast.makeText(this,"Permission allowed",Toast.LENGTH_SHORT).show();
            sendsms();
        }
        else {
            Toast.makeText(this,"Permission not allowed",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendsms() {
        String phone =et1.getText().toString().trim();
        String text  = et2.getText().toString().trim();
        if(phone.length()==10)
        {
            /// call code
            Uri uri = Uri.parse("tel:"+phone);   /// call code
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);   /// call code
            //Intent intent = new Intent(Intent.ACTION_VIEW);//sms
            //intent.setType("vnd.android-dir/mms-sms");/// sms sednin code
            //intent.putExtra("address",phone);/// sms sednin code
            //intent.putExtra("sms_body",text);/// sms sednin code
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"Incorrect number",Toast.LENGTH_SHORT).show();;
        }
    }
}
