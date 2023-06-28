package ReflectionAnotation.Cadastro.dao.Cliente;

import ReflectionAnotation.Cadastro.Exception.TipoChaveNaoEncontradaException;
import ReflectionAnotation.Cadastro.dao.Generic.IGenericDAO;
import ReflectionAnotation.Cadastro.domain.Cliente;

import java.util.Collection;

public interface IClienteDAO extends IGenericDAO<Cliente> {
    public Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException;

    public void excluir(Long cpf);

    public void alterar(Cliente cliente);

    public Cliente consultar(Long cpf);

    public Collection<Cliente> buscarTodos();
}
