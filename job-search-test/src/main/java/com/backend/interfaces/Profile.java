package com.backend.interfaces;

import java.util.List;

public interface Profile {
    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setEducation(List<String> education);
    void setWorkExperience(List<String> workExperience);
    void setSkills(List<String> skills);
    void setAccomplishments(List<String> accomplishments);
}
