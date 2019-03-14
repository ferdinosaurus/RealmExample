package com.ferdinosaurus.realmexample.realm.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MahasiswaModel extends RealmObject {
    public final static String KEY_ID = "id";
    public final static String KEY_NAMA = "nama";
    public final static String KEY_JURUSAN = "jurusan";

    @PrimaryKey
    private String id ;

    private String nama;
    private String jurusan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}
