package com.albastomi.arif.Model;

public class SensorResult {

    private Integer status_code;
    private String message;
    private Integer id;



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
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The error
     */
    public void setId(Integer id) {
        this.id = id;
    }


}
