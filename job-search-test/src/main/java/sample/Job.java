package sample;

public class Job implements interfaces.Job {

    private String title;
    private String company;
    private String location;
    private String date;
    private String url;

    public Job(String title, String company, String location, String date) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.date = date;
    }

    @Override
    public void favorite() {

    }

    @Override
    public void apply() {

    }
}
