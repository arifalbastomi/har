package com.albastomi.arif.Model;

public class LoginData {

    private String email;
    private String authorization;
    private String password;

    public LoginData(String email, String password,String authorization) {
        this.email = email;
        this.password = password;
        this.authorization = authorization;
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
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
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
