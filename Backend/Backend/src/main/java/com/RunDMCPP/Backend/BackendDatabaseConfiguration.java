package com.RunDMCPP.Backend;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.RunDMCPP.Backend")
public class BackendDatabaseConfiguration {

    @Value("${amazon.dynamodb.endpoint}")
    private String dBEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String dBRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        return AmazonDynamoDBAsyncClientBuilder.standard()
                //When we eventually deploy, we should probably use this if we can get it working
                //                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dBEndpoint, dBRegion))
                .build();
    }

}
