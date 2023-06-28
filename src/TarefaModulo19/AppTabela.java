package TarefaModulo19;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;



public class AppTabela {
    public static void main(String[] args) {
        executeLeituraAnotacao();
    }

    private static void executeLeituraAnotacao() {
        TabelaComAnotacao tabelaComAnotacao = new TabelaComAnotacao();

        Class<?> classe = tabelaComAnotacao.getClass();

        if (classe.isAnnotationPresent(ITabela.class)) {
            Annotation annotation = classe.getAnnotation(ITabela.class);
            ITabela tabela = (ITabela) annotation;

            System.out.println("**** Annotation ****");
            System.out.println("Annotation type: " + annotation.annotationType());
            System.out.println("**** Valor da anotação ****");
            System.out.println("Nome: " + tabela.value());
            System.out.println("CPF: " + tabela.cpf());
            System.out.println("Idade: " + tabela.idade());
            System.out.println("Cidade: " + tabela.cidade());
            System.out.println("**** Annotation ****");
            System.out.println("Nome da classe: " + tabela.getClass().getInterfaces()[0].getName());
            System.out.println("Nome da anotação com valores: " + tabela.toString());
        } else {
            System.out.println("A anotação não está presente na classe" );
        }
    }
}
