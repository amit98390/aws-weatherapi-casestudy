package com.casestudy.a61098390.awsWeatherApiCasestudy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {

	@Value("${aws.region}")
	private String awsRegion;
	
	/*
	 * @Value("${aws.accesskey}") private String awsAccessKey;
	 * 
	 * @Value("${aws.secretkey}") private String awsSecretKey;
	 */
	
	@Value("${aws.dynamodb.endpoint}")
	private String awsDynamoDBEndpoint;
	/*
	 * @Bean public AWSCredentials awsCredentials() { return new
	 * BasicAWSCredentials(awsAccessKey, awsSecretKey); }
	 * 
	 * @Bean public AWSCredentialsProvider awsCredProvider() { return new
	 * AWSStaticCredentialsProvider(awsCredentials()); }
	 */
	
	@Bean
	public AmazonDynamoDB dynamoDb() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamoDBEndpoint, awsRegion))
				.withCredentials(new InstanceProfileCredentialsProvider(false))
				.build();
	}
	
	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(dynamoDb());
	}
}

