package com.backend.interfaces;

import com.backend.Job;
import com.backend.Profile;

import java.io.IOException;
import java.util.ArrayList;

public interface JobAggregator {

    void populateList() throws IOException;
    void filterJobs();
    ArrayList<Job> getRecommendedJobs(Profile userProfile);
}
