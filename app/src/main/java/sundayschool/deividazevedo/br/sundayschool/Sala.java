package sundayschool.deividazevedo.br.sundayschool;

import java.io.Serializable;

/**
 * Created by DVD on 09/05/2015.
 */
public class Sala implements Serializable {

    Integer id;
    String sala;
    String professor;

    public Sala(String sala, String professor){
        this.sala = sala;
        this.professor = professor;
    }

    public Sala(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
