import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This program uses and depends on the Card and CardDeck class.
 * This program simulates rounds of poker until the user decides to stop playing.
 */
public class VideoPokerMain
{
    private static final int[] PAY_OUTS = {0, 1, 2, 3, 4, 5, 6, 25, 50, 250};
    private static final int CARDS_PER_HAND = 5;

    public static void main(String[] args)
    {
        System.out.print("Video Poker Program\n===============================");
        // Create an array which will remember all the pulls of the Poker game.
        final int[] PULLS = new int[10];
        // Give user 100 tokens to start with.
        int tokens = 100;
        // Create a new CardDeck object.
        CardDeck deck1 = new CardDeck();
        // Shuffle the card deck.
        deck1.shuffleDeck();
        String userResponse;
        do
        {
            System.out.println();
            // Get a hand of cards from the deck.
            ArrayList< Card> playerHand = deck1.getNewHand(CARDS_PER_HAND);
            // Sort the hand of cards using the PokerHandUtility utility class.
            PokerHandUtility.sortHand(playerHand);
            // Print the hand of cards to the screen.
            printHand(playerHand);
            System.out.println();
            // Prompt user if they want to reject any cards.
            rejectCards(playerHand, deck1);
            System.out.println();
            // Token must be paid prior to scoring user's hand of cards.
            --tokens;
            // Score the hand using the PokerHandUtility utility class.
            PokerHandUtility.Score playerScore =  PokerHandUtility.scoreHand(playerHand);
            // Print the score of the hand.
            printScoreEnum(playerScore);
            // Award the payout.
            tokens += PAY_OUTS[playerScore.ordinal()];
            // Print user's token count.
            System.out.printf("You have %d tokens\n", tokens);
            // Update the pulls array.
            ++PULLS[playerScore.ordinal()];
            // Prompt user to play again.
            System.out.print("Shuffle Again? (Y/N): ");
            userResponse = getLine();
        } while (userResponse.equalsIgnoreCase("Y"));
        // Print out the all the pulls of this poker game.
        System.out.println("\nPulls this game\n================================");
        printScoreResults(PULLS);
        // Print the user's final payout after the game has ended.
        printPayout(tokens);
    }

    /**
     * Returns a String representing the user's entire line of input.
     * @return a String representing the user's entire line of input
     */
    private static String getLine()
    {
        Scanner in = new Scanner(System.in);
        return in.nextLine().strip();
    }

    /**
     * Prints out a player's hand of cards.
     * @param hand the hand of cards to print out
     */
    public static void printHand(ArrayList< Card> hand)
    {
        System.out.print("Your hand contains:\n");
        int count = 1;
        for ( Card card : hand)
        {
            System.out.print(count++ + ": " + card.getRank() + " of " + card.getSuit() + "\n");
        }
    }

    /**
     * Prints the Score enum in a more normal string fashion.
     * @param score the Score enum that the hand of cards was awarded
     */
    public static void printScoreEnum( PokerHandUtility.Score score)
    {
        System.out.print("Hand Score: ");
        String scoreStr;
        switch (score)
        {
            case ONE_PAIR -> scoreStr = "One pair";
            case TWO_PAIRS -> scoreStr = "Two pairs";
            case THREE_OF_A_KIND -> scoreStr = "Three of a kind";
            case STRAIGHT -> scoreStr = "Straight";
            case FLUSH -> scoreStr = "Flush";
            case FULL_HOUSE -> scoreStr = "Full house";
            case FOUR_OF_A_KIND -> scoreStr = "Four of a kind!";
            case STRAIGHT_FLUSH -> scoreStr = "Straight Flush!";
            case ROYAL_FLUSH -> scoreStr = "Royal Flush!";
            default -> scoreStr = "No pairs";
        }
        System.out.println(scoreStr);
    }

