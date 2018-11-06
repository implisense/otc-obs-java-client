package com.implisense.cloud.otc.obs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.StringUtils;

public class OtcObsExample {

    public static void main(String[] args) {

        String accessKey = "....................";
        String secretKey = "........................................";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3 obs = OtcObsClientFactory.create(credentials);

        /*
         * This example shows a simple workflow using the object storage
         */

        System.out.println();
        System.out.println("Buckets:");
        for (Bucket bucket : obs.listBuckets()) {
            System.out.println(bucket.getName() + "\t" + StringUtils.fromDate(bucket.getCreationDate()));
        }

        System.out.println();
        System.out.println("Adding bucket \"foosandbars\"");
        obs.createBucket("foosandbars");

        System.out.println();
        System.out.println("Buckets:");
        for (Bucket bucket : obs.listBuckets()) {
            System.out.printf(" - %-30s %s\n",
                    bucket.getName(),
                    bucket.getCreationDate());
        }

        System.out.println();
        System.out.println("Contents of bucket \"foosandbars\":");
        for (S3ObjectSummary objectSummary : obs.listObjects("foosandbars").getObjectSummaries()) {
            System.out.printf(" - %s (%d)     %s\n",
                    objectSummary.getKey(),
                    objectSummary.getSize(),
                    objectSummary.getLastModified());
        }

        System.out.println();
        System.out.println("Putting object \"foo\" into bucket \"foosandbars\"");
        obs.putObject("foosandbars", "foo", "bar");

        System.out.println();
        System.out.println("Contents of bucket \"foosandbars\":");
        for (S3ObjectSummary objectSummary : obs.listObjects("foosandbars").getObjectSummaries()) {
            System.out.printf(" - %-10s %,7d bytes       %s\n",
                    objectSummary.getKey(),
                    objectSummary.getSize(),
                    objectSummary.getLastModified());
        }

        System.out.println();
        System.out.println("Deleting object \"foo\"");
        obs.deleteObject("foosandbars", "foo");

        System.out.println("Deleting bucket \"foosandbars\"");
        obs.deleteBucket("foosandbars");

        System.out.println();
        System.out.println("Buckets:");
        for (Bucket bucket : obs.listBuckets()) {
            System.out.printf(" - %-30s %s\n", bucket.getName(), bucket.getCreationDate());
        }
    }
}
