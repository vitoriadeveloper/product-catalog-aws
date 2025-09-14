package com.vitoriadeveloper.catolog_api.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;


@Configuration
public class AwsSnsConfig {
    public class AWSSNSConfig {
        @Value("${aws.region}")
        private String region;
        @Value("${aws.accessKeyId}")
        private String accessKeyId;
        @Value("${aws.secretKey}")
        private String secretKey;
        @Value("${aws.sns.topic.catalog.arn}")
        private String catalogTopic;


        @Bean
        public SnsClient amazonSNSbuilder() {
            return SnsClient.builder()
                    .region(Region.of(region))
                    .credentialsProvider(
                            StaticCredentialsProvider.create(
                                    AwsBasicCredentials.create(accessKeyId, secretKey)
                            )
                    ).build();
        }

        @Bean(value = "catalogTopic")
        public String catalogTopicArn() {
            return catalogTopic;
        }
    }
}
