import java.util.*;

public class PokerHand implements Comparable<PokerHand> {

    public static final int HIGH_CARD = 0;
    public static final int PAIR = 1;
    public static final int TWO_PAIRS = 2;
    public static final int THREE_OF_A_KIND = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int FOUR_OF_A_KIND = 7;
    public static final int STRAIGHT_FLUSH = 8;
    private final Card[] cards;

    public PokerHand(String handString) {
        String[] cardStrings = handString.split(" ");
        this.cards = new Card[5];
        for (int i = 0; i < 5; i++) {
            String cardString = cardStrings[i];
            char rankChar = cardString.charAt(0);
            char suitChar = cardString.charAt(1);
            Rank rank = Rank.fromSymbol(rankChar);
            Suit suit = Suit.fromSymbol(suitChar);
            Card card = new Card(rank, suit);
            this.cards[i] = card;
        }
    }

    public boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    public boolean isFourOfAKind() {
        int[] ranks = getRanks(cards);
        for (int rank : ranks) {
            if (countRank(rank, ranks) == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullHouse() {
        int[] ranks = getRanks(cards);
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;
        for (int rank : ranks) {
            int count = countRank(rank, ranks);
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasPair = true;
            }
        }
        return hasThreeOfAKind && hasPair;
    }

    public boolean isFlush() {
        String[] suits = getSuits(cards);
        for (int i = 1; i < suits.length; i++) {
            if (!Objects.equals(suits[i], suits[0])) {
                return false;
            }
        }
        return true;
    }

    public boolean isStraight() {
        int[] ranks = getRanks(cards);
        Arrays.sort(ranks);
        for (int i = 1; i < ranks.length; i++) {
            if (ranks[i] != ranks[i - 1] + 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isThreeOfAKind() {
        int[] ranks = getRanks(cards);
        for (int rank : ranks) {
            if (countRank(rank, ranks) == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean isPair() {
        int[] ranks = getRanks(cards);
        for (int rank : ranks) {
            if (countRank(rank, ranks) == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPairs() {
        int[] ranks = getRanks(cards);
        int countPairs = 0;
        for (int rank : ranks) {
            if (countRank(rank, ranks) == 2) {
                countPairs++;
            }
        }
        return countPairs == 4;
    }

    private int countRank(int rank, int[] ranks) {
        int count = 0;
        for (int r : ranks) {
            if (r == rank) {
                count++;
            }
        }
        return count;
    }

    private int[] getRanks(Card[] cards) {
        int[] ranks = new int[cards.length];
        for (int i = 0; i < cards.length; i++) {
            ranks[i] = cards[i].getRank().getValue();
        }
        return ranks;
    }

    private String[] getSuits(Card[] cards) {
        String[] suits = new String[cards.length];
        for (int i = 0; i < cards.length; i++) {
            suits[i] = cards[i].getSuit().name();
        }
        return suits;
    }

    public int getRank() {
        if (isStraightFlush()) {
            return STRAIGHT_FLUSH;
        } else if (isFourOfAKind()) {
            return FOUR_OF_A_KIND;
        } else if (isFullHouse()) {
            return FULL_HOUSE;
        } else if (isFlush()) {
            return FLUSH;
        } else if (isStraight()) {
            return STRAIGHT;
        } else if (isThreeOfAKind()) {
            return THREE_OF_A_KIND;
        } else if (isTwoPairs()) {
            return TWO_PAIRS;
        } else if (isPair()) {
            return PAIR;
        } else {
            return HIGH_CARD;
        }
    }

    public int compareTo(PokerHand other) {
        int thisRank = this.getRank();
        int otherRank = other.getRank();
        if (thisRank != otherRank) {
            return Integer.compare(thisRank, otherRank);
        } else {
            List<Card> thisCards = new ArrayList<>(List.of(this.cards));
            List<Card> otherCards = new ArrayList<>(List.of(other.cards));
            thisCards.sort(Comparator.comparingInt(Card::getRankValue).reversed());
            otherCards.sort(Comparator.comparingInt(Card::getRankValue).reversed());
            for (int i = 0; i < thisCards.size(); i++) {
                Card thisCard = thisCards.get(i);
                Card otherCard = otherCards.get(i);
                int diff = Integer.compare(thisCard.getRankValue(), otherCard.getRankValue());
                if (diff != 0) {
                    return diff;
                }
            }
            return 0;
        }
    }

}
