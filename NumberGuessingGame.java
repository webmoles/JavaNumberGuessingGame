import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String WHITE = "\033[37m";
    private static final String RESET = "\033[0m";
    
    // 默认难度级别
    private static int difficultyLevel = 1000;

    public static void playGame() {
        Scanner scanner = new Scanner(System.in);
        
        // 首次游戏或询问是否更改难度
        if (difficultyLevel == 1000) {
            // 首次游戏，设置难度
            System.out.println(WHITE + "===== Number Guessing Game =====" + RESET);
            setInitialDifficulty(scanner);
        } else {
            // 非首次游戏，询问是否更改难度
            System.out.print(WHITE + "Change level? (yes/no): " + RESET);
            String changeDifficulty = scanner.nextLine();
            
            if ("exit".equalsIgnoreCase(changeDifficulty)) {
                System.out.println(WHITE + "Exiting the game. Thanks for playing! Goodbye!" + RESET);
                scanner.close();
                System.exit(0);
            }
            
            if ("yes".equals(changeDifficulty)) {
                setDifficulty(scanner);
            }
            
            // 在游戏开始前添加两个空行，无论是否更改难度
            System.out.println();
            System.out.println();
        }
        
        // 生成随机数
        Random random = new Random();
        int theNumber = random.nextInt(difficultyLevel) + 1;
        
        System.out.println(WHITE + "===== Game Started (1-" + difficultyLevel + ") =====" + RESET);
        String input;
        int guess;
        boolean firstGuess = true;

        while (true) {
            // 根据是否首次猜测显示不同的提示
            if (firstGuess) {
                input = getInputWithExitOption(scanner, WHITE + "I'm thinking of a number between 1 and " + difficultyLevel + " (inclusive) ! Try to guess the number I'm thinking of (type 'exit' to quit): " + RESET);
                firstGuess = false;
            } else {
                input = getInputWithExitOption(scanner, WHITE + "Guess again: " + RESET);
            }
            
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println(WHITE + "Exiting the game. Thanks for playing! Goodbye!" + RESET);
                scanner.close();
                System.exit(0);
            }

            try {
                guess = Integer.parseInt(input);
                if (guess > theNumber) {
                    System.out.println(RED + guess + " is too high." + RESET);
                } else if (guess < theNumber) {
                    System.out.println(RED + guess + " is too low." + RESET);
                } else {
                    System.out.println(GREEN + "That's it! You guessed it right!" + RESET);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                    System.out.println();

                    String continueInput = getInputWithExitOption(scanner, WHITE + "Would you like to play again? (yes/no): " + RESET);

                    if (continueInput.equals("yes")) {
                        System.out.println();
                        System.out.println();
                        return;
                    } else {
                        System.out.println(WHITE + "Thanks for playing! Goodbye!" + RESET);
                        scanner.close();
                        System.exit(0);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input. Please guess a valid number or input 'exit'." + RESET);
            }
        }
    }
    
    // 获取用户输入并检查是否为exit
    private static String getInputWithExitOption(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        
        if ("exit".equalsIgnoreCase(input)) {
            System.out.println(WHITE + "Exiting the game. Thanks for playing! Goodbye!" + RESET);
            scanner.close();
            System.exit(0);
        }
        
        return input;
    }
    
    // 首次设置游戏难度（使用简化提示）
    private static void setInitialDifficulty(Scanner scanner) {
        String levelInput;
        while (true) {
            levelInput = getInputWithExitOption(scanner, WHITE + "Choose level (1=Easy|2=Medium|3=Hard): " + RESET);
            
            if (levelInput.equals("1")) {
                difficultyLevel = 10;
                System.out.println(WHITE + "Level set to Easy (1-10)" + RESET);
                break;
            } else if (levelInput.equals("2")) {
                difficultyLevel = 100;
                System.out.println(WHITE + "Level set to Medium (1-100)" + RESET);
                break;
            } else if (levelInput.equals("3")) {
                difficultyLevel = 1000;
                System.out.println(WHITE + "Level set to Hard (1-1000)" + RESET);
                break;
            } else {
                System.out.println(RED + "Invalid level. Please choose 1, 2, or 3." + RESET);
            }
        }
        System.out.println();
    }
    
    // 非首次设置游戏难度
    private static void setDifficulty(Scanner scanner) {
        String levelInput;
        while (true) {
            levelInput = getInputWithExitOption(scanner, WHITE + "Choose difficulty level (1=Easy|2=Medium|3=Hard): " + RESET);
            
            if (levelInput.equals("1")) {
                difficultyLevel = 10;
                System.out.println(WHITE + "Difficulty level set to Easy (1-10)" + RESET);
                break;
            } else if (levelInput.equals("2")) {
                difficultyLevel = 100;
                System.out.println(WHITE + "Difficulty level set to Medium (1-100)" + RESET);
                break;
            } else if (levelInput.equals("3")) {
                difficultyLevel = 1000;
                System.out.println(WHITE + "Difficulty level set to Hard (1-1000)" + RESET);
                break;
            } else {
                System.out.println(RED + "Invalid difficulty. Please input 1, 2, or 3." + RESET);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        while (true) {
            playGame();
        }
    }
}
