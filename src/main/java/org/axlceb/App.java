package org.axlceb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.axlceb.service.StringService;
import org.axlceb.service.StringServiceImp;

import java.io.*;
import java.util.stream.Collectors;

@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("App Starts");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        // Just a fancy resource loader usually handled by Spring Boot
        try (InputStream inputStream = new App().getClass().getResourceAsStream("/application.yml");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String content = reader.lines().collect(Collectors.joining(System.lineSeparator()));

            AppConfig appConfig = mapper.readValue(content, AppConfig.class);
            log.info("Appication: {} version: {}", appConfig.getName(), appConfig.getVersion());

            StringService stringService = StringServiceImp.create();

            // Run the cases from the YAML file
            appConfig.getCases().stream().forEach(c -> stringService.add(c));

        } catch (IOException e) {
            log.error("Error Reading the Config files", e);
        } catch (NullPointerException e) {
            log.error("Not such Config files", e);
        } catch (Exception e) {
            log.error("Unexpected error", e);
        }

        log.info("App Ends");
    }
}
