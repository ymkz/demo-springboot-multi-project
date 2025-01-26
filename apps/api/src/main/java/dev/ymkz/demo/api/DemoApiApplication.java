package dev.ymkz.demo.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.ymkz.demo")
@MapperScan(basePackages = "dev.ymkz.demo.core.gateway.datasource")
@OpenAPIDefinition(
  info = @Info(title = "API仕様書", version = "1.0.0"),
  servers = { @Server(url = "http://localhost:8080", description = "ローカル環境") }
)
public class DemoApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApiApplication.class, args);
  }
}
