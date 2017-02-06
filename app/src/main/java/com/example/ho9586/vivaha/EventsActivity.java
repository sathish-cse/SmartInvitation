package com.example.ho9586.vivaha;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Locale;

public class EventsActivity extends AppCompatActivity {

    ImageView wedding,reception;
    ImageView bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ImageView wedding=(ImageView)findViewById(R.id.weddinglocation);
        ImageView reception=(ImageView)findViewById(R.id.receptionlocation);
        bus=(ImageView)findViewById(R.id.avatar3);
        reception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", 11.4392411, 77.5596863, "Sri Angala Parameshwari Amman Temple");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                Toast.makeText(EventsActivity.this, "Route Map for Reception Location..", Toast.LENGTH_LONG).show();
                try {
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                } catch (ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    } catch (ActivityNotFoundException innerEx) {
                        Toast.makeText(EventsActivity.this, "Please install Any Map application..", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        wedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", 11.245805, 77.240445, "Sri Veeramathi Amman Temple");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                Toast.makeText(EventsActivity.this, "Route Map for Wedding Location..", Toast.LENGTH_LONG).show();
                try {
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                } catch (ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    } catch (ActivityNotFoundException innerEx) {
                        Toast.makeText(EventsActivity.this, "Please install Any Map application..", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Uri skypeUri = Uri.parse("skype:sureshssk?call&video=true ");
                Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);
                myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myIntent);*/
                show();

            }
        });
    }

    private void show() {

            final AlertDialog.Builder dialogBuilder2 = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView2 = inflater.inflate(R.layout.businfo, null);
            dialogBuilder2.setView(dialogView2);


            final AlertDialog b1 = dialogBuilder2.create();
            b1.show();
            b1.setCanceledOnTouchOutside(false);
        }

}
