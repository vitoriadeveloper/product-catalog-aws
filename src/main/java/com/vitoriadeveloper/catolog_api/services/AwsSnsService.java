package com.vitoriadeveloper.catolog_api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitoriadeveloper.catolog_api.dto.AwsMessageDTO;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.Topic;

@Service
public class AwsSnsService {
    private final SnsClient snsClient;
    private final String catalogTopic;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AwsSnsService(SnsClient snsClient, @Qualifier("catalogTopic") String catalogTopic) {
        this.snsClient = snsClient;
        this.catalogTopic = catalogTopic;
    }

    public void publish(AwsMessageDTO messageDTO) {
        try {
            String json = objectMapper.writeValueAsString(messageDTO);
            PublishRequest request = PublishRequest.builder()
                    .topicArn(catalogTopic)
                    .message(json)
                    .build();

            snsClient.publish(request);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter mensagem para JSON", e);
        }
    }
}
