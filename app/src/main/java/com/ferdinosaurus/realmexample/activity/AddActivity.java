package com.ferdinosaurus.realmexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ferdinosaurus.realmexample.R;
import com.ferdinosaurus.realmexample.realm.RealmController;
import com.ferdinosaurus.realmexample.realm.model.MahasiswaModel;

import java.util.UUID;

import io.realm.RealmResults;

public class AddActivity extends AppCompatActivity {

    EditText tv_add_nama,tv_add_jurusan;
    Button btn_add_submit;
    RealmController realmController;
    RealmResults<MahasiswaModel> mahasiswaModelRealmResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initialization();
        setupListener();

    }

    private void initialization(){
        tv_add_nama = findViewById(R.id.tv_add_nama);
        tv_add_jurusan = findViewById(R.id.tv_add_jurusan);
        btn_add_submit = findViewById(R.id.btn_add_submit);
        realmController = new RealmController(AddActivity.this);

    }

    private void setupListener(){
        btn_add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MahasiswaModel mahasiswaModel = new MahasiswaModel();
                mahasiswaModel.setId(UUID.randomUUID().toString());
                mahasiswaModel.setNama(tv_add_nama.getText().toString());
                mahasiswaModel.setJurusan(tv_add_jurusan.getText().toString());
                realmController.addMahasiswa(mahasiswaModel);
                mahasiswaModelRealmResults = realmController.showAllMahasiswa();
                for(int i=0;i<mahasiswaModelRealmResults.size();i++){
                    Log.d("data"+i+" id ",mahasiswaModelRealmResults.get(i).getId());
                    Log.d("data"+i+" nama ",mahasiswaModelRealmResults.get(i).getNama());
                    Log.d("data"+i+" jurusan ",mahasiswaModelRealmResults.get(i).getJurusan());
                }
                finish();
            }
        });
    }
}
