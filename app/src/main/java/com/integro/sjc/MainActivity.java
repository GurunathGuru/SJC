package com.integro.sjc;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.integro.sjc.adapters.ViewPageAdapter;
import com.integro.sjc.firebase.MyFirebaseMessagingService;

import static com.integro.sjc.firebase.MyFirebaseMessagingService.NOTIFICATION_KEY;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    public static String Email, Password = " ";
    private static boolean flag = false;
    private TextView btnVIP, btnLogout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser vipUser = firebaseAuth.getCurrentUser();

        Log.i(TAG, "onStart: " + vipUser);

        if (vipUser != null) {
            btnVIP.setEnabled(false);
            flag = true;
            btnVIP.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
            FirebaseMessaging.getInstance().subscribeToTopic("Faculty");
            FirebaseMessaging.getInstance().subscribeToTopic("Student");
        } else {
            flag = false;
            btnVIP.setVisibility(View.VISIBLE);
            btnVIP.setEnabled(true);
            btnLogout.setVisibility(View.GONE);
            FirebaseMessaging.getInstance().subscribeToTopic("Student");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseMessaging.getInstance().subscribeToTopic("sjc");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseMessaging.getInstance().subscribeToTopic("sjc");

        TextView tvDepartment = findViewById(R.id.tv_department);
        TextView tvContactUs = findViewById(R.id.tv_contactus);
        ImageView ivCall = findViewById(R.id.iv_call);
        ImageView ivFacebook = findViewById(R.id.iv_facebook);
        ImageView ivMail = findViewById(R.id.iv_mail);
        TextView tvGallery = findViewById(R.id.tvGallery);
        TextView tvFacilities = findViewById(R.id.fac);

        btnVIP = findViewById(R.id.btnVIP);
        btnLogout = findViewById(R.id.btnLogout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(adapter);

        boolean isFCMIntent = getIntent().getBooleanExtra(MyFirebaseMessagingService.TAG, false);
        if (isFCMIntent) {
            String type = getIntent().getExtras().getString("type");
            switch (type) {
                case NOTIFICATION_KEY:
                    viewPager.setCurrentItem(2);
                    break;
            }
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);//setText("home")//;
        tabLayout.getTabAt(1).setIcon(R.drawable.newss);
        tabLayout.getTabAt(2).setIcon(R.drawable.notifications);
        tabLayout.getTabAt(3).setIcon(R.drawable.www1);

        final int colorCream = ContextCompat.getColor(getApplicationContext(), R.color.colorCream);
        final int colorWhiite = ContextCompat.getColor(getApplicationContext(), R.color.colorWhite);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // viewPager.setCurrentItem(tab.getPosition());
                tab.getIcon().setColorFilter(colorCream, PorterDuff.Mode.SRC_IN);
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
                Toast.makeText(MainActivity.this, "coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "coming soon", Toast.LENGTH_SHORT).show();
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

        btnVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (firebaseAuth.getCurrentUser() != null) {
                    firebaseAuth.signOut();
                    startActivity(getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName())
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                }
            }
        });
    }

    private void getDialog() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.card_vip_login, null);
        dialogBuilder.setView(view);

        final AlertDialog alertDialog = dialogBuilder.create();
        final EditText email = view.findViewById(R.id.etEmailId);
        email.setText("sjc@gmail.com");
        email.setEnabled(false);
        final EditText password = view.findViewById(R.id.etPassword);
        final Button loginVip = view.findViewById(R.id.btnLoginVip);

        loginVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean signInFlag = true;
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                Log.i(TAG, "onClick: currentUser " + currentUser);
                if (currentUser == null) {

                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    email.setError("Please Enter E - mail ID");
                    signInFlag = false;
                }
                if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Please Enter Password");
                    signInFlag = false;
                }
                if (signInFlag) {
                    Email = email.getText().toString();
                    Password = password.getText().toString();
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        Log.i(TAG, "onComplete: " + user);
                                        if (user != null) {
                                            startActivity(getBaseContext().getPackageManager()
                                                    .getLaunchIntentForPackage(getBaseContext().getPackageName())
                                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "Please Contact Admin.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
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

    public void gratitude(View view) {
        Intent intent = new Intent(getApplicationContext(), GratitudeActivity.class);
        startActivity(intent);
    }

    public void alumni(View view) {
        Intent intent = new Intent(getApplicationContext(), AlumniActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle("Exit");
        AlertDialog.Builder builder = alertDialogBuilder.setMessage("Do you really want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        System.exit(0);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void principalMessage(View view) {
        Intent intent = new Intent(getApplicationContext(), PrincipalMessageActivity.class);
        startActivity(intent);
    }
}
