package ReflectionAnotation.Cadastro.Fabrica;

import ReflectionAnotation.Cadastro.Exception.DadosInvalidosException;
import ReflectionAnotation.Cadastro.domain.Cliente;
import ReflectionAnotation.Cadastro.domain.IPersistente;

public class ClienteFabrica implements IFabricaPersistente {
    @Override
    public IPersistente criarObjeto(String dadosSeparados[]) throws DadosInvalidosException {
        try {
            return new Cliente(dadosSeparados[0],dadosSeparados[1],dadosSeparados[2],dadosSeparados[3],dadosSeparados[4],dadosSeparados[5],dadosSeparados[6]);
        } catch(IndexOutOfBoundsException e) {
            throw new DadosInvalidosException("Dados de cliente estão inválidos");
        }

    }
}
