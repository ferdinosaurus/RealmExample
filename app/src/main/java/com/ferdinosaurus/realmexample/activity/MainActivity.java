package com.ferdinosaurus.realmexample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ferdinosaurus.realmexample.R;
import com.ferdinosaurus.realmexample.adapter.MahasiswaAdapter;
import com.ferdinosaurus.realmexample.realm.MyMigration;
import com.ferdinosaurus.realmexample.realm.MyModules;
import com.ferdinosaurus.realmexample.realm.RealmConfig;
import com.ferdinosaurus.realmexample.realm.RealmController;
import com.ferdinosaurus.realmexample.realm.model.MahasiswaModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_data;

    RealmController realmController;
    RealmResults<MahasiswaModel> mahasiswaModelRealmResults;

    MahasiswaAdapter mahasiswaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization() {
        rv_data = findViewById(R.id.rv_data);
        realmController = new RealmController(MainActivity.this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_add:
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(this, "onresume", Toast.LENGTH_SHORT).show();
        mahasiswaModelRealmResults = realmController.showAllMahasiswa();
        for(int i=0;i<mahasiswaModelRealmResults.size();i++){
            Log.d("data"+i+" id ",mahasiswaModelRealmResults.get(i).getId());
            Log.d("data"+i+" nama ",mahasiswaModelRealmResults.get(i).getNama());
            Log.d("data"+i+" jurusan ",mahasiswaModelRealmResults.get(i).getJurusan());
        }
        mahasiswaAdapter = new MahasiswaAdapter(MainActivity.this,mahasiswaModelRealmResults);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_data.setLayoutManager(lm);
        rv_data.setItemAnimator(new DefaultItemAnimator());
        rv_data.setAdapter(mahasiswaAdapter);
    }
}