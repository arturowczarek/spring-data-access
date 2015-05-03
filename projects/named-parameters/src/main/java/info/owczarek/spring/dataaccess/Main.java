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

        ProductsRepository productsRepository = context.getBean("productsRepository", ProductsRepository.class);
        List<Map<String, Object>> productsWithPriceRange = productsRepository.getProductsWithPriceRange(50.0, 60.0);
        for (Map<String, Object> product : productsWithPriceRange) {
            System.out.println(product);
        }
    }
}
