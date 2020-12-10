package org.lsy.learn.kafkamq.consumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("请输入1到3999内的罗马数字：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int preNum = getNum(str.charAt(0));
        int sum = 0;//XXIV
        for (int i = 1; i < str.length(); i++) {
            int num = getNum(str.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            }else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;

        System.out.println(sum);
    }

    public static Integer getNum(char key) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        /*map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("IC", 90);
        map.put("CD", 400);
        map.put("CM", 900);*/

        return map.get(key);
    }

    public static int reverse(int x) {
        if (x < 0) {
            return 0;
        }
        int num = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            num = num * 10 + pop;
        }

        return num;
    }

    public static Boolean palindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int num = 0;
        while (x > num) {
            num = num * 10 + x % 10;
            x /= 10;
        }

        return num == x || (num / 10 == x);
    }
}