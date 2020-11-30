package com.integro.sjc;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.integro.sjc.adapters.ViewPageAdapter;
import com.integro.sjc.model.GalleryAlbum;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView tvDepartment = findViewById(R.id.tv_department);
        TextView tvContactUs = findViewById(R.id.tv_contactus);
        ImageView ivCall = findViewById(R.id.iv_call);
        ImageView ivFacebook = findViewById(R.id.iv_facebook);
        ImageView ivMail = findViewById(R.id.iv_mail);
        TextView tvGallery=findViewById(R.id.tvGallery);
        TextView tvFacilities=findViewById(R.id.fac);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);//setText("home")//;
        tabLayout.getTabAt(1).setIcon(R.drawable.newss);
        tabLayout.getTabAt(2).setIcon(R.drawable.notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.www1);

        final int colorYellow = ContextCompat.getColor(getApplicationContext(), R.color.colorYellow);
        final int colorWhiite = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(colorYellow, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(colorWhiite, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tvFacilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FacilitiesActivity.class);
                startActivity(intent);
            }
        });


        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                final CharSequence[] phone = new CharSequence[]{"7676759199"};
                String phone1 = "7676759199";
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone1, null));
                startActivity(intentCall);
            }
        });

        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailintent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject" + " " + "&body" + " " + "&to=" + "desk@sjc.ac.in");
                mailintent.setData(data);
                startActivity(mailintent);
            }
        });

        ivFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), WebActivity.class);
                String url = "https://www.facebook.com/sjcbengaluru";
                intent4.putExtra("TAG", url);
                startActivity(intent4);
            }
        });

        tvDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intiativesIntent = new Intent(MainActivity.this, DepartmentActivity.class);
                startActivity(intiativesIntent);
            }
        });

        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GalleryAlbumActivity.class);
                startActivity(intent);
            }
        });

        tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getContactUs();
            }
        });
    }
        public void onBackPressed () {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setTitle("Exit");
            AlertDialog.Builder builder = alertDialogBuilder.setMessage("Do you really want to exit?").setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //System.exit(0);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            //super.onBackPressed();
        }
    public void getContactUs() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dilouge_contact_us, null);
        dialogBuilder.setView(view);
        TextView tvAddress = view.findViewById(R.id.tv_Address);
        Button btnMap = view.findViewById(R.id.btn_Map);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Coming Soon..!", Toast.LENGTH_SHORT).show();
                String Map = "SJC";
                String uri = "https://www.google.com/maps/place/St.+Joseph's+College/@12.962323,77.59643,16z/data=!4m5!3m4!1s0x0:0xdec852132523ceae!8m2!3d12.962323!4d77.5964302?hl=en-IN" + Map;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });
        dialogBuilder.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
