package interfaces;

import java.io.IOException;

public interface JobAggregator {

    void populateList() throws IOException;
    void filterJobs();
    void getRecommendedJobs();
}
