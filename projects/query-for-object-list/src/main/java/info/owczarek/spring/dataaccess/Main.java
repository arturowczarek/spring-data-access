package info.owczarek.spring.dataaccess;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//        ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
        ProductsRepository productsRepository = context.getBean("productsRepository", ProductsRepository.class);

        double average = productsRepository.getProductPrices().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .getAsDouble();

        System.out.println(average);
    }
}
