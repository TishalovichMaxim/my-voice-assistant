package by.tishalovichm.mva;

import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    @SneakyThrows
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext("by.tishalovichm.mva");
        context.getBean(ApplicationRunner.class).run();
    }
}
