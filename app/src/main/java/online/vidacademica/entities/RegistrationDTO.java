package online.vidacademica.entities;


import android.os.Build;

import androidx.annotation.RequiresApi;


import java.io.Serializable;


public class RegistrationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String date;
    private String user;
    private String classe;

    public RegistrationDTO() {
    }

    public RegistrationDTO(Long id, String date, String user, String classe) {
        this.id = id;
        this.user = user;
        this.classe = classe;
        setDate(date);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        String response = "";
        if (date != null) {
            String[] mesAnoDia = date.toString().split("\\-");
            response = String.format("%s/%s/%s", mesAnoDia[2], mesAnoDia[0], mesAnoDia[1]);
        }
        return response;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDate(String date) {
        String[] splittedDate = date.split("\\/");
        this.date = String.format("%s-%s-%sT00:00:00Z", Integer.parseInt(splittedDate[2]), Integer.parseInt(splittedDate[1]), Integer.parseInt(splittedDate[0]));
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }


}