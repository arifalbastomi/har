package com.albastomi.arif.Model;

public class LoginResult {

    /*"status_code": 200,
    "message": "success",
    "id_user": "1",
    "nama": "tomi",
    "email": "tomi@gmail.com",*/
    private Integer status_code;
    private String message;
    private Integer id_user;
    private String nama;
    private String email;



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
    public Integer getId_user() {
        return id_user;
    }

    /**
     *
     * @param id_user
     * The error
     */
    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    /**
     *
     * @return
     * The nama
     */
    public String getNama() {
        return nama;
    }

    /**
     *
     * @param nama
     * The message
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The message
     */
    public void setEmail(String email) {
        this.email = email;
    }


}
