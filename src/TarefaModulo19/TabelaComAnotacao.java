package TarefaModulo19;

@ITabela(value = "Victor", idade = 29, cidade = "Sao Paulo", cpf = 12346)
public class TabelaComAnotacao {
    private String nome;
    private Integer idade;
    private String cidade;
    private Long cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
}
