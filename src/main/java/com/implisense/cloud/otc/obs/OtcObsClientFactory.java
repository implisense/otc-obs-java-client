package com.implisense.cloud.otc.obs;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.SignerFactory;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * This class should be instantiated using the factory method as the special signer
 * has to be registered previously. For example:
 * <code><pre>
 * String accessKey = "....................";
 * String secretKey = "........................................";
 * AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
 *
 * AmazonS3 obs = OtcObsClientFactory.create(credentials);
 * </pre></code>
 *
 * @author Hannes Korte (hannes.korte@implisense.com)
 */
public class OtcObsClientFactory {

    /**
     * If this factory is used to create the instance, there is no need to register the special signer manually.
     * The default configuration uses the HTTPS protocol.
     * @param credentials
     * @return A new instance of this class
     */
    public static AmazonS3 create(AWSCredentials credentials) {
        return create(new AWSStaticCredentialsProvider(credentials));
    }

    /**
     * If this factory is used to create the instance, there is no need to register the special signer manually.
     * The default configuration uses the HTTPS protocol.
     * @param credentialsProvider
     * @return A new instance of a configured AmazonS3Client
     */
    public static AmazonS3 create(AWSCredentialsProvider credentialsProvider) {
        return create(credentialsProvider, new ClientConfiguration());
    }

    /**
     * If this factory is used to create the instance, there is no need to register the special signer manually.
     * @param credentials
     * @param clientConfiguration
     * @return A new instance of this class
     */
    public static AmazonS3 create(AWSCredentials credentials, ClientConfiguration clientConfiguration) {
        return create(new AWSStaticCredentialsProvider(credentials), clientConfiguration);
    }

    /**
     * If this factory is used to create the instance, there is no need to register the special signer manually.
     * @param credentialsProvider
     * @param clientConfiguration
     * @return A new instance of this class
     */
    public static AmazonS3 create(AWSCredentialsProvider credentialsProvider, ClientConfiguration clientConfiguration) {
        SignerFactory.registerSigner("OtcObsSigner", OtcObsSigner.class);
        clientConfiguration.setSignerOverride("OtcObsSigner");

        clientConfiguration.setProtocol(Protocol.HTTPS);

        AmazonS3ClientBuilder clientBuilder = AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withClientConfiguration(clientConfiguration)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("obs.otc.t-systems.com", "eu-de"));

        return clientBuilder.build();
    }

}
