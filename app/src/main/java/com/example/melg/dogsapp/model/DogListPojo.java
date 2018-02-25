package com.example.melg.dogsapp.model;

import java.util.List;

/**
 * Created by melg on 2/25/18.
 */

public class DogListPojo {


    private List<String> message;
    private String status;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
