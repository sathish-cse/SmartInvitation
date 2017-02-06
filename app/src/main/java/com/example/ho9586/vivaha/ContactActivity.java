package com.example.ho9586.vivaha;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ContactActivity extends Activity {
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contact);

        call = (Button) findViewById(R.id.call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri skypeUri = Uri.parse("skype:sureshssk?call&video=true");
                Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);
                myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent);
            }
        });

    }


}
