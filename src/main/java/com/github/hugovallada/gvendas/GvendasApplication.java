package com.github.hugovallada.gvendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// Só precisa informar, caso os seus pacotes não estejam no pacote principal
@EntityScan(basePackages = {"com.github.hugovallada.gvendas.entidades"})
@EnableJpaRepositories(basePackages = {"com.github.hugovallada.gvendas.repositorio"})
@ComponentScan(basePackages = {"com.github.hugovallada.gvendas.servico", "com.github.hugovallada.gvendas.controlador"})
@SpringBootApplication
public class GvendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(GvendasApplication.class, args);
    }

}
