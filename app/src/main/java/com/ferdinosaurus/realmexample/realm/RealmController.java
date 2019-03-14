package com.ferdinosaurus.realmexample.realm;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.ferdinosaurus.realmexample.realm.model.MahasiswaModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {

    private final Realm realm;
    private Activity activity;


    public RealmController(Activity activity) {
        this.activity = activity;
        realm = Realm.getDefaultInstance();
    }
    public void addMahasiswa(final MahasiswaModel mahasiswaModel) {
        try {
            //realm.beginTransaction();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(mahasiswaModel);
                    Toast.makeText(activity, "masuk", Toast.LENGTH_SHORT).show();
                    Log.d("RealmController", "masuk");
                }
            });
        } catch (Exception e) {
            Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
            Log.d("RealmController", "addmahasiswa catch : " + e.toString());
        } finally {
            //realm.commitTransaction();
        }
    }

    public RealmResults<MahasiswaModel> showAllMahasiswa() {

        return realm.where(MahasiswaModel.class).findAll();
    }

    public void deleteMahasiswa(final MahasiswaModel mahasiswaModel) {
        final RealmResults<MahasiswaModel> mahasiswaModelRealmResults = realm.where(MahasiswaModel.class)
                .equalTo(MahasiswaModel.KEY_ID, mahasiswaModel.getId()).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mahasiswaModelRealmResults.deleteAllFromRealm();
            }
        });
    }

    public void updateMahasiswa(String idMahasiswa,MahasiswaModel mahasiswaModelNewValue){
        MahasiswaModel mahasiswaModel =  realm.where(MahasiswaModel.class).equalTo(MahasiswaModel.KEY_ID, idMahasiswa).findFirst();

        realm.beginTransaction();
        mahasiswaModel.setNama(mahasiswaModelNewValue.getNama());
        mahasiswaModel.setJurusan(mahasiswaModelNewValue.getJurusan());
        realm.commitTransaction();
    }

    public MahasiswaModel getMahasiswaById(String idMahasiswa){
        MahasiswaModel mahasiswaModel =  realm.where(MahasiswaModel.class).equalTo(MahasiswaModel.KEY_ID, idMahasiswa).findFirst();

        return mahasiswaModel;
    }

}
