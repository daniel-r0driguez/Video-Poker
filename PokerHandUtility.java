
import java.util.ArrayList;

/**
 * This is a utility class for the VideoPokerMain class.
 * This class's main purpose is to sort a hand a cards and/or score a hand of cards.
 * Note: The hand of cards must be sorted prior to getting its score.
 */
public class PokerHandUtility
{
    public enum Score {NO_PAIRS, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND,
                        STRAIGHT_FLUSH, ROYAL_FLUSH}

    /**
     * Scores a hand of cards. The hand of cards must be sorted prior to calling this method.
     * @param hand the sorted hand of cards to score
     * @return a Score enum indicating what score the hand was awarded
     */
    public static Score scoreHand(ArrayList<Card> hand)
    {
        if (isRoyalFlush(hand))
        {
            return Score.ROYAL_FLUSH;
        }
        else if (isStraightFlush(hand))
        {
            return Score.STRAIGHT_FLUSH;
        }
        else if (isFourOfAKind(hand))
        {
            return Score.FOUR_OF_A_KIND;
        }
        else if (isFullHouse(hand))
        {
            return Score.FULL_HOUSE;
        }
        else if (isFlush(hand))
        {
            return Score.FLUSH;
        }
        else if (isStraight(hand))
        {
            return Score.STRAIGHT;
        }
        else if (isThreeOfAKind(hand))
        {
            return Score.THREE_OF_A_KIND;
        }
        else if (isTwoPairs(hand))
        {
            return Score.TWO_PAIRS;
        }
        else if (isOnePair(hand))
        {
            return Score.ONE_PAIR;
        }
        else
        {
            return Score.NO_PAIRS;
        }
    }

