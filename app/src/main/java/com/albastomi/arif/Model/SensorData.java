package com.albastomi.arif.Model;

public class SensorData {
    private String id;
    private String authorization;


    public SensorData(String id, String authorization) {
        this.id = id;
        this.authorization = authorization;
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
