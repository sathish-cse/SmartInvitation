package com.example.ho9586.vivaha;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ho9586.vivaha.CircleLayout.OnCenterClickListener;
import com.example.ho9586.vivaha.CircleLayout.OnItemClickListener;
import com.example.ho9586.vivaha.CircleLayout.OnItemSelectedListener;
import com.example.ho9586.vivaha.CircleLayout.OnRotationFinishedListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import customfonts.ParticleSystem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnItemSelectedListener,
        OnItemClickListener, OnRotationFinishedListener, OnCenterClickListener {

    CircularImageView avatar;
    ParticleSystem ps;
    RelativeLayout main;
    protected CircleLayout circleLayout;
    DrawerLayout drawer;
   // ImageView flower;
    // protected TextView selectedTextView;
    Toolbar toolbar;
    private TextView txtTimerDay, txtTimerHour, txtTimerMinute, txtTimerSecond;
    //  private TextView tvEvent;
    private Handler handler;
    private Runnable runnable;
    View v1;
    Integer i = 0;
    public MediaPlayer mp;
    String MenuItem = "";
    SharedPreferences appPreferences;
    boolean isAppInstalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        main = (RelativeLayout) findViewById(R.id.main);
        avatar = (CircularImageView) findViewById(R.id.avatar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mp = MediaPlayer.create(this, R.raw.song);
      //  flower=(ImageView)findViewById(R.id.flower);
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        circleLayout = (CircleLayout) findViewById(R.id.circle_layout);
        circleLayout.setOnItemSelectedListener(this);
        circleLayout.setOnItemClickListener(this);
        circleLayout.setOnRotationFinishedListener(this);
        circleLayout.setOnCenterClickListener(this);

        // selectedTextView = (TextView) findViewById(R.id.selected_textView);

        String name = null;
        View view = circleLayout.getSelectedItem();
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }
        // selectedTextView.setText(name);

        txtTimerDay = (TextView) findViewById(R.id.txtTimerDay);
        txtTimerHour = (TextView) findViewById(R.id.txtTimerHour);
        txtTimerMinute = (TextView) findViewById(R.id.txtTimerMinute);
        txtTimerSecond = (TextView) findViewById(R.id.txtTimerSecond);
        //  tvEvent = (TextView) findViewById(R.id.tvhappyevent);
        countDownStart();
        v1 = toolbar.findViewById(R.id.toolbar);


        appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isAppInstalled = appPreferences.getBoolean("isAppInstalled", false);
        if (!isAppInstalled) {
            /**
             * create short cut
             */
            Intent shortcutIntent = new Intent(getApplicationContext(), SplashScreen.class);
            shortcutIntent.setAction(Intent.ACTION_MAIN);
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Naveen Weds Abi");
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.icon));
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            getApplicationContext().sendBroadcast(intent);
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            mp.stop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.flower:
                i += 1;
                if (i == 2) {
                    Intent intent = getIntent();
                    startActivity(intent);
                    finish();
                    mp.stop();
                } else {
                    mp.start();
                    CountDownTimer aCounter = new CountDownTimer(24000, 1000) {
                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            mp.stop();
                            mp.start();
                            Intent intent = getIntent();
                            startActivity(intent);
                            finish();
                        }
                    }.start();

                    new ParticleSystem(MainActivity.this, 100, R.drawable.red, 3000)
                            .setAcceleration(0.00013f, 90)
                            .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                            .setFadeOut(200, new AccelerateInterpolator())
                            .emitWithGravity(v1, Gravity.BOTTOM, 15);

                    new ParticleSystem(MainActivity.this, 100, R.drawable.green, 3000)
                            .setAcceleration(0.00013f, 90)
                            .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                            .setFadeOut(200, new AccelerateInterpolator())
                            .emitWithGravity(v1, Gravity.BOTTOM, 15);
                    new ParticleSystem(MainActivity.this, 100, R.drawable.yellow, 3000)
                            .setAcceleration(0.00013f, 90)
                            .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                            .setFadeOut(200, new AccelerateInterpolator())
                            .emitWithGravity(v1, Gravity.BOTTOM, 15);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.invitation) {
            Intent i1 = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(i1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else if (id == R.id.bride) {
            Intent i1 = new Intent(MainActivity.this, BrideActivity.class);
            startActivity(i1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else if (id == R.id.groom) {
            Intent i1 = new Intent(MainActivity.this, Groomactivity.class);
            startActivity(i1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else if (id == R.id.events) {
             Intent i1=new Intent(MainActivity.this,EventsActivity.class);
            startActivity(i1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else if (id == R.id.venue) {
            String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", 11.4392411, 77.5596863, "Sri Angala Parameshwari Amman Temple");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            Toast.makeText(this, "Route Map for Reception Location..", Toast.LENGTH_LONG).show();
            try {
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            } catch (ActivityNotFoundException ex) {
                try {
                    Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(unrestrictedIntent);
                } catch (ActivityNotFoundException innerEx) {
                    Toast.makeText(this, "Please install Any Map application..", Toast.LENGTH_LONG).show();
                }
            }
        } else if (id == R.id.contact) {
            show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(View view) {
        final String name;
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        } else {
            name = null;
        }

        //   selectedTextView.setText(name);

        switch (view.getId()) {
            case R.id.main_calendar_image:
                // Handle calendar selection
                MenuItem = "groom";
                break;
            case R.id.main_cloud_image:
                // Handle cloud selection
                MenuItem = "groom";
                break;
            case R.id.main_key_image:
                // Handle key selection
                MenuItem = "events";
                break;
            case R.id.main_profile_image:
                // Handle profile selection
                MenuItem = "location";
                break;
            case R.id.main_mail_image:
                // Handle profile selection
                MenuItem = "card";
                break;
        }
    }


    @Override
    public void onItemClick(View view) {
        String name = null;
        if (view instanceof CircleImageView) {
            name = ((CircleImageView) view).getName();
        }

        String text = getResources().getString(R.string.start_app, name);
        //   Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.main_calendar_image:
                // Handle calendar click
                Intent i1 = new Intent(MainActivity.this, Groomactivity.class);
                startActivity(i1);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.main_cloud_image:
                // Handle cloud click
                Intent i2 = new Intent(MainActivity.this, BrideActivity.class);
                startActivity(i2);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.main_key_image:
                // Handle key click
                Intent i4=new Intent(MainActivity.this,EventsActivity.class);
                startActivity(i4);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.main_profile_image:
                // Handle profile click
                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", 11.4392411, 77.5596863, "Sri Angala Parameshwari Amman Temple");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                Toast.makeText(this, "Route Map for Reception Location..", Toast.LENGTH_LONG).show();
                try {
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                } catch (ActivityNotFoundException ex) {
                    try {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    } catch (ActivityNotFoundException innerEx) {
                        Toast.makeText(this, "Please install Any Map application..", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.main_mail_image:
                // Handle profile selection
                Intent i5=new Intent(MainActivity.this,ContactActivity.class);
                startActivity(i5);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
        }
    }

    @Override
    public void onRotationFinished(View view) {
        Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2, view.getHeight() / 2);
        animation.setDuration(250);
        view.startAnimation(animation);

        if (MenuItem == "groom") {
            Intent i1 = new Intent(MainActivity.this, BrideActivity.class);
            startActivity(i1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else if (MenuItem == "groom") {
            Intent i1 = new Intent(MainActivity.this, Groomactivity.class);
            startActivity(i1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else if (MenuItem == "events") {
            Intent i1=new Intent(MainActivity.this,EventsActivity.class);
            startActivity(i1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        } else if (MenuItem == "location") {
            String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?daddr=%f,%f (%s)", 11.4392411, 77.5596863, "Sri Angala Parameshwari Amman Temple");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            Toast.makeText(this, "Route Map for Reception Location..", Toast.LENGTH_LONG).show();
            try {
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            } catch (ActivityNotFoundException ex) {
                try {
                    Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(unrestrictedIntent);
                } catch (ActivityNotFoundException innerEx) {
                    Toast.makeText(this, "Please install Any Map application..", Toast.LENGTH_LONG).show();
                }
            }
        } else if (MenuItem == "card") {
             Intent i1=new Intent(MainActivity.this,SplashScreen.class);
             startActivity(i1);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }

    @Override
    public void onCenterClick() {
        //  Toast.makeText(getApplicationContext(), R.string.center_click, Toast.LENGTH_SHORT).show();
    }

 /*   public void onAddClick(View view) {
        CircleImageView newMenu = new CircleImageView(this);
        newMenu.setBackgroundResource(R.drawable.circle);
        newMenu.setImageResource(R.drawable.ic_voice);
        newMenu.setName(getString(R.string.voice_search));
        circleLayout.addView(newMenu);
    }
*/
    public void onRemoveClick(View view) {
        if (circleLayout.getChildCount() > 0) {
            circleLayout.removeViewAt(circleLayout.getChildCount() - 1);
        }
    }

    public void countDownStart() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss.SSS");
// Please here set your event date//YYYY-MM-DD
                    Date futureDate = dateFormat.parse("2017-06-06 06:00:00.000");
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        txtTimerDay.setText("" + String.format("%02d", days));
                        txtTimerHour.setText("" + String.format("%02d", hours));
                        txtTimerMinute.setText(""
                                + String.format("%02d", minutes));
                        txtTimerSecond.setText(""
                                + String.format("%02d", seconds));
                    } else {
                        //  tvEvent.setVisibility(View.VISIBLE);
                        //  tvEvent.setText("The event started!");
                        textViewGone();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

    public void textViewGone() {
        findViewById(R.id.LinearLayout10).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout11).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout12).setVisibility(View.GONE);
        findViewById(R.id.LinearLayout13).setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }

    private void show() {

        final AlertDialog.Builder dialogBuilder2 = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView2 = inflater.inflate(R.layout.contact, null);
        dialogBuilder2.setView(dialogView2);


        final AlertDialog b1 = dialogBuilder2.create();
        b1.show();
        b1.setCanceledOnTouchOutside(false);
    }

}