    /**
     * Checks if the passed in hand of cards is a Royal Flush.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards is a Royal Flush, false otherwise
     */
    private static boolean isRoyalFlush(ArrayList<Card> hand)
    {
        final Card.Suits SUIT_TO_MATCH = hand.get(0).getSuit();
        final Card.Ranks[] neededRanks = {Card.Ranks.TEN, Card.Ranks.JACK, Card.Ranks.QUEEN,
                                          Card.Ranks.KING, Card.Ranks.ACE};

        for (int i = 0; i < neededRanks.length; ++i)
        {
            if (hand.get(i).getRank() != neededRanks[i] || hand.get(i).getSuit() != SUIT_TO_MATCH)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a hand of cards is a Straight Flush.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards is a Straight Flush, false otherwise
     */
    private static boolean isStraightFlush(ArrayList<Card> hand)
    {
        return isStraight(hand) && isFlush(hand);
    }

    /**
     * Checks if a hand of cards is a Four of a Kind.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards is a Four of a Kind, false otherwise
     */
    private static boolean isFourOfAKind(ArrayList<Card> hand)
    {
        return findNumOfAKind(hand, 4);
    }

    /**
     * Checks if a hand of cards is a Full House.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards is a Full House, false otherwise
     */
    private static boolean isFullHouse(ArrayList<Card> hand)
    {
        return isThreeOfAKind(hand) && isTwoPairs(hand);
    }

    /**
     * Checks if a hand of cards is a Flush.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards is a Flush, false otherwise
     */
    private static boolean isFlush(ArrayList<Card> hand)
    {
        final Card.Suits SUIT_TO_MATCH = hand.get(0).getSuit();

        for (Card card : hand)
        {
            if (card.getSuit() != SUIT_TO_MATCH)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a hand of cards is a Straight.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards is a Straight, false otherwise
     */
    private static boolean isStraight(ArrayList<Card> hand)
    {
        Card.Ranks startingRank = hand.get(0).getRank();

        // If the starting rank is a 2, then it can either be [2,3,4,5,6] or [2,3,4,5,Ace]...
        // ...although the Ace usually precedes the 2.
        // Either way, both are Straights.
        if (startingRank == Card.Ranks.TWO)
        {
            for (int i = 0; i < hand.size() - 1; ++i)
            {
                if (hand.get(i).getRank().ordinal() != Card.Ranks.TWO.ordinal() + i)
                {
                    return false;
                }
            }
            return hand.get(hand.size() - 1).getRank() == Card.Ranks.ACE || hand.get(hand.size() - 1).getRank() == Card.Ranks.SIX;
        }

        // Otherwise, simply check if the cards are in consecutive, ascending order.
        for (int i = 0; i < hand.size(); ++i)
        {
            if (hand.get(i).getRank().ordinal() != startingRank.ordinal() + i)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a hand of cards is Three of a kind.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards is Three of a kind, false otherwise
     */
    private static boolean isThreeOfAKind(ArrayList<Card> hand)
    {
        return findNumOfAKind(hand, 3);
    }

    /**
     * Checks if a hand of cards contains two pairs.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards contains two pairs, false otherwise
     */
    private static boolean isTwoPairs(ArrayList<Card> hand)
    {
        return findNumOfPairs(hand, 2);
    }

    /**
     * Checks if a hand of cards contains at least one pair.
     * @param hand the sorted hand of cards to check
     * @return true if the hand of cards contains at least one pair, false otherwise
     */
    private static boolean isOnePair(ArrayList<Card> hand)
    {
        return findNumOfPairs(hand, 1);
    }

    /**
     * Helper function for isTwoPairs() and isOnePair() methods.
     * Checks if a hand of cards contains a desired amount of pairs.
     * @param hand the sorted hand of cards to check
     * @param desiredNumOfPairs the amount of desired pairs
     * @return true if the hand of cards contains a desired amount of pairs, false otherwise
     */
    private static boolean findNumOfPairs(ArrayList<Card> hand, int desiredNumOfPairs)
    {
        int numOfPairs = 0;
        int index = 0;
        while (index < hand.size() - 1)
        {
            if (hand.get(index).getRank() == hand.get(index + 1).getRank())
            {
                ++numOfPairs;
                index += 2;
            }
            else
            {
                ++index;
            }
        }
        return numOfPairs == desiredNumOfPairs;
    }

    /**
     * Helper function for isThreeOfAKind() and isFourOfAKind() methods.
     * Checks if a hand of cards contains a desired amount of cards of the same rank.
     * @param hand the sorted hand of cards to check
     * @param desiredNumOfAKind the desired amount cards of the same rank
     * @return true if the hand of cards contains the desired amount of cards of the same rank, false otherwise
     */
    private static boolean findNumOfAKind(ArrayList<Card> hand, int desiredNumOfAKind)
    {
        int curIndex = 0;
        while (curIndex < hand.size())
        {
            Card.Ranks curRankToMatch = hand.get(curIndex).getRank();
            int count = 0;
            for (Card card : hand)
            {
                if (card.getRank() == curRankToMatch)
                {
                    ++count;
                }
            }
            if (count >= desiredNumOfAKind)
            {
                return true;
            }
            ++curIndex;
        }
        return false;
    }

    /**
     * Invokes a sort method to sort an ArrayList of Cards.
     * @param hand the hand to sort in ascending order.
     */
    public static void sortHand(ArrayList<Card> hand)
    {
        quickSort(hand, 0, hand.size() - 1);
    }

    /**
     * Sorts an ArrayList of base type <Card> in ascending order (using the quick sort algorithm),
     * @param hand the hand of cards to sort
     * @param left the left boundary index
     * @param right the right boundary index
     */
    private static void quickSort(ArrayList<Card> hand, int left, int right)
    {
        if (left < right)
        {
            int partitionIndex = partition(hand, left, right);
            quickSort(hand, left, partitionIndex - 1);
            quickSort(hand, partitionIndex + 1, right);
        }
    }

    /**
     * Helper function for sortHand() method.
     * @param hand the hand of cards to partition
     * @param left the left boundary index
     * @param right the right boundary index
     * @return the partition index of the current element
     */
    private static int partition(ArrayList<Card> hand, int left, int right)
    {
        Card.Ranks pivotValue = hand.get(right).getRank();
        int pivotIndex = left;

        for (int i = left; i < right; ++i)
        {
            if (hand.get(i).getRank().ordinal() < pivotValue.ordinal())
            {
                swap(hand, i, pivotIndex);
                ++pivotIndex;
            }
        }
        swap(hand, pivotIndex, right);
        return pivotIndex;
    }

    /**
     * Swaps two elements in an ArrayList of base type <Card>.
     * @param hand the hand of cards to swap two elements from
     * @param index1 the first element
     * @param index2 the second element
     */
    private static void swap(ArrayList<Card> hand, int index1, int index2)
    {
        Card temp = hand.get(index1);
        hand.set(index1, hand.get(index2));
        hand.set(index2, temp);
    }
}