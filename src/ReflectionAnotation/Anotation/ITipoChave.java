package ReflectionAnotation.Anotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ITipoChave {
    String value();
}
