package com.example.docchecnyznac;

import java.util.List;

public class Document {

    public Description description;
    public String doc_id;
    public String doc_status;
    public String doc_type;
    public boolean importRequest;
    public String owner_inn;
    public String participant_inn;
    public String producer_inn;
    public String production_date;
    public String production_type;
    public List<Product> products;
    public String reg_date;
    public String reg_number;


    public static class Description {
        public String participantInn;
    }


    public static class Product {
        public String certificate_document;
        public String certificate_document_date;
        public String certificate_document_number;
        public String owner_inn;
        public String producer_inn;
        public String production_date;
        public String tnved_code;
        public String uit_code;
        public String uitu_code;
    }
}
