import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PokerHandTest {

    @Test
    public void shouldStraightFlush() {
        // Given
        PokerHand hand = new PokerHand("JS TS QS KS AS");

        //Then
        assertTrue(hand.isStraightFlush());
    }

    @Test
    public void shouldFourOfAKind() {
        // Given
        PokerHand hand = new PokerHand("KH KC KD KS 2H");

        //Then
        assertTrue(hand.isFourOfAKind());
    }

    @Test
    public void shouldFullHouse() {
        // Given
        PokerHand hand = new PokerHand("AH AS AD KH KS");

        //Then
        assertTrue(hand.isFullHouse());
    }

    @Test
    public void shouldFlush() {
        // Given
        PokerHand hand = new PokerHand("3S 7S 9S 4S 2S");

        //Then
        assertTrue(hand.isFlush());
    }

    @Test
    public void shouldStraight() {
        // Given
        PokerHand hand = new PokerHand("7S 8H 9D TS JH");

        //Then
        assertTrue(hand.isStraight());
    }

    @Test
    public void shouldThreeOfAKind() {
        // Given
        PokerHand hand = new PokerHand("2C 2H 2D 5S 7H");

        //Then
        assertTrue(hand.isThreeOfAKind());
    }

    @Test
    public void shouldPair() {
        // Given
        PokerHand hand = new PokerHand("AS AC 8H 5D 2C");

        //Then
        assertTrue(hand.isPair());
    }

    @Test
    public void shouldTwoPairs() {
        // Given
        PokerHand hand = new PokerHand("KS KD QH QD JC");

        //Then
        assertTrue(hand.isTwoPairs());
    }

    @Test
    public void sortByRank() {
        // Given
        PokerHand royalFlush = new PokerHand("TS JS QS KS AS");
        PokerHand fourOfAKind = new PokerHand("AH AC AD AS 9C");
        PokerHand fullHouse = new PokerHand("KD KS KC 3D 3H");
        PokerHand flush = new PokerHand("3H 6H TH QH JH");
        PokerHand straight = new PokerHand("9C TS JH QD KD");
        PokerHand threeOfAKind = new PokerHand("2S 2D 2H 4S 7H");
        PokerHand twoPairs = new PokerHand("5D 5C 9H 9S JC");
        PokerHand pair = new PokerHand("8C 8H QS 3D 6S");
        PokerHand highCard = new PokerHand("2C 5D 7H 9S QD");
        List<PokerHand> hands = new ArrayList<>(List.of(pair,royalFlush,fullHouse,fourOfAKind , straight, flush, threeOfAKind, twoPairs, highCard));

        // When
        Collections.sort(hands);

        //Then
        assertEquals(royalFlush, hands.get(8));
        assertEquals(fourOfAKind, hands.get(7));
        assertEquals(fullHouse, hands.get(6));
        assertEquals(flush, hands.get(5));
        assertEquals(straight, hands.get(4));
        assertEquals(threeOfAKind, hands.get(3));
        assertEquals(twoPairs, hands.get(2));
        assertEquals(pair, hands.get(1));
        assertEquals(highCard, hands.get(0));
    }
}

