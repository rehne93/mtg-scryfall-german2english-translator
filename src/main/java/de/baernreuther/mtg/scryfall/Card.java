package de.baernreuther.mtg.scryfall;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Card {

    private String id;
    private String name;
    private String printed_name;
    private double cmc;
    private String rarity;


    /**
     * Returns the CSV-Records that are relevant for the output
     * in the correct order
     *
     * @return List of Data for CSV-File
     */
    public List<String> getCsvRecords() {
        List<String> records = new ArrayList<>();
        records.add(name);
        records.add(Objects.requireNonNullElse(printed_name, ""));
        records.add(rarity);
        records.add(String.valueOf(cmc));
        return records;
    }

    /**
     * Returns the header for the CSV-Records
     *
     * @return List of headers
     */
    public static List<String> getCsvHeader() {
        List<String> headers = new ArrayList<>();
        headers.add("Englisch");
        headers.add("Deutsch");
        headers.add("Seltenheit");
        headers.add("CMC");
        return headers;
    }
}
