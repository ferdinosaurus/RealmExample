package com.ferdinosaurus.realmexample.realm;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

public class MyMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }
}
