package sample;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("welcome to job search application");
        System.out.println("Enter job title: ");
        String jobTitle = sc.nextLine();
        System.out.println("Enter location: ");
        String location = sc.nextLine();
        JobAggregator jobAggregator = new JobAggregator(jobTitle, location);
        jobAggregator.populateList();
    }
}