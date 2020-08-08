package com.albastomi.arif.Model;

public class SensorData {

    private String authorization;
    private String id;
    private String id_user;
    private String id_project;
    private String gyro_x;
    private String gyro_y;
    private String gyro_z;
    private String acc_x;
    private String acc_y;
    private String acc_z;
    private String hrv;
    private String attempt;
    private String createdate;

    public SensorData(String id,String id_user,String id_project,String gyro_x,String gyro_y,String gyro_z,String acc_x,String acc_y,String acc_z,String hrv,String createdate,String attempt,String authorization) {
        this.id = id;
        this.authorization = authorization;
        this.id_user = id_user;
        this.id_project = id_project;
        this.gyro_x = gyro_x;
        this.gyro_y = gyro_y;
        this.gyro_z = gyro_z;
        this.acc_x = acc_x;
        this.acc_y = acc_y;
        this.acc_z = acc_z;
        this.attempt = attempt;
        this.createdate = createdate;
    }

    /**
     *
     * @return
     * The email
     */
    public String getId() {
        return id;
    }
    /**
     *
     * @param id
     * The email
     */
    public void setId(String id) {
        this.id = id;
    }



    /**
     *
     * @return
     * The authorization
     */
    public String getAuthorization() {
        return authorization;
    }

    /**
     *
     * @param authorization
     * The authorization
     */
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }



    /**
     *
     * @return
     * The id_user
     */
    public String getId_user() {
        return id_user;
    }

    /**
     *
     * @param id_user
     * The id_user
     */
    public void setId_user(String id_user) {
        this.id_user = id_user;
    }


    /**
     *
     * @return
     * The id_project
     */
    public String getId_project() {
        return id_project;
    }

    /**
     *
     * @param id_project
     * The id_project
     */
    public void setId_project(String id_project) {
        this.id_project = id_project;
    }


    /**
     *
     * @return
     * The gyro_x
     */
    public String getGyro_x() {
        return gyro_x;
    }

    /**
     *
     * @param gyro_x
     * The gyro_x
     */
    public void setGyro_x(String gyro_x) {
        this.gyro_x = gyro_x;
    }

    /**
     *
     * @return
     * The gyro_y
     */
    public String getGyro_y() {
        return gyro_y;
    }

    /**
     *
     * @param gyro_y
     * The gyro_y
     */
    public void setGyro_y(String gyro_y) {
        this.gyro_y = gyro_y;
    }

    /**
     *
     * @return
     * The gyro_z
     */
    public String getGyro_z() {
        return gyro_z;
    }

    /**
     *
     * @param gyro_z
     * The gyro_z
     */
    public void setGyro_z(String gyro_z) {
        this.gyro_z = gyro_z;
    }


    /**
     *
     * @return
     * The acc_x
     */
    public String getAcc_x() {
        return acc_x;
    }

    /**
     *
     * @param acc_x
     * The acc_x
     */
    public void setAcc_x(String acc_x) {
        this.acc_x = acc_x;
    }


    /**
     *
     * @return
     * The acc_x
     */
    public String getAcc_y() {
        return acc_y;
    }

    /**
     *
     * @param acc_y
     * The acc_y
     */
    public void setAcc_y(String acc_y) {
        this.acc_y = acc_y;
    }


    /**
     *
     * @return
     * The acc_z
     */
    public String getAcc_z() {
        return acc_z;
    }

    /**
     *
     * @param acc_z
     * The acc_z
     */
    public void setAcc_z(String acc_z) {
        this.acc_z = acc_z;
    }


    /**
     *
     * @return
     * The hrv
     */
    public String getHrv() {
        return hrv;
    }

    /**
     *
     * @param hrv
     * The hrv
     */
    public void setHrv(String hrv) {
        this.hrv = hrv;
    }

    /**
     *
     * @return
     * The createdate
     */
    public String getCreatedate() {
        return createdate;
    }

    /**
     *
     * @param createdate
     * The createdate
     */
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }


    /**
     *
     * @return
     * The createdate
     */
    public String getAttempt() {
        return attempt;
    }

    /**
     *
     * @param attempt
     * The createdate
     */
    public void setAttempt(String attempt) {
        this.createdate = attempt;
    }
}
