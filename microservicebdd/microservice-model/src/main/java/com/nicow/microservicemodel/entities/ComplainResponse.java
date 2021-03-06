package com.nicow.microservicemodel.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "complainresponse")
public class ComplainResponse {

    @Id
    private String id;
    private String response;
    private String extLink;
    private int popularity;
    private String creatorEmail;
    private String creatorPseudo;
    private String creationDate;
    private List<String> userWhoChangePopularityList;
    private String requestId;
    private List<ComplainComment> commentList;


    // constructor
    public ComplainResponse() {
    }

    public ComplainResponse(String id, String response, int popularity, String creatorEmail, String creatorPseudo,
                            String creationDate, List<String> userWhoChangePopularityList, String requestId) {
        this.id = id;
        this.response = response;
        this.extLink = null;
        this.popularity = popularity;
        this.creatorEmail = creatorEmail;
        this.creatorPseudo = creatorPseudo;
        this.creationDate = creationDate;
        this.userWhoChangePopularityList = userWhoChangePopularityList;
        this.requestId = requestId;
        this.commentList = new ArrayList<>();
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

    public String getExtLink() { return extLink; }

    public void setExtLink(String extLink) { this.extLink = extLink; }

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

    public List<String> getUserWhoChangePopularityList() {
        return userWhoChangePopularityList;
    }

    public void setUserWhoChangePopularityList(List<String> userWhoChangePopularityList) {
        this.userWhoChangePopularityList = userWhoChangePopularityList;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<ComplainComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<ComplainComment> commentList) {
        this.commentList = commentList;
    }

    // to string
    @Override
    public String toString() {
        return "ComplainResponse{" +
                "id='" + id + '\'' +
                ", response='" + response + '\'' +
                ", extLink='" + extLink + '\'' +
                ", popularity=" + popularity +
                ", creatorEmail='" + creatorEmail + '\'' +
                ", creatorPseudo='" + creatorPseudo + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", userWhoChangePopularityList=" + userWhoChangePopularityList +
                ", requestId='" + requestId + '\'' +
                ", commentList=" + commentList +
                '}';
    }

    // methods
    public void addUserWhoIncreasePopularity(String userInput) {
        this.userWhoChangePopularityList.add(userInput);
    }
    public void addCommentOnResponseCommentList(ComplainComment commentInput) { this.commentList.add(commentInput); }
}
