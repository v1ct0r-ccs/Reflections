package ReflectionAnotation.Cadastro.dao.Generic;

import ReflectionAnotation.Cadastro.dao.Generic.SingletonMap;
import ReflectionAnotation.Anotation.ITipoChave;
import ReflectionAnotation.Cadastro.domain.IPersistente;
import ReflectionAnotation.Cadastro.Exception.TipoChaveNaoEncontradaException;
import ReflectionAnotation.Cadastro.domain.Cliente;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public abstract class GenericDAO<T extends IPersistente> implements IGenericDAO<T>{

    //protected Map<Class, Map<Long, T>> map;

    /**
     * Necessário utilizar Singleton para ter apenas um MAP no sistema
     */
    private SingletonMap singletonMap;

    public abstract Class<T> getTipoClasse();

    public abstract void atualizarDados(T entity, T entityCadastrado);

    public GenericDAO() {
        this.singletonMap = SingletonMap.getInstance();
        }

    public Long getChave(T entity) throws TipoChaveNaoEncontradaException {
        Field[] fields = entity.getClass().getDeclaredFields();
        Long returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(ITipoChave.class)) {
                ITipoChave tipoChave = field.getAnnotation(ITipoChave.class);
                String nomeMetodo = tipoChave.value();
                try {
                    Method method = entity.getClass().getMethod(nomeMetodo);
                    returnValue = (Long) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                   e.printStackTrace();
                   throw new TipoChaveNaoEncontradaException("Chave principal do objeto " + entity.getClass() + " não encontrada", e);
                }
            }
        }
        if (returnValue == null){
            String msg = "Chave principal do objeto " + entity.getClass() + " não encontrada";
            System.out.println("**** ERRO ****" + msg);
            throw new TipoChaveNaoEncontradaException(msg);
        }
        return null;
    }

    @Override
    public Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException {
        //Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
        Map<Long, T> mapaInterno = (Map<Long, T>) this.singletonMap.getMap().get(getTipoClasse());
        Long chave = getChave(entity);
        if (mapaInterno.containsKey(chave)) {
            return false;
        }

        mapaInterno.put(chave, entity);
        return true;
    }

    @Override
    public boolean excluir(String valor) {
        //Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
        Map<Long, T> mapaInterno = (Map<Long, T>) this.singletonMap.getMap().get(getTipoClasse());
        for (Map.Entry<Long, T> entry : mapaInterno.entrySet()) {
            T objetoCadastrado = entry.getValue();
            if (objetoCadastrado instanceof Cliente) {
                Cliente cliente = (Cliente) objetoCadastrado;

                if (cliente.getCpf().equals(valor)) {
                    mapaInterno.remove(entry.getKey());
                    break;
                }
            }
        }
        return false;
    }

    @Override
    public void alterar(T entity) throws TipoChaveNaoEncontradaException {
        //Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
        Map<Long, T> mapaInterno = (Map<Long, T>) this.singletonMap.getMap().get(getTipoClasse());
        Long chave = getChave(entity);
        T objetoCadastrado = mapaInterno.get(chave);
        if (objetoCadastrado != null) {
            atualizarDados(entity, objetoCadastrado);
        }
    }

    @Override
    public T consultar(Long valor) {
        //Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
        Map<Long, T> mapaInterno = (Map<Long, T>) this.singletonMap.getMap().get(getTipoClasse());
        return mapaInterno.get(valor);
    }

    @Override
    public Collection<T> buscarTodos() {
        //Map<Long, T> mapaInterno = this.map.get(getTipoClasse());
        Map<Long, T> mapaInterno = (Map<Long, T>) this.singletonMap.getMap().get(getTipoClasse());
        return mapaInterno.values();
    }
}
