import java.util.ArrayList;
import java.util.Random;

/**
 * This class depends on the Card class, as without cards, there can be no Card Deck.
 * CardDeck's main purpose is to build a deck of cards of all 13 Ranks of each of the 4 Suits (52 total cards each deck)
 * This class can shuffle its card deck, return a hand of cards, return a single card from the top of its deck, or...
 * ...reset (un-shuffle) the deck entirely to get it ready for the next game.
 */
public class CardDeck
{
    private static final Random RANDOMIZER = new Random(System.currentTimeMillis());
    private final ArrayList<Card> CARD_DECK;

    /**
     * Constructs a card deck with all four suits. It contains a total of 52 cards.
     */
    public CardDeck()
    {
        this.CARD_DECK = new ArrayList<>(52);
        loadHearts();
        loadDiamonds();
        loadSpades();
        loadClubs();
    }

    /**
     * @return returns the current card deck
     */
    public ArrayList<Card> getCardDeck()
    {
        return new ArrayList<>(this.CARD_DECK);
    }

    /**
     * @return returns the size of the card deck
     */
    public int getCardDeckSize()
    {
        return this.CARD_DECK.size();
    }

    /**
     * Pops a card from the top of the card deck, essentially decrementing the card deck size by -1.
     * @return returns a Card object which is located at the top of the card deck.
     */
    public Card getTopCard()
    {
        if (this.getCardDeckSize() > 0)
        {
            return this.CARD_DECK.remove(this.CARD_DECK.size() - 1);
        }
        return null;
    }

    /**
     * Returns a new hand of cards.
     * If there are not enough cards in the deck to create a new hand of cards, then the deck will...
     * ...automatically reset and shuffle its cards, and then return the new hand of cards.
     * @param handSize the desired size amount of cards in the hand
     * @return a new hand of cards of a desired size amount
     */
    public ArrayList<Card> getNewHand(int handSize)
    {
        if (this.getCardDeckSize() < handSize)
        {
            this.resetDeck();
            this.shuffleDeck();
        }
        return getCards(handSize);
    }

    /**
     * Randomly shuffles the CardDeck's deck.
     */
    public void shuffleDeck()
    {
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        // Remove each card randomly and put into another deck.
        while (this.CARD_DECK.size() > 0)
        {
            shuffledDeck.add(this.CARD_DECK.remove(CardDeck.RANDOMIZER.nextInt(this.CARD_DECK.size())));
        }

        // Remove each card randomly from the other deck and put back into the original.
        while (shuffledDeck.size() > 0)
        {
            this.CARD_DECK.add(shuffledDeck.remove(CardDeck.RANDOMIZER.nextInt(shuffledDeck.size())));
        }
    }

    /**
     * Resets the card to its default, non-shuffled form.
     * Use the shuffleDeck() method after invoking this method in...
     * ...order to get the deck ready for the next game.
     */
    public void resetDeck()
    {
        this.CARD_DECK.clear();
        loadHearts();
        loadDiamonds();
        loadSpades();
        loadClubs();
    }

    /**
     * Helper function for getNewHand(). Returns a hand containing n amount of cards from the CardDeck object's deck.
     * @param amount the desired amount of cards to be grabbed
     * @return an array list containing the desired amount of cards
     */
    private ArrayList<Card> getCards(int amount)
    {
        ArrayList<Card> hand = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i)
        {
            hand.add(getTopCard());
        }
        return hand;
    }


    /**
     * Loads all the ranks of the Hearts suit.
     */
    private void loadHearts()
    {
        for (Card.Ranks rank : Card.Ranks.values())
        {
            this.CARD_DECK.add(new Card(rank, Card.Suits.HEARTS));
        }
    }

    /**
     * Loads all the ranks of the Diamonds suit.
     */
    private void loadDiamonds()
    {
        for (Card.Ranks rank : Card.Ranks.values())
        {
            CARD_DECK.add(new Card(rank, Card.Suits.DIAMONDS));
        }
    }

    /**
     * Loads all the ranks of the Spades suit.
     */
    private void loadSpades()
    {
        for (Card.Ranks rank : Card.Ranks.values())
        {
            CARD_DECK.add(new Card(rank, Card.Suits.SPADES));
        }
    }

    /**
     * Loads all the ranks of the Clubs suit.
     */
    private void loadClubs()
    {
        for (Card.Ranks rank : Card.Ranks.values())
        {
            CARD_DECK.add(new Card(rank, Card.Suits.CLUBS));
        }
    }
}