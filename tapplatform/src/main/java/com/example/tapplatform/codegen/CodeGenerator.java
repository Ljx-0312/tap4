package com.example.tapplatform.codegen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CodeGenerator {
    private final Configuration configuration;

    @Autowired
    public CodeGenerator(@Qualifier("customFreeMarkerConfiguration") Configuration configuration) {
        this.configuration = configuration;
        this.configuration.setClassForTemplateLoading(this.getClass(), "/templates");
    }

    // 其余代码保持不变
    public void generateCode(String entityName, String packagePath, String outputPath) throws IOException, TemplateException {
        Map<String, Object> model = new HashMap<>();
        model.put("entityName", entityName);
        model.put("packagePath", packagePath);

        // 生成 Rule 相关类
        generateFile("RuleEntity.ftl", model, outputPath + "/entity/" + entityName + ".java");
        generateFile("RuleRepository.ftl", model, outputPath + "/repository/" + entityName + "Repository.java");
        generateFile("RuleService.ftl", model, outputPath + "/service/" + entityName + "Service.java");
        generateFile("RuleController.ftl", model, outputPath + "/controller/" + entityName + "Controller.java");

        // 生成 InstructionStats 相关类
        generateFile("InstructionStatsEntity.ftl", model, outputPath + "/entity/InstructionStats.java");
        generateFile("InstructionStatsRepository.ftl", model, outputPath + "/repository/InstructionStatsRepository.java");

        // 生成 RuleExecutionLog 相关类
        generateFile("RuleExecutionLogEntity.ftl", model, outputPath + "/entity/RuleExecutionLog.java");
        generateFile("RuleExecutionLogRepository.ftl", model, outputPath + "/repository/RuleExecutionLogRepository.java");
    }

    private void generateFile(String templateName, Map<String, Object> model, String outputPath) throws IOException, TemplateException {
        Template template = configuration.getTemplate(templateName);
        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(outputFile)) {
            template.process(model, writer);
        }
    }
}