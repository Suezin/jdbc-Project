package Model;

public class Treino {
    private Integer id;
    private String tipoDeTreino;
    private Integer quantidadeDeExercicios;
    private Integer diasDeTreino;
    private Integer idProfessor;

    public Treino(Integer id, Integer quantidadeDeExercicios, String tipoDeTreino, Integer diasDeTreino, Integer idProfessor) {
        this.id = id;
        this.quantidadeDeExercicios = quantidadeDeExercicios;
        this.tipoDeTreino = tipoDeTreino;
        this.diasDeTreino = diasDeTreino;
        this.idProfessor = idProfessor;
    }

    public Treino() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipoDeTreino(String tipoDeTreino) {
        this.tipoDeTreino = tipoDeTreino;
    }

    public void setQuantidadeDeExercicios(Integer quantidadeDeExercicios) {
        this.quantidadeDeExercicios = quantidadeDeExercicios;
    }

    public void setDiasDeTreino(Integer diasDeTreino) {
        this.diasDeTreino = diasDeTreino;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public Integer getId() {
        return id;
    }

    public String getTipoDeTreino() {
        return tipoDeTreino;
    }

    public Integer getQuantidadeDeExercicios() {
        return quantidadeDeExercicios;
    }

    public Integer getDiasDeTreino() {
        return diasDeTreino;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }
}

