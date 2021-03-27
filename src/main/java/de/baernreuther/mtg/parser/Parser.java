package de.baernreuther.mtg.parser;

import de.baernreuther.mtg.scryfall.Card;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface Parser {

    /**
     * Parses the given Inputstream to a List of Cards from Scryfall
     * @param inputStream Inputstream of Data
     * @return List of Cards with relevant Information from Scryfall-API
     */
    List<Card> parse(InputStream inputStream) throws IOException;
}
