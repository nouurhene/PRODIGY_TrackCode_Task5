package webScrap;

import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraper {

    public static void main(String[] args) {
        String url = "http://books.toscrape.com/";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements books = doc.select(".product_pod");

            // Prepare a CSV file to write the data
            FileWriter csvWriter = new FileWriter("books.csv");
            csvWriter.append("Title,Price\n"); // Write the header row

            for (Element book : books) {
                String title = book.select("h3 > a").text();
                String price = book.select(".price_color").text();
                String pr = price.substring(1);

                if (Double.parseDouble(pr) < 25) {
                    // Write the data to the CSV file
                    csvWriter.append(title).append(",")
                            .append(price).append("\n");
                }
            }

            // Close the CSV writer
            csvWriter.flush();
            csvWriter.close();

            System.out.println("Data successfully scraped and saved to books.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
