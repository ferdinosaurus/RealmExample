package com.ferdinosaurus.realmexample.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.ferdinosaurus.realmexample.realm.MyMigration;
import com.ferdinosaurus.realmexample.realm.RealmConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    private static Context context;
    private static Activity activity;
    @Override
    public void onCreate() {
        super.onCreate();

        MyApplication.context = getApplicationContext();

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(RealmConfig.name)
                .schemaVersion(RealmConfig.schemaVersion)
                .migration(new MyMigration())
                .build();


        Realm.setDefaultConfiguration(realmConfiguration);
        Realm.getInstance(realmConfiguration);

    }


    @Override
    public void onTerminate() {
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }


    public static Context getAppContext() {
        return MyApplication.context;
    }


    public static Activity getActivity() {return MyApplication.activity;}



}
