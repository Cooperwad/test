package com.backend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JobAggregator implements com.backend.interfaces.JobAggregator {

    private ArrayList<Job> jobList = new ArrayList<>();
    private String jobTitle;
    private String location;// Eventually replace with Filter Parameters
    public JobAggregator(String jobTitle, String location) {
        this.jobTitle = jobTitle;
        this.location = location;
    }
    @Override
    public void populateList() throws IOException {
        String query = "keywords=" + URLEncoder.encode(jobTitle.toLowerCase(), StandardCharsets.UTF_8).replace("+", "%20") +
                "&location=" + URLEncoder.encode(location.toLowerCase(), StandardCharsets.UTF_8).replace("+", "%20");

        String url = "https://www.linkedin.com/jobs/search/?" + query + "&position=1&pageNum=0";

        System.out.println(url);
        Document doc = Jsoup.connect(url).get();

        Elements ul=doc.select("ul.jobs-search__results-list");
        Elements li = ul.select("li");

        int i = 0;
        for (Element item : li) {

            String JobTitle= item.getElementsByClass("base-search-card__title").text();
            String company = item.getElementsByClass("base-search-card__subtitle").text();
            String listedLocation = item.getElementsByClass("job-search-card__location").text();
            String date = item.getElementsByClass("job-search-card__listdate").text();

            System.out.println("Job : " + (i + 1));
            System.out.println("Title : " + JobTitle);
            System.out.println("Company : " + company);
            System.out.println("Location : " + listedLocation);
            System.out.println("Date : " + date);
            System.out.println("");
            i++;

            Job job = new Job(jobTitle, company, listedLocation, date);
            jobList.add(job);

            if (i >= 15){
                break;
            }
        }
    }

    @Override
    public void filterJobs() {

    }

    @Override
    public ArrayList<Job> getRecommendedJobs(Profile userProfile) {
        ArrayList<Job> recommendedJobs = new ArrayList<>();

        List<String> combinedCriteria = new ArrayList<>();
        combinedCriteria.addAll(userProfile.getSkills());
        if (userProfile.getSkills() != null) {
            combinedCriteria.addAll(userProfile.getSkills());
        }
        if (userProfile.getWorkExperience() != null) {
            combinedCriteria.addAll(userProfile.getWorkExperience());
        }

        combinedCriteria.replaceAll(String::toLowerCase);

        for (Job job : jobList) {
            for (String criteria : combinedCriteria) {
                if (job.getTitle().toLowerCase().contains(criteria) || job.getCompany().toLowerCase().contains(criteria)) {
                    recommendedJobs.add(job);
                }
            }

        }

        return recommendedJobs;
    }
}
