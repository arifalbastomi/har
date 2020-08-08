package com.albastomi.arif.Model;

public class ActivityData {

    private Integer id_project;
    private String authorization;
    private Integer id_user;
    private String activity;
    private Integer attempt;

    public ActivityData(Integer id_project, Integer id_user,String activity,Integer attempt,String authorization) {
        this.id_project = id_project;
        this.id_user = id_user;
        this.activity = activity;
        this.attempt = attempt;
        this.authorization = authorization;
    }

    /**
     *
     * @return
     * The id_project
     */
    public Integer getId_project() {
        return id_project;
    }
    /**
     *
     * @param id_project
     * The id_project
     */
    public void setId_project(Integer id_project) {
        this.id_project = id_project;
    }

    /**
     *
     * @return
     * The id_user
     */
    public Integer getId_user() {
        return id_user;
    }
    /**
     *
     * @param id_user
     * The id_user
     */
    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    /**
     *
     * @return
     * The activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     *
     * @param activity
     * The activity
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     *
     * @return
     * The attempt
     */
    public Integer getAttempt() {
        return attempt;
    }
    /**
     *
     * @param attempt
     * The attempt
     */
    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }


    /**
     *
     * @return
     * The password
     */
    public String getAuthorization() {
        return authorization;
    }

    /**
     *
     * @param authorization
     * The password
     */
    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

}
