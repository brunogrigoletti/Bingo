package game;
import java.util.ArrayList;
import java.util.Random;

public class Bingo{
    Random r;
    ArrayList<Integer> cardNumbers;
    ArrayList<Integer> roundNumbers;

    public Bingo(){
        r = new Random();
        cardNumbers = new ArrayList<>();
        roundNumbers = new ArrayList<>();
    }

    public int newCardNumber(){
        int cardNumber = r.nextInt(51);
        if (!isInCard(cardNumber)){
            cardNumbers.add(cardNumber);
            return cardNumber;
        }
        return newCardNumber();
    }

    public int newRoundNumber(){
        int roundNumber = r.nextInt(51);
        if (!wasPlayed(roundNumber)){
            roundNumbers.add(roundNumber);
            return roundNumber;
        }
        return newRoundNumber();
    }

    public boolean isInCard(int number){
        if (cardNumbers.contains(number))
            return true;
        return false;
    }

    public boolean wasPlayed(int number){
        if (roundNumbers.contains(number))
            return true;
        return false;
    }
}