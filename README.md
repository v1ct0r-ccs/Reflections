# Reflections

Reflexão é um recurso da API Java que possibilita aos aplicativos o acesso e a modificação do comportamento de aplicações que estão rodando na *Java Virtual Machine*. Uma classe pode acessar outras classes em tempo de execução, sem conhecer a sua definição no momento da compilação. Informações relativas a esta definição, como os seus construtores, métodos e atributos, podem ser facilmente acessados por métodos de reflexão dda API Java.

## Classes
```
Class c1 = boolean.class;
System.out.print(c1);

Class c2 = java.io.PrintStream.class;
System.out.print(c2);

Class c = Class.forName("Reflections.ReflectionClasses");
System.out.print(c);

System.out.print(ReflectionsClasses.class);
```
- Resposta no console
```
boolean
class java.io.PrintStream
class Reflections.RefectionsClasses
class Reflections.RefectionsClasses
RefectionsClasses
Reflections.RefectionsClasses
```

## Acesso a partir das classes

- **Membro**
    - **Class**
        - **Lista dos Membros? | Membros herdados? | Membros privados?**

- Field
    - getDeclaredField()
        - não | não | sim
    - getField() 
        - não | sim | não
    - getDeclaredFiels()
        - sim | não | sim
    - getFields()
        - sim | sim | não

- Method
    - getDeclaredMethod()
      - não | não | sim
    - getMethod()
      - não | sim | não
    - getDeclaredMethods()
      - sim | não | sim
    - getMethods()
      - sim | sim | não

- Constructor
    - getDeclaredConstructor()
        - não | N/A | sim
    - getConstructor()
        - não | N/A | não
    - getDeclaredConstructors()
        - sim | N/A | sim
    - getConstructor()
        - sim | N/A | não

## Construtores

```
System.out.println("**** Construtores ****");
Class prodC = ProdutoReflection.class;
System.out.println(prodC);

Constructor con = prodC.getConstructor();
System.out.println(con);

ProdutoReflection prod = (ProdutoReflection) con.newInstance();
System .out.println(prod);

Constructor con1 = prodC.getConstructor(Long.class);
System.out.println(con1);
ProdutoReflextion prod1 = (ProdutoReflection) con1.newInstance(10L);
System.out.println(prod1 + " tem o valor: " + prod1.getCodigo());

Constructor[] constructor = prodC.getDeclaredConstructors();
System.out.println("Construtores declarados");
for (Constructor cons : constructos) {
System.out.println(cons);
}
```

## Propriedades
```
System.out.println("**** Propriedades ****");
ProdutoReflection prod = new ProdutoReflection();
Field[] field = prod.getClass().getDeclaredFielsd();
por (Fiels field : fields) {
    System.out.println("Nome completo: " + field);
    System.out.println("Nome simples: " + field.getName());
    System.out.println("Tipo da propriedade: " + field.getType());
    System.out.println();
}
```

## Métodos
```
System.out.println("**** Métodos ****");
ProtudoReflection prod = new ProdutoReflection();
Method[] methods = prod.getClass().getDeclaredMethods();
for (Method m : methods) {
    System.out.println("Nome completo: " + m);
    System.out.println("Nome simples: " + m.getName());
    System.out.println("Tipo de retorno: " + m.getReturnType());
    System.out.println();
}

Method method = prod.getClass().getMethod("getNome");
System.out.println("Pegando método pelo nome: " + method.getName());

Method method1 = prod.getClass().getMethod("setNome", String.class);
System.out.println("Pegando método pelo nome: " + method1.getName());

method1.invoke(prod, "Victor");
System.out.println("Pegando valor do Nome: " + method.invoke(prod));
```
