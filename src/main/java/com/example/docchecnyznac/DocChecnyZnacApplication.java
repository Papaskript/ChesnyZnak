package com.example.docchecnyznac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DocChecnyZnacApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocChecnyZnacApplication.class, args);

        Document document = new Document();
        document.description = new Document.Description();
        document.description.participantInn = "123456789";
        document.doc_id = "DOC123";
        document.doc_status = "DRAFT";
        document.doc_type = "Secret";
        document.importRequest = true;
        document.owner_inn = "0987654321";
        document.participant_inn = "12345";
        document.producer_inn = "12345";
        document.production_date = "2024-06-27";
        document.production_type = "Secret";
        document.reg_date = "2024-06-27";
        document.reg_number = "REG123";

        Document.Product product1 = new Document.Product();
        product1.certificate_document = "Сертификат777";
        product1.certificate_document_date = "2024-06-27";
        product1.certificate_document_number = "Номер Сертификата 2";
        product1.owner_inn = "12345";
        product1.producer_inn = "12345";
        product1.production_date = "2024-06-27";
        product1.tnved_code = "0";
        product1.uit_code = "UIT12345";
        product1.uitu_code = "UITU12345";

        Document.Product product2 = new Document.Product();
        product2.certificate_document = "Сертификат66";
        product2.certificate_document_date = "2024-06-27";
        product2.certificate_document_number = "Номер Сертификата 1";
        product2.owner_inn = "123";
        product2.producer_inn = "123";
        product2.production_date = "22024-06-27";
        product2.tnved_code = "333";
        product2.uit_code = "UIT333";
        product2.uitu_code = "UITU444";

        List<Document.Product> products = Arrays.asList(product1, product2);
        document.products = products;


        CheZnakApiClient cheZnakApiClient = new CheZnakApiClient(5);

        String signature = "Моя подпись";

        try {
            cheZnakApiClient.createDocument(document, signature);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}