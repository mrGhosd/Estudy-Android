package com.example.vsokoltsov.estudy.models;

/**
 * Created by vsokoltsov on 08.03.16.
 */

public class User {
        private String email;
        private String firstName;
        private String lastName;

        public User(String email, String first_name, String last_name ) {
            this.email = email;
            this.firstName = first_name;
            this.lastName = last_name;
        }

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
}
