package com.albastomi.arif.Model;

public class ProjectResult {

    private Integer status_code;
    private String message;
    private Integer id_project;
    private Integer insert_interval;
    private Integer sync_interval;



    /**
     *
     * @return
     * The status_code
     */
    public Integer getStatus_code() {
        return status_code;
    }

    /**
     *
     * @param status_code
     * The error
     */
    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The id_user
     */
    public Integer getId_project() {
        return id_project;
    }

    /**
     *
     * @param id_project
     * The error
     */
    public void setId_project(Integer id_project) {
        this.id_project = id_project;
    }

    /**
     *
     * @return
     * The nama
     */
    public Integer getInsert_interval() {
        return insert_interval;
    }

    /**
     *
     * @param insert_interval
     * The message
     */
    public void setInsert_interval(Integer insert_interval) { this.insert_interval = insert_interval; }

    /**
     *
     * @return
     * The email
     */
    public Integer getSync_interval() {return sync_interval; }

    /**
     *
     * @param sync_interval
     * The message
     */
    public void setSync_interval(Integer sync_interval) {
        this.sync_interval = sync_interval;
    }


}
