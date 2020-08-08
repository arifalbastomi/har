package com.albastomi.arif.Model;

public class ProjectData {

    private String id,id_user;
    private String authorization;


    public ProjectData(String id,String id_user, String authorization) {
        this.id = id;
        this.id_user = id_user;
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
     * The iduser
     */
    public String getId_user() {
        return id_user;
    }
    /**
     *
     * @param iduser
     * The iduser
     */
    public void setId_user(String iduser) {
        this.id_user = iduser;
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
