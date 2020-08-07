package com.albastomi.arif.Utils;

import android.app.Application;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class BaseApp extends  Application {

    public void onCreate() {
        super.onCreate();

        //konfigurasi Realm

        Realm.init(getApplicationContext());

        // create your Realm configuration
        RealmConfiguration config = new RealmConfiguration.
                Builder().
                deleteRealmIfMigrationNeeded().
                build();
        Realm.setDefaultConfiguration(config);

    }

    private class DataMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

            //mengakses schema database untuk tujuan membuat, mengubah atau menghapus kelas dan kolom.
            RealmSchema schema = realm.getSchema();

            //jika versi 0 maka buat schema baru
            if (oldVersion == 0) {
                // buat kelas project
                schema.create("project")
                        .addField("id_project", int.class)
                        .addField("nama", String.class)
                        .addField("tgl_lahir", String.class)
                        .addField("usia", int.class)
                        .addField("jenis_kelamin", int.class);

                oldVersion++;
            }
        }
    }


}
