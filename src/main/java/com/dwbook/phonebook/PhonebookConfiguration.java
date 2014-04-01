package com.dwbook.phonebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Max;

public class PhonebookConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    private String message;

    @JsonProperty
    private String additionalMessage = "This is optional";

    @Valid
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    @Max(10)
    private int messageRepititions;

    public String getMessage() {
        return message;
    }

    public int getMessageRepititions() {
        return messageRepititions;
    }

    public String getAdditionalMessage() {
        return additionalMessage;
    }

    public DataSourceFactory getDatabase() {
        return database;
    }
}
