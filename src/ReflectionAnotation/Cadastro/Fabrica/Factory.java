package ReflectionAnotation.Cadastro.Fabrica;

public class Factory implements IFactory {
    @Override
    public IFabricaPersistente criarFabrica(String opcaoMenuGeral) {
        if ("1".equals(opcaoMenuGeral)) {
            return new ClienteFabrica();
        } else {
            return new ProdutoFabrica();
        }
    }
}
