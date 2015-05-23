package sundayschool.deividazevedo.br.sundayschool;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by DVD on 09/05/2015.
 */
public class Relatorio implements Serializable {

    Integer id;
    Integer dia, mes, ano;
    Integer totalPresentes,totalBiblias;

    public Relatorio(Integer dia, Integer mes, Integer ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public Relatorio(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getTotalPresentes() {
        return totalPresentes;
    }

    public void setTotalPresentes(Integer totalPresentes) {
        this.totalPresentes = totalPresentes;
    }

    public Integer getTotalBiblias() {
        return totalBiblias;
    }

    public void setTotalBiblias(Integer totalBiblias) {
        this.totalBiblias = totalBiblias;
    }
}
