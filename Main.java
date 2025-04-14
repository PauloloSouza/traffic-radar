package org.example;
import java.util.*;
import java.lang.String;

public class Main {
    public static long start;
    public static int limit = 60;
    public static int radarDistance = 360;
    public static double metersPerSec = limit/3.6;
    public static double timeToGetFined = radarDistance / metersPerSec;

    public static void main(String[] args) {

        boolean running = true;
        double speed;
        double acceptable = (limit * 0.1 + (limit));

        System.out.println(acceptable);

        while (running) {
            //Check if want to do a test or not.
            System.out.println("Want to do a speed test?");
            System.out.println("0 - Start.");
            System.out.println("1 - End Program.");

            try {
                //Get input to run or not.
                Scanner input = new Scanner(System.in);
                int starter = input.nextInt();

                if (starter == 0 || starter == 1) {
                    if (Objects.equals(starter, 0)) {
                        for (int i = 2; i > 1; i++) { //Loop to force user enter correct input
                            try { // try-catch block to didn't break code
                                System.out.println("Set your speed:");
                                Scanner input1 = new Scanner(System.in);
                                speed = input1.nextDouble();
                                if (speed <= 0) { // Another loop to force correct input
                                    System.out.println("Please chose valid speed, (positive numbers and not 0).");
                                }
                                if (isDouble(speed) && speed > 0) {
                                    System.out.println(speed); // Displaying chosen initial speed
                                    i = 0;
                                }
                            } catch (Exception e) { //Catch block for error, and loop again forcing correct input
                                System.out.println("You entered a character. Please enter your speed with numbers.");
                            }
                        }
                        for (int i = 2; i > 1; i++) {
                            try {
                                System.out.println("You see a radar. Set your speed passing through it:");
                                Scanner input2 = new Scanner(System.in);
                                speed = input2.nextDouble();
                                start = System.nanoTime(); //Start count seconds until the next input to calculate the time to next radar
                                if (speed <= 0) {
                                    System.out.println("Please chose valid speed, (positive numbers and not 0).");
                                }
                                if (isDouble(speed) && speed > 0) { //Answers according to chosen speed
                                    System.out.println(speed);
                                    i = 0;
                                    if (speed <= limit) {
                                        System.out.println("Ok, You didn't get fined, your speed was below the limit.");
                                    } else if (speed > limit && speed <= acceptable) {
                                        System.out.println("You're above limits, but still acceptable, be careful and reduce speed.");
                                    } else if (speed > acceptable) {
                                        System.out.println("You're above acceptable limit and you get fined. You have to decrease your speed.");
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("You entered a character. Please enter your speed with numbers.");
                            }
                        }
                        for (int i = 2; i > 1; i++) {
                            try {
                                System.out.println("You see another radar about " +radarDistance+ "m distant from last one. Set your speed passing through it:");
                                Scanner input3 = new Scanner(System.in);
                                speed = input3.nextDouble();
                                long finish = System.nanoTime(); // End of time count
                                /*System.out.println("Start = " + start / 1000000000.0);
                                System.out.println("Finish =" + finish / 1000000000.0);*/
                                // Calculate time between 2 inputs (elapsed time)
                                long timeElapsed = ((finish - start));
                                double timeElapsedToSeconds = timeElapsed / 1000000000.0;

                                if (speed <= 0) { // Loop to force correct input, similar above
                                    System.out.println("Please chose valid speed, (positive numbers and not 0).");
                                }

                                if (isDouble(speed) && speed > 0) { //Answers according to chosen speed and time waited;
                                    System.out.println("You toke " + Math.round(timeElapsedToSeconds) + " seconds to pass trough next radar.");
                                    i = 0;
                                    if (speed > acceptable || timeToGetFined > timeElapsedToSeconds) {
                                        System.out.println("You get fined. You're above acceptable limit or didn't respect speed limit when there were no radars. You have to decrease your speed.");
                                        System.out.println("Your speed was " + speed + ".");
                                        System.out.println("You had " + Math.round(timeToGetFined) + " seconds to pass trough radar.");
                                        System.out.println("You passed trough radar with " + timeElapsedToSeconds);
                                    }
                                    if ((speed <= limit) && (timeToGetFined <= timeElapsedToSeconds)) {
                                        System.out.println("You didn't get fined, your speed was below the limit and you respect speed limit.");
                                        System.out.println("Your speed was " + speed + ".");
                                        System.out.println("You had " + Math.round(timeToGetFined) + " seconds to pass trough radar.");
                                        System.out.println("You passed trough radar with " + timeElapsedToSeconds);
                                    }
                                    if (((speed > limit) &&  (timeToGetFined <= timeElapsedToSeconds)) & (speed <= acceptable)) {
                                        System.out.println("You're above limits, but still acceptable, be careful and reduce speed.");
                                        System.out.println("Your speed was " + speed + ".");
                                        System.out.println("You had " + Math.round(timeToGetFined) + " seconds to pass trough radar.");
                                        System.out.println("You passed trough radar with " + timeElapsedToSeconds);
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("You entered a character. Please enter your speed with numbers.");
                            }
                        }
                    } else {
                        running = false;
                    }
                }
                if (starter < 0 || starter > 1) {
                    System.out.println("Please chose between 0 or 1.");
                }
            } catch (Exception e) {
                System.out.println("You entered a character. Please chose between 0 or 1.");
            }
            if (!running) { // Finish speed test
                System.out.println("Your test ended.");
            }
        }
    }

    private static boolean isDouble(double input) {
        try {
            Double.parseDouble(String.valueOf(input));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}