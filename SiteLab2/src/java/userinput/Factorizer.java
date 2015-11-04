/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinput;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class Factorizer {

    void method() {
        Scanner scan = new Scanner(System.in);
        int input;
        int numFac = 0;
        int perNumInc = 0;
        System.out.println("Which number do you want to factor?");
        input = scan.nextInt();
        System.out.println("You selected: " + input);
        for (int i = 1; i < input; i++) {

            if (input % i == 0) {

                numFac++;
                perNumInc += i;
                System.out.println(i + " is a factor");

            }

        }
        System.out.println("There were " + numFac + " factors.");
        if (input == perNumInc) {
            System.out.println("This number is a perfect number!");
        }
        if (numFac == 1) {
            System.out.println(input + " is a prime number.");
        }
    }
}
