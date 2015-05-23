package sundayschool.deividazevedo.br.sundayschool;

/**
 * Created by DVD on 14/05/2015.
 */
public class Contagem {

    Integer id, idRelatorio;
    Integer presentes, visitantes, biblias;
    String nomeSala;

    public Contagem (){

    }

    public Contagem(Integer presentes, Integer visitantes, Integer biblias){
        this.biblias = biblias;
        this.presentes = presentes;
        this.visitantes = visitantes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(Integer idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public Integer getPresentes() {
        return presentes;
    }

    public void setPresentes(Integer presentes) {
        this.presentes = presentes;
    }

    public Integer getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(Integer visitantes) {
        this.visitantes = visitantes;
    }

    public Integer getBiblias() {
        return biblias;
    }

    public void setBiblias(Integer biblias) {
        this.biblias = biblias;
    }

    public String getNomeSala() {
        return nomeSala;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }
}
