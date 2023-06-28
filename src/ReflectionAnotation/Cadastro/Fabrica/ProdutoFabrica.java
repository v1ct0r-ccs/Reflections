package ReflectionAnotation.Cadastro.Fabrica;

import ReflectionAnotation.Cadastro.Exception.DadosInvalidosException;
import ReflectionAnotation.Cadastro.domain.IPersistente;
import ReflectionAnotation.Cadastro.domain.Produto;

public class ProdutoFabrica implements IFabricaPersistente {
    @Override
    public IPersistente criarObjeto(String[] dados) throws DadosInvalidosException {
        try {
            Produto produto = new Produto();
            produto.setCodigo(Long.parseLong(dados[0].trim()));
            produto.setNome(dados[1]);
            produto.setPreco(Double.parseDouble(dados[2]));
            produto.setQuant(Integer.parseInt(dados[3]));
            return produto;
        } catch(IndexOutOfBoundsException e) {
            throw new DadosInvalidosException("Dados de produto estão inválidos");
        }
    }
}
