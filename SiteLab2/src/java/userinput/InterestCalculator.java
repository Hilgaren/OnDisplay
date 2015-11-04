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
public class InterestCalculator {

    void method() {
        Scanner scan = new Scanner(System.in);
        double qir;
        double principal;
        double curBal;
        double newBal = 0;
        int numYears;
        int currentYear = 0;
        int r;
        int cNum;
        System.out.println("What is the annual interest rate? (in percent)");
        qir = scan.nextDouble() / 4;
        System.out.println("What is the principal amount?");
        principal = scan.nextDouble();
        curBal = principal;
        System.out.println("How many years?");
        numYears = scan.nextInt();
        System.out.println("How is the interest compounded?");
        System.out.println("1. Quarterly 2. Monthly 3. Daily (number)");
        r = scan.nextInt();

        for (int i = 0; i < numYears; i++) {
            double p = curBal;
            currentYear++;
            System.out.println("year: " + currentYear);
            System.out.println("Principal: " + curBal);
            if (r == 1) {

                for (int x = 0; x < 4; x++) {

                    newBal = curBal * (1 + (qir / 100));
                    curBal = newBal;
                }

            } else if (r == 2) {
                for (int x = 0; x < 12; x++) {

                    newBal = curBal * (1 + ((qir / 3) / 100));
                    curBal = newBal;
                }

            } else if (r == 3) {
                for (int x = 0; x < 1; x++) {

                    newBal = curBal * (1 + ((qir * 4) / 100));
                    curBal = newBal;
                }

            }
            curBal = newBal;
            System.out.println("Interest Amount: " + (curBal - p));
            System.out.println("EoY Principal: " + curBal);

        }

    }
}
