package com.albastomi.arif.Utils;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProjectModel extends RealmObject{

    @PrimaryKey
    private int id_project;
    private  String nama;
    private String tgl_lahir;
    private int usia;
    private String jenis_kelamin;

    public int getId() {
        return id_project;
    }

    public void setId(int id_project) {
        this.id_project = id_project;
    }

    public String getNama() {
        return nama;
    }

    public void getNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }


    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }


    public String getJenis_Kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_Kelamin(String usia) {
        this.jenis_kelamin = jenis_kelamin;
    }


}
