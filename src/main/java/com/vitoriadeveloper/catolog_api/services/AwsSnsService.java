package com.vitoriadeveloper.catolog_api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitoriadeveloper.catolog_api.dto.AwsMessageCategoryDTO;
import com.vitoriadeveloper.catolog_api.dto.AwsMessageProductDTO;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class AwsSnsService {
    private final SnsClient snsClient;
    private final String catalogTopic;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AwsSnsService(SnsClient snsClient, @Qualifier("catalogTopic") String catalogTopic) {
        this.snsClient = snsClient;
        this.catalogTopic = catalogTopic;
    }

    public void publishProdct(AwsMessageProductDTO messageDTO) {
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

    public void publishCategory(AwsMessageCategoryDTO messageDTO) {
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
