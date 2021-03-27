package de.baernreuther.mtg.writer;

import de.baernreuther.mtg.scryfall.Card;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter implements Writer {


    @Override
    public void write(List<Card> cardList) {
        try {
            CSVPrinter printer = new CSVPrinter(new FileWriter(cardList.hashCode() + "cards.csv"), CSVFormat.EXCEL);
            printer.printRecord(Card.getCsvHeader());
            for (Card card : cardList) {
                printer.printRecord(card.getCsvRecords());
            }
            printer.println();
            printer.flush();
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
