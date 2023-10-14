package com.github.platformoon.infra.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.platformoon.domain.ci.Pipeline;
import com.github.platformoon.domain.ci.PipelineExecutor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.InternalServerErrorException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static jakarta.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@ApplicationScoped
public class TektonPipelineClient implements PipelineExecutor {


    private static final Logger LOGGER = LoggerFactory.getLogger(TektonPipelineClient.class);
    private final ObjectMapper objectMapper;
    @ConfigProperty(name = "tekton.listener.url")
    String tektonListenerUrl;

    public TektonPipelineClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void executePipeline(Pipeline pipeline) {
        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            String body = objectMapper.writeValueAsString(pipeline);
            LOGGER.info("request body to tekton: {}", body);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(tektonListenerUrl))
                    .header(CONTENT_TYPE, APPLICATION_JSON)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            LOGGER.info("response from tekton: {}", response.body());

            if (response.statusCode() != 202) {
                throw new InternalServerErrorException("tekton pipeline status code is not 200");
            }

        } catch (IOException | InterruptedException e) {
            LOGGER.error("error while calling tekton pipeline listener", e);
            throw new InternalServerErrorException(e);
        } catch (InternalServerErrorException e) {
            LOGGER.error("error while calling tekton pipeline listener", e);
        }
    }
}
