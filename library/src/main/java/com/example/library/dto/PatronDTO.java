package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PatronDTO {

    @NotBlank(message = "contactInformation is required")
    @NotNull
    private String contactInformation;

    @NotBlank(message = "name is required")
    @NotNull
    private String name;

    // Constructor with fields
    public PatronDTO(String contactInformation, String name) {
        this.contactInformation = contactInformation;
        this.name = name;

    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String ContactInformation) {
        this.contactInformation = contactInformation;
    }

}
