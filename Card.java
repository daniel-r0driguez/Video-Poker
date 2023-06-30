/**
 * Simple Card class which holds two values. Its rank and its suit type.
 * It also contains enums which represent different Ranks and Suits for the other classes to use.
 */
public class Card
{
    public enum Ranks {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}
    public enum Suits {HEARTS, DIAMONDS, SPADES, CLUBS}
    // Instance variables
    private final Ranks rank;
    private final Suits suit;

    /**
     * Constructs a Card object with a set rank (A - K) and set suit (Hearts, Spades, Clubs, Diamonds)
     * @param rank the desired rank
     * @param suit the desired suit
     */
    public Card(Ranks rank, Suits suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * @return returns the Card object's rank.
     */
    public Ranks getRank()
    {
        return this.rank;
    }

    /**
     * @return returns the Card object's suit
     */
    public Suits getSuit()
    {
        return this.suit;
    }
}