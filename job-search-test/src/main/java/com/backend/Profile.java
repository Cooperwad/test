package com.backend;

import java.util.ArrayList;
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
    private JobAggregator jobAggregator;

    public Profile() {
        this.jobAggregator = new JobAggregator("", "");
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setEducation(List<String> education) {
        this.education = education;
    }

    @Override
    public void setWorkExperience(List<String> workExperience) {
        this.workExperience = workExperience;
    }

    @Override
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public void setAccomplishments(List<String> accomplishments) {
        this.accomplishments = accomplishments;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getResume() {
        return resume;
    }

    public List<String> getEducation() {
        return education;
    }

    public List<String> getWorkExperience() {
        return workExperience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getAccomplishments() {
        return accomplishments;
    }

    public ArrayList<Job> getJobRecommendations() {
        return jobAggregator.getRecommendedJobs(this);
    }

    public void createFromResume(byte[] resumeData) throws Exception {
        ResumeParser parser = new ResumeParser();
        String resumeText = parser.parseResume(resumeData);
        String extractedEmail = parser.extractEmail(resumeText);
        String extractedName = parser.extractName(resumeText);
        if (extractedName != null) {
            String[] nameParts = extractedName.split("\\s+", 2);
            this.firstName = nameParts[0];
            this.lastName = (nameParts.length > 1) ? nameParts[1] : "";
        }

        this.email = extractedEmail;
        this.resume = resumeData;
    }
}
