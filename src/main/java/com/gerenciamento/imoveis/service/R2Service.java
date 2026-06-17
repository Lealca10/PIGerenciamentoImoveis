package com.gerenciamento.imoveis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
public class R2Service {

    @Autowired
    private S3Client s3Client;

    @Value("${cloudflare.r2.bucket}")
    private String bucket;

    @Value("${cloudflare.r2.public-url}")
    private String publicUrl;

    public String uploadFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest putReq = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(file.getContentType() != null ? file.getContentType() : "application/octet-stream")
                .contentLength((long) bytes.length)
                .build();

        s3Client.putObject(putReq, RequestBody.fromBytes(bytes));

        if (publicUrl.endsWith("/")) {
            return publicUrl + key;
        }
        return publicUrl + "/" + key;
    }
}