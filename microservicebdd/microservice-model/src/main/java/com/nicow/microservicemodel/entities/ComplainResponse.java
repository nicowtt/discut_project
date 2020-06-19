package com.nicow.microservicemodel.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "complainresponse")
public class ComplainResponse {

    @Id
    private String id;
    private String response;
    private int popularity;
    private String creatorEmail;
    private String creatorPseudo;
    private String creationDate;

    // constructor
    public ComplainResponse() {
    }

    public ComplainResponse(String id, String response, int popularity, String creatorEmail, String creatorPseudo, String creationDate) {
        this.id = id;
        this.response = response;
        this.popularity = popularity;
        this.creatorEmail = creatorEmail;
        this.creatorPseudo = creatorPseudo;
        this.creationDate = creationDate;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getCreatorEmail() {
        return creatorEmail;
    }

    public void setCreatorEmail(String creatorEmail) {
        this.creatorEmail = creatorEmail;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreatorPseudo(String creatorPseudo) { this.creatorPseudo = creatorPseudo;}

    public String getCreatorPseudo() { return creatorPseudo;}

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    // to string
    @Override
    public String toString() {
        return "ComplainResponse{" +
                "id='" + id + '\'' +
                ", response='" + response + '\'' +
                ", popularity=" + popularity +
                ", creatorEmail='" + creatorEmail + '\'' +
                ", creatorPseudo='" + creatorPseudo + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
