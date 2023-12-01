package com.backend;

import java.io.File;
import java.util.List;

public class Profile implements com.backend.interfaces.Profile {

    private String firstName;
    private String lastName;
    private String email;
    private byte[] resume;
    private List<String> education;
    private List<String> workExperience;
    private List<String> skills;
    private List<String> accomplishments;


    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public void setEducation(List<String> education) {

    }

    @Override
    public void setWorkExperience(List<String> workExperience) {

    }

    @Override
    public void setSkills(List<String> skills) {

    }

    @Override
    public void setAccomplishments(List<String> accomplishments) {

    }
}
