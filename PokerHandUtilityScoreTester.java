import java.util.ArrayList;

/**
 * This class simply tests the PokerHandUtility's scoring methods (isRoyalFlush(), isStraight(), isFullHouse(), etc.)
 * It is not necessary to run the VideoPokerMain.java program, however, it is necessary in order to test the scoring methods.
 * A hand of cards is manually created in order to test if each scoring method is computed correctly.
 * This test class uses public methods from the VideoPokerMain class and PokerHandUtility class.
 */
public class PokerHandUtilityScoreTester
{
    public static void main(String[] args)
    {
        // Create an ArrayList of Cards
        ArrayList<Card> hand = new ArrayList<>(5);

        // Test case where the hand has no pairs.
        hand.add(new Card(Card.Ranks.NINE, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.ACE, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.TEN, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.QUEEN, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.EIGHT, Card.Suits.SPADES));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: No Pairs\n\n");

        // Test case where the hand has at least one pair.
        hand.clear();
        hand.add(new Card(Card.Ranks.NINE, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.ACE, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.NINE, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.JACK, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.EIGHT, Card.Suits.SPADES));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: One Pair\n\n");

        // Test case where the hand has two pairs.
        hand.clear();
        hand.add(new Card(Card.Ranks.NINE, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.JACK, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.NINE, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.JACK, Card.Suits.SPADES));
        hand.add(new Card(Card.Ranks.EIGHT, Card.Suits.SPADES));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: Two Pairs\n\n");

        // Test case where the hand has three cards of the same rank.
        hand.clear();
        hand.add(new Card(Card.Ranks.JACK, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.JACK, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.NINE, Card.Suits.SPADES));
        hand.add(new Card(Card.Ranks.JACK, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.EIGHT, Card.Suits.DIAMONDS));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: Three of a Kind\n\n");

        // Test case where the hand has five cards with consecutive values.
        hand.clear();
        hand.add(new Card(Card.Ranks.JACK, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.KING, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.ACE, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.TEN, Card.Suits.SPADES));
        hand.add(new Card(Card.Ranks.QUEEN, Card.Suits.SPADES));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: Straight\n\n");

        // Test case where the hand has five cards of the same suit.
        hand.clear();
        hand.add(new Card(Card.Ranks.FOUR, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.ACE, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.TEN, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.SIX, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.TWO, Card.Suits.CLUBS));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: Flush\n\n");

        // Test case where the hand has three of a kind and a pair.
        hand.clear();
        hand.add(new Card(Card.Ranks.KING, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.KING, Card.Suits.CLUBS));
        hand.add(new Card(Card.Ranks.TWO, Card.Suits.SPADES));
        hand.add(new Card(Card.Ranks.KING, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.TWO, Card.Suits.SPADES));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: Full House\n\n");

        // Test case where the hand has four cards with the same rank.
        hand.clear();
        hand.add(new Card(Card.Ranks.THREE, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.THREE, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.QUEEN, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.THREE, Card.Suits.HEARTS));
        hand.add(new Card(Card.Ranks.THREE, Card.Suits.HEARTS));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: Four of a kind!\n\n");

        // Test case where the hand is both a straight and flush.
        hand.clear();
        hand.add(new Card(Card.Ranks.ACE, Card.Suits.SPADES));
        hand.add(new Card(Card.Ranks.FOUR, Card.Suits.SPADES));
        hand.add(new Card(Card.Ranks.THREE, Card.Suits.SPADES));
        hand.add(new Card(Card.Ranks.TWO, Card.Suits.SPADES));
        hand.add(new Card(Card.Ranks.FIVE, Card.Suits.SPADES));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: Straight Flush!\n\n");

        // Test case where the hand contains a 10, Jack, Queen, King, and Ace...
        // ...while all belonging to the same Suit.
        hand.clear();
        hand.add(new Card(Card.Ranks.JACK, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.KING, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.ACE, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.TEN, Card.Suits.DIAMONDS));
        hand.add(new Card(Card.Ranks.QUEEN, Card.Suits.DIAMONDS));
        PokerHandUtility.sortHand(hand);
        VideoPokerMain.printHand(hand);
        VideoPokerMain.printScoreEnum(PokerHandUtility.scoreHand(hand));
        System.out.print("Expected: Royal Flush!\n\n");
    }
}