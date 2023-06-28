package ReflectionAnotation.Cadastro.dao.Cliente;



import ReflectionAnotation.Cadastro.Exception.TipoChaveNaoEncontradaException;
import ReflectionAnotation.Cadastro.dao.Generic.SingletonMap;
import ReflectionAnotation.Cadastro.dao.Generic.GenericDAO;
import ReflectionAnotation.Cadastro.domain.Cliente;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClienteMapDAO extends GenericDAO<Cliente> implements IClienteDAO {

    public ClienteMapDAO() {
        super();
        Map<Long, Cliente> mapaInterno =
                (Map<Long, Cliente>) SingletonMap.getInstance().getMap().get(getTipoClasse());
        if (mapaInterno == null) {
            mapaInterno = new HashMap<>();
            SingletonMap.getInstance().getMap().put(getTipoClasse(), mapaInterno);
        }
    }

    @Override
    public Class<Cliente> getTipoClasse() {
        return Cliente.class;
    }

    @Override
    public void atualizarDados(Cliente cliente, Cliente entityCadastrado) {
        entityCadastrado.setNome(cliente.getNome());
        entityCadastrado.setTel(cliente.getTel());
        entityCadastrado.setNumero(cliente.getNumero());
        entityCadastrado.setEnd(cliente.getEnd());
        entityCadastrado.setCidade(cliente.getCidade());
        entityCadastrado.setEstado(cliente.getEstado());
    }


    private Map<Long, Cliente> map;

/*    public ClienteMapDAO() {
        this.map = new HashMap<>();
    }*/

    @Override
    public Boolean cadastrar(Cliente entity) {
        if (this.map.containsKey(entity.getCpf())) {
            return false;
        }
        this.map.put(Long.valueOf(entity.getCpf()), entity);
        return true;
    }


    @Override
    public void excluir(Long valor) {
        Cliente clienteCadastrado = (Cliente) this.map.get(getTipoClasse());

        if (clienteCadastrado != null) {
            this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        Cliente clienteCadastrado = this.map.get(cliente.getCpf());
        if (clienteCadastrado != null) {
            clienteCadastrado.setNome(cliente.getNome());
            clienteCadastrado.setTel(cliente.getTel());
            clienteCadastrado.setNumero(cliente.getNumero());
            clienteCadastrado.setEnd(cliente.getEnd());
            clienteCadastrado.setCidade(cliente.getCidade());
            clienteCadastrado.setEstado(cliente.getEstado());
        }
    }

    @Override
    public Cliente consultar(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return this.map.values();
    }
}
