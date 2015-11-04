/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinput;

/**
 *
 * @author apprentice
 */
public class LuckySevensServlet {

    int moneyString;

    public void setMoneyString(int moneyString) {
        this.moneyString = moneyString;
    }

    public int getMoneyString() {
        return moneyString;
    }

    public String method() {
        int money = moneyString;
        
        int roll = 0;
        int max = 0;
        int rollAtMax = 1;
        //System.out.println("How many dollars do you wish to bet?");
        String ret = "";
        max = money;
        while (money > 0) {
            roll++;
            int die1 = (int) (6 * Math.random() + 1);
            // ret += die1 + " <br>";
            int die2 = (int) (6 * Math.random() + 1);
            //ret += die2 + " <br>";
            int total = die1 + die2;
            if (total == 7) {
                money += 4;
                if (money > max) {
                    max = money;
                    rollAtMax = roll;
                }
                ret += "Match! <br>";
            } else {
                money--;
            }

        }
        ret += max + " max  money <br>";
        ret += "roll " + rollAtMax + " at max money <br>";
        ret += roll + " rolls <br>";
        return ret;
    }
}
