package com.company;
import java.util.*;
public class DotComBust {
    private  GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void SetUpGame() {
        //create sites
        DotCom one = new DotCom();
        one.setName("google.com");

        DotCom two = new DotCom();
        two.setName("pkgn.ru");

        DotCom three = new DotCom();
        three.setName("amazon.com");

        DotCom four  = new DotCom();
        four.setName("pets.com");

        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);
        dotComsList.add(four);

        System.out.println("Ваша цель потопить 4 сайта размерами от 3 до 6 клеток \ngoogle.com, pkgn.ru,  pets.com, amazon.com \nпопытайтесь сделать это за минимальное количество ходов");
        for (DotCom dotComToSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(getRandomNumber(3,7));

            dotComToSet.setLocationCells(newLocation);
        }
    }

    private int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private void startPlaying(){
        while (!dotComsList.isEmpty()){
            String userGuess  = helper.getUserInput("сделайте ход");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void checkUserGuess(String userGuess){
        numOfGuesses++;
        String result = "Мимо";
        for ( DotCom dotComToTest : dotComsList){
            result = dotComToTest.checkYourself(userGuess);

            if(result.equals("Попал")){
                break;
            }
            if (result.equals("Потопил")){
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }
    private void finishGame(){
        System.out.println("Все сайты ушли ко дну! ваши акции теперь ничего не стоят.");
        if(numOfGuesses <= 28) {
            System.out.println("это заняло у вас всего " + numOfGuesses + " попыток. \nВы успели выбраться до того, как ваши вложения утонули. ");
        } else {
            System.out.println("Это заняло у вас довольно много времени целых " + numOfGuesses +  " попыток. \nРыбки водять хороводы вокруг ваших вложений.");
        }
    }

    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.SetUpGame();
        game.startPlaying();
    }
}
