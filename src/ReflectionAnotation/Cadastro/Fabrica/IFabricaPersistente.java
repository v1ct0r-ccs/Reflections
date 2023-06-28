package ReflectionAnotation.Cadastro.Fabrica;

import ReflectionAnotation.Cadastro.Exception.DadosInvalidosException;
import ReflectionAnotation.Cadastro.domain.IPersistente;

public interface IFabricaPersistente {
    IPersistente criarObjeto(String dados[]) throws DadosInvalidosException;
}
