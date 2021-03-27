package de.baernreuther.mtg.writer;

import de.baernreuther.mtg.scryfall.Card;

import java.util.List;

public interface Writer {

    /**
     * Writes a list of Cards to an arbitrary source
     * @param cardList List of Cards to Save.
     */
    void write(List<Card> cardList);

}
