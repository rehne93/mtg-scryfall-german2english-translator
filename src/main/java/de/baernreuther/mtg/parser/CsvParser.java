package de.baernreuther.mtg.parser;

import com.google.gson.Gson;
import de.baernreuther.mtg.scryfall.Card;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements Parser {

    private final String ENGL_HEADER = "Name (engl)";
    private final String DE_HEADER = "Name (deu)";


    private Gson gson = new Gson();
    private List<Card> foundCards = new ArrayList<>();

    @Override
    public List<Card> parse(InputStream inputStream) throws IOException {
        CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new InputStreamReader(inputStream));
        this.foundCards = new ArrayList<>();
        for (CSVRecord record : parser) {
            if (record.get(ENGL_HEADER).equals("")) {
                executeRequestForCard(record.get(DE_HEADER));
            } else {
                executeRequestForCard(record.get(ENGL_HEADER));
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this.foundCards;
    }

    private void executeRequestForCard(String unencodedCardname) throws IOException {
        String encodedCardName = URLEncoder.encode(unencodedCardname, StandardCharsets.UTF_8);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://api.scryfall.com/cards/named?fuzzy=" + encodedCardName);
        CloseableHttpResponse response = httpclient.execute(get);
        String content = new String(response.getEntity().getContent().readAllBytes());
        Card card = gson.fromJson(content, Card.class);
        this.foundCards.add(card);
    }
}
