package TarefaModulo19;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ITabela {
    String value();
    long cpf();
    int idade();
    String cidade();

}
