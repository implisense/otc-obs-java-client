# OTC OBS Java Client
This is a java client for the _Object Storage Service_ of the [Open Telekom Cloud](https://cloud.telekom.de/en/cloud-infrastructure/open-telekom-cloud/).
It is just a minimal wrapper of the AWS java client for S3.

## Usage

```
String accessKey = "....................";
String secretKey = "........................................";
AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

AmazonS3 obs = OtcObsClient.create(credentials);

System.out.println("Buckets:");
for (Bucket bucket : obs.listBuckets()) {
    System.out.println(bucket.getName());
}
```

The `accessKey` and the `secretKey` can be generated in the OTC console under _My Credentials_ > _Access Credentials_.

The versioning is bound to the one of the AWS Java SDK from Amazon. But as it only consists of two classes and is currently not deployed to maven central, you could also simply copy both files into your project. I don't expect them to be changed much unless the interface of either the OBS service or the AWS client change.

## License
This software is licensed under the MIT License:
<pre>
MIT License

Copyright (c) 2016 Implisense GmbH

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
</pre>