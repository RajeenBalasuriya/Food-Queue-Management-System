import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {


        //Initialize of scanner object to get inputs
        Scanner scan = new Scanner(System.in);

        //Keeping menu options in array to iterate using a for loop in the displayMenu method
        String[] menuOptions = {

                "100 or VFQ: View all Queues",
                "101 or VEQ: View all Empty Queues.",
                "102 or ACQ: Add customer to a Queue.",
                "103 or RCQ: Remove a customer from a Queue. (From a specific location)",
                "104 or PCQ: Remove a served customer.",
                "105 or VCS: View Customers Sorted in alphabetical order (Do not use library sort routine)",
                "106 or SPD: Store Program Data into file.",
                "107 or LPD: Load Program Data from file.",
                "108 or STK: View Remaining burgers Stock.",
                "109 or AFS: Add burgers to Stock.",
                "999 or EXT: Exit the Program."

        };


        //Multi-dimensional array is used to handle queue and cashier data
        String[][] queuePlan = new String[3][];
        queuePlan[0] = new String[2];
        queuePlan[1] = new String[3];
        queuePlan[2] = new String[5];

        //array will help to reuse details get from VEQ function
        int[] emptyQueues = new int[3];


        String choice = displayMenu(menuOptions, scan);//getting the user choice

        while (!choice.equals("EXT") && !choice.equals("999")) {

            switch (choice) {
                case "VFQ":
                case "100":
                    //1 option
                    printingQue(queuePlan);
                    break;


                case "VEQ":
                case "101":
                    //2 option
                    viewAllEmptyQueues(queuePlan, emptyQueues);
                    break;

                case "ACQ":
                case "102":
                    //3 option
                    viewAllEmptyQueues(queuePlan, emptyQueues);//this method is called here to update emptyQueue array
                    addCustomerToQueue(emptyQueues, scan, queuePlan);//this will add the customer to queue
                    break;


                case "RCQ":
                case "103":
                    //4 option

                case "PCQ":
                case "104":
                    //5 option

                case "VCS":
                case "105":
                    //6 option

                case "SPD":
                case "106":
                    //7 option

                case "LPD":
                case "107":
                    //8 option

                case "STK":
                case "108":
                    //9 option

                case "AFS":
                case "109":
                    //10 option

                default: {
                    System.out.println("Invalid option");
                    break;
                }
            }

            choice = displayMenu(menuOptions, scan);//getting the user choice again
        }


    }

    private static String displayMenu(String[] menuOptions, Scanner scan) {


        System.out.println("-----Foodies Fave Queue Management System.-------");
        System.out.println();


        System.out.println("Please select an option from below :");


        //Enhance for loop to iterate over menuOptions array
        for (String option : menuOptions) {
            System.out.println(option);

        }

        if (scan.hasNextInt()) {
            String menuChoice = String.valueOf(scan.nextInt());
            return menuChoice;

        } else {
            String menuChoice = scan.nextLine();
            return menuChoice;

        }


    }


    private static void printingQue(String[][] queuePlan) {
        //following for-loop will print cashier area
        for (int i = 0; i < 3; i++) {
            if (i == 0 || i == 2) {
                System.out.println("  *******************");
            } else {
                System.out.println("  *     Cashier     *");
            }
        }

        //following nested for-loops will print queues
        for (int i = 0; i < 5; i++) {
            try {
                for (int j = 0; j < 3; j++) {
                    try {
                        if (queuePlan[j][i] == null) {
                            System.out.print("    O ");
                        } else {
                            System.out.print("    X ");
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.print("      ");//this will add spaces when there is no spaces in a queue
                    }
                }
                System.out.println();

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("      ");//this will cause to go to new line when exception occur at end of a horizontal row
            }
        }


    }

    private static void viewAllEmptyQueues(String[][] queuePlan, int[] emptyQueues) {
        System.out.print("Empty queues at the moment  :");
        //following for-loop will check the last place of each queue to make sure the queue is empty
        for (int i = 0; i < 3; i++) {
            if (queuePlan[i][(queuePlan[i].length - 1)] == null) {
                System.out.print((i + 1) + " ");
                emptyQueues[i] = i + 1;//adding the empty queue to array
            } else {
                emptyQueues[i] = 0;//will hold 0 for relevant queue if not empty
            }
        }
        System.out.println();


    }

    private static void addCustomerToQueue(int[] emptyQueues, Scanner scan, String[][] queuePlan) {
        boolean match;//this variable is used to decide weather program should go for next iteration when needed
        boolean contains; //this variable is used to find that entered array is empty or not

        do {
            try {
                match = false;
                System.out.println("Enter queue number: ");
                int queueNumber = scan.nextInt();


                contains = IntStream.of(emptyQueues).anyMatch((x -> x == queueNumber));

                //implementation of the functionality of the method through adding process method
                if (contains) {

                    addingProcess(queueNumber, queuePlan, scan);

                }

                //else block will handle the input validation
                else {
                    System.out.println("This queue is empty ! would you like to try another queue ?(Y/N)");
                    String choice = scan.next().toUpperCase();

                    //while loop is used to validate input when an invalid option is given
                    while (!choice.equals("Y") && !choice.equals("N")) {
                        System.out.println("Please enter a valid option!");
                        choice = scan.next().toUpperCase();

                    }
                    if (choice.equals("Y")) {
                        match = true;//will go for next iteration
                    }
                }


            } catch (InputMismatchException e) {
                System.out.println("youe enterd an invalid input");
                match = true;
            }

        } while (match);


    }

    private static void addingProcess(int queueNumber, String[][] queuePlan, Scanner scan) {

        System.out.println("Please enter your name :");
        String name = scan.next();

        for (int i = 0; i < (queuePlan[queueNumber - 1].length); i++) {
            if (queuePlan[queueNumber - 1][i] == null) {
                queuePlan[queueNumber - 1][i] = name;//making the position occupied
                break;
            }
        }


    }


}

