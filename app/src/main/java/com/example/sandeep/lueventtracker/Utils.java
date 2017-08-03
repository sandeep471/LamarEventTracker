package com.example.sandeep.lueventtracker;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeep on 3/15/2017.
 */

public class Utils {

    private Utils() {
    }

    public static List<EventList> fetchEventList(String eventsUrl) {

        ArrayList<EventList> events = new ArrayList<>();
        Document doc = null;
        String link;
        String summary = null;
        try {
            doc = Jsoup.connect(eventsUrl).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Elements content = doc.getElementsByClass("three-fourths block");
        Elements content1 = doc.getElementsByClass("one-fourth block");
        //System.out.println(content.toString());
        for (int i = 0; i < content.size(); i++) {
            Element links = content.get(i);
            Element dateBlock = content1.get(i);
            //System.out.println(links.toString());
            //for (Element link : links) {

            Elements linkText = links.getElementsByTag("a");
            String[] titleArr = linkText.text().toString().split("more");
            String title = titleArr[0];

            String date = links.getElementsByClass("eventDate").text().toString();
//            String[] date1 = date.split(",");
//            String month = date1[1].toString().substring(1,4);
//            String day = date1[1].toString().substring(7);
            String month = dateBlock.getElementsByClass("event-datetop").text().toString();
            String day = dateBlock.getElementsByClass("event-datebottom").text().toString();


            try{
                String[] summaryArr1 = links.text().toString().split(title);
                String[] summaryArr2 = summaryArr1[1].split(date);
                summary = summaryArr2[0];
            }catch (ArrayIndexOutOfBoundsException e){
                Log.e("Utils", e.toString());
            }


            link = links.getElementsByAttribute("href").attr("href").toString().trim();

            //System.out.println(title + "\n" + summary + "\n" + month + "\n" +day + "\n" + link + "\n\n");

            EventList eventList = new EventList(title, summary, month, day, link);
            events.add(eventList);
        }
        return events;
    }


    public static List<EventDetails> fetchEventDetails(String detailsUrl){

        ArrayList<EventDetails> eventDetails = new ArrayList<>();

        Document doc = null;
        try {
            String url = "http://events.lamar.edu/"+detailsUrl;
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String title = doc.getElementsByTag("h2").text().toString();
        System.out.println(title+"\n");
        Elements content = doc.getElementsByClass("eventleft");
        Elements content1 = doc.getElementsByClass("eventright");
        Elements content2 = doc.getElementsByClass("eventdetails block");
        String data1;
        String[] output = new String[6];
        for(int i=0; i<content.size();i++){
            String data = content.get(i).text();
            System.out.println(data+" ");

            if(data.equalsIgnoreCase("Details:")){
                output[4] = content2.get(0).text();
            } else if(data.equalsIgnoreCase("Date:") || data.equalsIgnoreCase("Starts:")){
                output[0] = content1.get(i).text();
            } else if(data.equalsIgnoreCase("Time:")){
                output[1] = content1.get(i).text();
            } else if(data.equalsIgnoreCase("Category:")){
                output[2] = content1.get(i).text();
            } else if(data.equalsIgnoreCase("Location:")){
                output[3] = content1.get(i).text();
            } else if(data.equalsIgnoreCase("Contact:")){
                output[5] = content1.get(i-1).text();
            }
            //output[i] = data1;
            //eventDetails = (List<EventDetails>) new EventDetails(title, output[0], output[1], output[2], output[3], output[4], output[5]);
        }
        //for(int j=0; j<6; j++){
            EventDetails eventList = new EventDetails(title, output[0], output[1], output[2], output[3], output[4], output[5]);
            eventDetails.add(eventList);
        //}
        System.out.println(title+"\n"+output[0]+"\n"+output[1]+"\n"+output[2]+"\n"+output[3]+"\n"+output[4]+"\n"+output[5]);
        return eventDetails;
    }

}
