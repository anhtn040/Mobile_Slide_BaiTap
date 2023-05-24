package com.example.baitap9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    ViewFlipper viewFlipper;
    FrameLayout frameLayout;
    FragmentDangNhap frDN = null;
    FragmentCaiDat frCD = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Banner(){
        ImageView v1 = new ImageView(getApplicationContext());
        ImageView v2 = new ImageView(getApplicationContext());
        v1.setImageResource(R.drawable.banner1);
        v2.setImageResource(R.drawable.banner2);
        v1.setScaleType(ImageView.ScaleType.FIT_XY);
        v2.setScaleType(ImageView.ScaleType.FIT_XY);
        viewFlipper.addView(v1);
        viewFlipper.addView(v2);
        viewFlipper.setAutoStart(true);
    }
    private void setEvent() {
        Banner();
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.mnDangNhap:
                        if (frDN==null)
                            frDN = new FragmentDangNhap();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,frDN).commit();
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mnCaiDat:
                        if (frCD==null)
                            frCD = new FragmentCaiDat();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,frCD).commit();
                        drawerLayout.closeDrawers();
                        break;
                    default:
                        break;
                }
//                if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                }
                return true;
            }
        });
    }

    private void setControl() {
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        viewFlipper = findViewById(R.id.viewFlipper);
        frameLayout = findViewById(R.id.frameLayout);
    }
}