    /**
     * Prints the collection of pulls achieved during the poker game.
     */
    private static void printScoreResults(int[] pulls)
    {
        String[] scoreStrings = {"No Pairs", "One Pair", "Two Pairs", "Three of a Kind", "Straight", "Flush",
                "Full House", "Four of a Kind", "Straight Flush", "Royal Flush"};
        for (int i = 0; i < scoreStrings.length; ++i)
        {
            System.out.printf("%s: %d\n", scoreStrings[i], pulls[i]);
        }
    }

    /**
     * Prints out the player's payout after the game has ended.
     * @param tokens the player's amount of tokens
     */
    private static void printPayout(int tokens)
    {
        System.out.printf("\nYour payout is: %d tokens\n", tokens);
    }

    /**
     * Prompts the user if they want to modify their hand of cards.
     * The user can choose to keep, replace, or get a new hand of cards.
     * @param hand the hand of cards to possibly reject from
     * @param deck the deck of cards to get the replacement cards from
     */
    private static void rejectCards(ArrayList<Card> hand, CardDeck deck)
    {
        // Exit case. There is not a reasonable amount of cards in the deck in order...
        // ...to replace old cards or provide a new hand of cards for the user.
        if (deck.getCardDeckSize() < CARDS_PER_HAND)
        {
            System.out.print("""
                    !!! You cannot reject any cards at the moment !!!
                    The Card Deck will be re-shuffled next round.
                    """);
            return;
        }

        // Print out the expected inputs.
        System.out.print("""
                Enter "Keep" to keep your hand of cards.
                Enter "New Hand" to receive a new hand of cards.
                Enter a sequence of numbers which represent which cards to replace.
                (Example: "1 2 4" would replace the first, second, and fourth card of the list above).
                """);
        System.out.print("Input: ");
        String userResponse = getLine();

        // If the user wants to keep their hand, simply return.
        if (userResponse.equalsIgnoreCase("Keep"))
        {
            return;
        }
        // If the user wants to get a new hand of cards, give them a new hand of cards.
        if (userResponse.equalsIgnoreCase("New Hand"))
        {
            hand.clear();
            hand.addAll(deck.getNewHand(CARDS_PER_HAND));
        }
        // Otherwise the user most likely wants to replace some cards.
        else
        {
            // ArrayList which holds which the Card indexes to remove.
            ArrayList<Integer> cardsToReplace = new ArrayList<>();
            // Array which keeps track of which indexes have already been entered.
            boolean[] entered = new boolean[5];

            // Scan the user's input for integers (digits).
            for (int i = 0; i < userResponse.length(); ++i)
            {
                // If a digit is found...
                if (Character.isDigit(userResponse.charAt(i)))
                {
                    int digit = Character.digit(userResponse.charAt(i), 10) - 1;
                    // ...and it is in range has not been entered already...
                    if (digit >= 0 && digit < 5 && !entered[digit])
                    {
                        // ...add it to the cardsToReplace ArrayList.
                        entered[digit] = true;
                        cardsToReplace.add(digit);
                    }
                }
            }
            // If no digits were found, then the user must have entered an unexpected String.
            // Exit this method call.
            if (cardsToReplace.isEmpty())
            {
                return;
            }
            // Sort the ArrayList in reverse order.
            // This way Card objects from the @param hand ArrayList are removed from the back...
            // ...minimizing out-of-place removals for the next remove() call.
            cardsToReplace.sort(Collections.reverseOrder());

            // Keep track of how many cards to give back.
            int numCardsToReplace = cardsToReplace.size();

            // Remove the cards.
            for (int index : cardsToReplace)
            {
                hand.remove(index);
            }

            // Give back new cards.
            for (int i = 0; i < numCardsToReplace; ++i)
            {
                hand.add(deck.getTopCard());
            }
        }
        // Sort the hand of cards.
        PokerHandUtility.sortHand(hand);
        // Print the hand of cards.
        printHand(hand);
        System.out.println();
    }
}