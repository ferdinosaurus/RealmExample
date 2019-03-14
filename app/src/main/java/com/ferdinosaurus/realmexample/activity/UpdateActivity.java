package com.ferdinosaurus.realmexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ferdinosaurus.realmexample.R;
import com.ferdinosaurus.realmexample.realm.RealmController;
import com.ferdinosaurus.realmexample.realm.model.MahasiswaModel;

public class UpdateActivity extends AppCompatActivity {

    EditText tv_update_nama,tv_update_jurusan;
    Button btn_update_submit;

    RealmController realmController;
    MahasiswaModel mahasiswaModel= new MahasiswaModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initialization();
        setupData();
        setupListener();
    }

    private void initialization(){
        tv_update_nama = findViewById(R.id.tv_update_nama);
        tv_update_jurusan = findViewById(R.id.tv_update_jurusan);
        btn_update_submit = findViewById(R.id.btn_update_submit);

        realmController = new RealmController(UpdateActivity.this);
    }

    private void setupData(){
        mahasiswaModel = realmController.getMahasiswaById(getIntent().getStringExtra("id"));
        tv_update_nama.setText(mahasiswaModel.getNama());
        tv_update_jurusan.setText(mahasiswaModel.getJurusan());
    }

    private void setupListener(){
        btn_update_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mahasiswaModel= new MahasiswaModel();
                mahasiswaModel.setNama(tv_update_nama.getText().toString());
                mahasiswaModel.setJurusan(tv_update_jurusan.getText().toString());
                realmController.updateMahasiswa(getIntent().getStringExtra("id"),mahasiswaModel);
                finish();
            }
        });
    }
}
