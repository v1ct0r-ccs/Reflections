package ReflectionAnotation.Cadastro.dao.Produto;


import ReflectionAnotation.Cadastro.Exception.TipoChaveNaoEncontradaException;
import ReflectionAnotation.Cadastro.dao.Generic.IGenericDAO;
import ReflectionAnotation.Cadastro.domain.Produto;

import java.util.Collection;

public interface IProdutoDAO extends IGenericDAO<Produto> {
    public Boolean cadastrar(Produto produto) throws TipoChaveNaoEncontradaException;

    public boolean excluir(Long codigo);

    public void alterar(Produto produto) throws TipoChaveNaoEncontradaException;

    public Produto consultar(Long codigo);

    public Collection<Produto> buscarTodos();
}
