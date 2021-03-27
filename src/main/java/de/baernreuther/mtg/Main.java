package de.baernreuther.mtg;

import de.baernreuther.mtg.parser.CsvParser;
import de.baernreuther.mtg.parser.Parser;
import de.baernreuther.mtg.scryfall.Card;
import de.baernreuther.mtg.writer.CsvWriter;
import de.baernreuther.mtg.writer.Writer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Parser parser = new CsvParser();
        List<Card> cards = parser.parse(new FileInputStream("test.csv"));
        Writer writer = new CsvWriter();
        writer.write(cards);
    }
}
