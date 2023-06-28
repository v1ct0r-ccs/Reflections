package ReflectionAnotation.Cadastro.Fabrica;

public interface IFactory {
    IFabricaPersistente criarFabrica(String opcaoMenuGeral);
}
