package ReflectionAnotation.Cadastro.dao.Generic;

import ReflectionAnotation.Cadastro.Exception.TipoChaveNaoEncontradaException;
import ReflectionAnotation.Cadastro.domain.IPersistente;

import java.util.Collection;

public interface IGenericDAO <T extends IPersistente> {
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;

    public boolean excluir(String valor);

    public void alterar(T entity) throws TipoChaveNaoEncontradaException;

    public T consultar(Long valor);

    public Collection<T> buscarTodos();
}