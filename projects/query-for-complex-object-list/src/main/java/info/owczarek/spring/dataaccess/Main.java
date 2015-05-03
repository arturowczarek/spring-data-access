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
        OfficesRepository officesRepository = context.getBean("officesRepository", OfficesRepository.class);

        List<Office> allOffices = officesRepository.getAllOffices();

        for (Office office : allOffices) {
            System.out.println(office);
        }
    }
}
