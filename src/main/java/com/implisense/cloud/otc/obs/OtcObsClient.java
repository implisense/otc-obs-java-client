package com.implisense.cloud.otc.obs;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.SignerFactory;
import com.amazonaws.regions.InMemoryRegionImpl;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * This class should be instantiated using the factory method as the special signer
 * has to be registered previously. For example:
 * <code><pre>
 * String accessKey = "....................";
 * String secretKey = "........................................";
 * AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
 *
 * AmazonS3 obs = OtcObsClient.create(credentials);
 * </pre></code>
 *
 * @author Hannes Korte (hannes.korte@implisense.com)
 */
public class OtcObsClient extends AmazonS3Client {

    public OtcObsClient(AWSCredentials credentials, ClientConfiguration clientConfiguration) {
        super(credentials, clientConfiguration);
        this.setSignerRegionOverride("eu-de");
        this.setRegion(new Region(new InMemoryRegionImpl("eu-de", "otc.t-systems.com")));
    }

    /**
     * If this factory is used to create the instance, there is no need to register the special signer manually.
     * The default configuration uses the HTTPS protocol.
     * @param credentials
     * @return A new instance of this class
     */
    public static OtcObsClient create(AWSCredentials credentials) {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTPS);
        return create(credentials, clientConfiguration);
    }

    /**
     * If this factory is used to create the instance, there is no need to register the special signer manually.
     * @param credentials
     * @param clientConfiguration
     * @return A new instance of this class
     */
    public static OtcObsClient create(AWSCredentials credentials, ClientConfiguration clientConfiguration) {
        SignerFactory.registerSigner("OtcObsSigner", OtcObsSigner.class);
        clientConfiguration.setSignerOverride("OtcObsSigner");
        return new OtcObsClient(credentials, clientConfiguration);
    }

    @Override
    protected String getServiceNameIntern() {
        return "obs";
    }
}
