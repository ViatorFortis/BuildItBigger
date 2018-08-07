package com.viatorfortis.givemejokelib;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Jokes {
    private final static List<String> mJokes = Arrays.asList(
            "A man was very proud of his guard dog, he would leave it to roam free in the garden to sow the world his house was guarded. One day a woman knocked at his door. \"Is that your big dog outside?\" Wondering how she had got past him he said \"Yes why?\" She said I'm sorry but my dog just killed him!\" \"What??\" Roared the man \"What kind of dog have you got??\" \"A Peke\" Replied the woman. \"A Peke??? how could that little thing kill my big fine guard dog?\" \"I think it got stuck in his throat!\" Replied the woman.",

            "Do you want to speak to the boss? OR Someone who really knows what's happening?",

            "Q. imagine you're in a haunted house with monsters and ghosts surrounding you....how do you survive? \n"
                    + "\n" + "A. stop imaging!",

            "Borrow money from a pessimist, they don't expect it back!",

            "What do toilet seats, anniversaries and birthdays have in common? Men miss all of them!");

    public static String giveMeJoke() {
        return mJokes.get(new Random().nextInt(mJokes.size() ) );
    }
}
