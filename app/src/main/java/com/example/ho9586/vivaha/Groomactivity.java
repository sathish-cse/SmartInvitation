package com.example.ho9586.vivaha;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.mikhaellopez.circularimageview.CircularImageView;

public class Groomactivity extends AppCompatActivity {

    CircularImageView groom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groomactivity);

        groom = (CircularImageView) findViewById(R.id.avatar);

        groom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

    }


    private void show() {

        final AlertDialog.Builder dialogBuilder2 = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView2 = inflater.inflate(R.layout.groom_details, null);
        dialogBuilder2.setView(dialogView2);


        final AlertDialog b1 = dialogBuilder2.create();
        b1.show();
        b1.setCanceledOnTouchOutside(false);
    }
}
