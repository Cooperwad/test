package org.example;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Select job title:");
        String jobTitle = sc.nextLine();

//        System.out.println("Select location:");
//        String location = sc.nextLine();

        String query = "keywords=" + URLEncoder.encode(jobTitle, StandardCharsets.UTF_8) +
                "&location=" + URLEncoder.encode("Las Vegas, Nevada United States", StandardCharsets.UTF_8) + "&geoId=1002" + "93800&currentJobId=3415227738&start=25";

        //        Document doc = Jsoup.connect("https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?keywords" +
//                "=Python%20%28Programming%20Language%29&location=Las%20Vegas%2C%20Nevada%2C%20United%20States&geoId=1002" +
//                "93800&currentJobId=3415227738&start=25").get();

        Document doc = Jsoup.connect("https://www.linkedin.com/jobs-guest/jobs/api/seeMoreJobPostings/search?" + query).get();

        System.out.println(doc.location());
        Elements newsHeadlines = doc.select("body li div");
        for (Element headline : newsHeadlines) {
            if (!headline.select(".base-search-card__title").html().isBlank()){
                System.out.println(headline.select(".base-search-card__title").html());
            }
        }

    }
}