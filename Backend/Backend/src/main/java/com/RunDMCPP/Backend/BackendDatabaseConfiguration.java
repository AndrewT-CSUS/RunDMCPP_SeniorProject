package com.RunDMCPP.Backend;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.model.TimeToLiveSpecification;
import com.amazonaws.services.dynamodbv2.model.UpdateTimeToLiveRequest;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BackendDatabaseConfiguration. This class configures settings for the backend.
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.RunDMCPP.Backend")
public class BackendDatabaseConfiguration {

    // Inject DynamoDB endpoint URL from app properties, then region
    @Value("${amazon.dynamodb.endpoint}")
    private String dBEndpoint;
    @Value("${amazon.dynamodb.region}")
    private String dBRegion;
    //@Value("${ttl.enabled}")
    //private boolean ttlEnabled; 
    // Method makes bean (Spring object) for AmazonDynamoDB client
    @Bean
    public AmazonDynamoDB amazonDynamoDB(){

        DefaultAWSCredentialsProviderChain credentialsProvider = new DefaultAWSCredentialsProviderChain();

        // Build and configure AmazonDynamoDB client
        AmazonDynamoDB amazonDynamoDB = AmazonDynamoDBAsyncClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dBEndpoint, dBRegion))
                .withCredentials(credentialsProvider).build();
        return amazonDynamoDB;
    }

    /*private void enableTTL(AmazonDynamoDB amazonDynamoDB, String tableName) { //specifies and enables ttl attribute for a table
        
            UpdateTimeToLiveRequest updateTimeToLiveRequest = new UpdateTimeToLiveRequest().withTableName(tableName)
            .withTimeToLiveSpecification(new TimeToLiveSpecification().withAttributeName("ttl").withEnabled(true)); 

            amazonDynamoDB.updateTimeToLive(updateTimeToLiveRequest);
        
         

    }*/

    
}
