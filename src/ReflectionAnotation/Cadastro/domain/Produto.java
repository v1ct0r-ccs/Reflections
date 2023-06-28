package ReflectionAnotation.Cadastro.domain;


import ReflectionAnotation.Anotation.ITipoChave;
import ReflectionAnotation.Cadastro.domain.IPersistente;

public class Produto implements IPersistente {

    @ITipoChave("getCodigo")
    private Long codigo;
    private String nome;
    private double preco;
    private Integer quant;

/*    public Produto (){
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quant = quant;
    }*/

    public Long getCodigo() {
       return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    @Override
    public String toString() {
        return "Produto: " +
                "\ncodigo: " + this.codigo +
                "\nNome: " + this.nome +
                "\npreco: " + this.preco +
                "\nquant: " + this.quant;

    }
}
