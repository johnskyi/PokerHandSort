
public class Card {
    private final Rank rank; // номинал карты
    private final Suit suit; // масть карты

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank.name() + suit.name();
    }
}
