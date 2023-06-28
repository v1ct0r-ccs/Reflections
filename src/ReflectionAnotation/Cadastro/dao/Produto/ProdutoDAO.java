package ReflectionAnotation.Cadastro.dao.Produto;

import ReflectionAnotation.Cadastro.Exception.TipoChaveNaoEncontradaException;
import ReflectionAnotation.Cadastro.dao.Generic.SingletonMap;
import ReflectionAnotation.Cadastro.dao.Generic.GenericDAO;
import ReflectionAnotation.Cadastro.domain.Produto;

import java.util.HashMap;
import java.util.Map;


public class ProdutoDAO extends GenericDAO<Produto> implements IProdutoDAO  {

    public ProdutoDAO() {
        super();
        Map<Long, Produto> mapaInterno =
                (Map<Long, Produto>) SingletonMap.getInstance().getMap().get(getTipoClasse());
        if (mapaInterno == null) {
            mapaInterno = new HashMap<>();
            SingletonMap.getInstance().getMap().put(getTipoClasse(), mapaInterno);
        }
    }

    @Override
    public Class<Produto> getTipoClasse() {
        return Produto.class;
    }

    @Override
    public void atualizarDados(Produto entity, Produto entityCadastrado) {
        entityCadastrado.setNome(entity.getNome());
        entityCadastrado.setCodigo(entity.getCodigo());
        entityCadastrado.setPreco(entity.getPreco());
        entityCadastrado.setQuant(entity.getQuant());
    }

    @Override
    public boolean excluir(Long codigo) {
        Map<Long, Produto> mapaInterno =
                (Map<Long, Produto>) SingletonMap.getInstance().getMap().get(getTipoClasse());
        Produto produto = mapaInterno.remove(codigo);
        return produto != null;
    }

    @Override
    public Produto consultar(Long codigo) {
        Map<Long, Produto> mapaInterno =
                (Map<Long, Produto>) SingletonMap.getInstance().getMap().get(getTipoClasse());
        return mapaInterno.get(codigo);
    }

    @Override
    public Boolean cadastrar(Produto produto) throws TipoChaveNaoEncontradaException {
        Map<Long, Produto> mapaInterno = (Map<Long, Produto>)SingletonMap.getInstance().getMap().get(getTipoClasse());
        Long chave = getChave(produto);
        if (mapaInterno.containsKey(chave)) {
            return false;
        }

        mapaInterno.put(chave, produto);
        return true;
    }
}
