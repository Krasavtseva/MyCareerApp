package com.example.tesster;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SocialActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private FirebaseAuth mAuth;
    String currentUserID;
    private DatabaseReference UsersRef;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private Dialog loadingdialog;
    private RecyclerView recyclerView;
    private DrawerLayout drawer;

    TextView text_whatsApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //ссылки на соц. сети
        TextView textvk = findViewById(R.id.text_vk);
        textvk.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textinst = findViewById(R.id.text_inst);
        textinst.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textfb = findViewById(R.id.text_fb);
        textfb.setMovementMethod(LinkMovementMethod.getInstance());

        TextView textyan = findViewById(R.id.text_yandex);
        textyan.setMovementMethod(LinkMovementMethod.getInstance());

        text_whatsApp = findViewById(R.id.text_whatsApp);
        text_whatsApp.setText("+7(985)840-33-58");
        Linkify.addLinks(text_whatsApp,Linkify.PHONE_NUMBERS);
        //конец ссылка на соц. сети

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        final TextView navUsername = headerView.findViewById(R.id.nav_username);
        final TextView navUseremail = headerView.findViewById(R.id.nav_useremail);

        //Подключение
        mAuth = FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Социальные сети");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//кнопка назад

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R. string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        UsersRef.child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();

                    navUsername.setText(name);
                    navUseremail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_chat){
            startActivity(new Intent(SocialActivity.this, ChatActivity.class));
        }else if (id == R.id.nav_home){
            startActivity(new Intent(SocialActivity.this, CategoriesActivity.class));
        }else if (id == R.id.nav_singout){
            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(this, HomeActivity.class);
            startActivity(loginActivity);
            finish();
        }else if(id == R.id.nav_share)
        {
            Intent shareintent = new Intent();
            shareintent.setAction(Intent.ACTION_SEND);
            shareintent.putExtra(Intent.EXTRA_TEXT, "Ссылка на приложение My Career - https://drive.google.com/open?id=1Ef1G8hZcAoT-tgfcNf0UHW2m96PxXQu9");
            shareintent.setType("text/plain");
            startActivity(Intent.createChooser(shareintent,"Поделиться"));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}
