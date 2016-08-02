package com.implisense.cloud.otc.obs;

import com.amazonaws.services.s3.internal.AWSS3V4Signer;

/**
 * @author Hannes Korte (hannes.korte@implisense.com)
 */
public class OtcObsSigner extends AWSS3V4Signer {
    public OtcObsSigner() {
        super();
        // this differs from the service name "obs" which is used to construct the URIs.
        this.serviceName = "s3";
    }

    @Override
    public void setServiceName(String serviceName) {
        /*
         * We ignore any other serviceName here. The client usually uses the same service name for the signer as for
         * constructing service endpoint URLs. But for OBS we need "obs" in the URL and "S3" in the signature.
         */
    }
}
