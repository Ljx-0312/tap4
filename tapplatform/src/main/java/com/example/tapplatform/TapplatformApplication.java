package com.example.tapplatform;

import com.example.tapplatform.codegen.CodeGenerator;
import freemarker.template.TemplateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
@EnableCaching
public class TapplatformApplication {
    public static void main(String[] args) throws IOException, TemplateException {
        ApplicationContext context = SpringApplication.run(TapplatformApplication.class, args);
        CodeGenerator codeGenerator = context.getBean(CodeGenerator.class);
        String outputPath = "src/main/java/com/example/tapplatform";
        codeGenerator.generateCode("Rule", "com.example.tapplatform", outputPath);
    }
}