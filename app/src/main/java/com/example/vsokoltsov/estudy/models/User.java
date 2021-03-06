package com.example.vsokoltsov.estudy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vsokoltsov on 08.03.16.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
        @JsonProperty("email")
        private String email;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        @JsonProperty("image")
        private Attachment image;

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public Attachment getImage() {
                return image;
        }

        public void setImage(Attachment image) {
                this.image = image;
        }

        public String getCorrectName() {
            if (this.firstName != null && this.lastName != null) {
                return this.firstName + " " + this.lastName;
            }
            else {
                return this.email;
            }
        }
}
