package lambda_streams;

import concurrency.Color;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Numbers {
    static List<Integer> nums = Arrays.asList(10,100,1000,5,50,500,3,30,300,7,70,700,1,10,100,25,250,2500);

    public static void main(String[] args) {
        //Part I :complete the static class methods that have been set up in this Numbers class java file.  Use streams to compute the results whenever possible.
        System.out.println(nums);

        //Part II - refactor all of the class methods to accept lambda expressions.
        // You can put the lambda functions directly inside the method calls, or defined them first, then pass them into the methods.
        // give them the same names as the static methods, but add the word 'lambda' in front of every lambda function:

        Function<Integer, Boolean> lambdaIsOdd = i -> { return (nums.get(i) % 2 != 0);};

        Function<Integer, Boolean> lambdaIsEven = i -> { return (nums.get(i) % 2 == 0);};

        Function<Integer, Boolean> lambdaIsPrime = i -> {
            int ind = nums.get(i);
            if (ind <= 1) {return false;};
            for(int j=2; j<=ind/2;j++)
            {
                int testI = ind % j;
                if (testI == 0){
                    return false;}
            }
            return true;};

        Function<Integer, Integer> lambdaAdded = added -> { return
            nums.stream().reduce(0, (subtotal, element) -> subtotal + element);};

        Function<Integer, Integer> lambdaSubtracted = added -> { return
            nums.stream().reduce(0, (subtotal, element) -> subtotal - element);};

        Function<BigInteger, BigInteger> lambdaMultiplied = product -> { return
            nums.stream().map(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply);};

        Function<Double, Double> lambdaDivided = quotient -> { return
            nums.stream().map(Double::valueOf).reduce(1d, (subtotal, element) -> subtotal / element);};

        Function<Integer, Integer> lambdaFindMax = max -> { return nums.stream().max(Comparator.comparing(Integer::valueOf))
            .get();};

        Function<Integer, Integer> lambdaFindMin = max -> { return nums.stream().min(Comparator.comparing(Integer::valueOf))
            .get();};

        Function<List<Integer>, Integer> lambdaCompare = compared -> {
            if (nums.get(compared.get(0)) > nums.get(compared.get(1))) {
                return 1;
            }
            if (nums.get(compared.get(1)) > nums.get(compared.get(0))) {
                return -1;
            } else return 0;

            };

        Function<Integer, Integer> lambdaAppend = appended -> {
            nums = new ArrayList<Integer>(nums);
            nums.add(Integer.valueOf(appended));
            System.out.println(Color.getRandom() + "Appended List: " + Color.getRandom() + nums + Color.RESET);
            return appended;


        };

        //Instances of methods
        for (int i = 0; i < nums.size(); i++){
           System.out.println(Color.getRandom() + "is " + nums.get(i) + " odd? " + Color.getRandom() + isOdd(i, lambdaIsOdd) + Color.RESET);
        }
        for (int i = 0; i < nums.size(); i++){
            System.out.println(Color.getRandom() + "is " + nums.get(i) + " even? " + Color.getRandom() + isEven(i, lambdaIsEven) + Color.RESET);
        }
        for (int i = 0; i < nums.size(); i++){
            System.out.println(Color.getRandom() + "is " + nums.get(i) + " prime? " + Color.getRandom() + isPrime(i, lambdaIsPrime) + Color.RESET);
        }

        System.out.println(Color.getRandom() + "List Sum: " + Color.getRandom() + added(lambdaAdded) + Color.RESET);
        System.out.println(Color.getRandom() + "List Subtracted: " + Color.getRandom() + subtracted(lambdaSubtracted) + Color.RESET);
        System.out.println(Color.getRandom() + "List Product: " + Color.getRandom() + multiplied(lambdaMultiplied) + Color.RESET);
        System.out.println(Color.getRandom() + "List Quotient: " + Color.getRandom() + divided(lambdaDivided) + Color.RESET);
        System.out.println(Color.getRandom() + "List max value: " + Color.getRandom() + findMax(lambdaFindMax) + Color.RESET);
        System.out.println(Color.getRandom() + "List min value: " + Color.getRandom() + findMin(lambdaFindMin) + Color.RESET);

        int compared = compare(Arrays.asList(0, 1), lambdaCompare);
        System.out.println(Color.getRandom() + "Is 10 > 100? " + Color.getRandom() + compared + Color.RESET);

        int compared2 = compare(Arrays.asList(2, 3), lambdaCompare);
        System.out.println(Color.getRandom() + "Is 1000 > 5? " + Color.getRandom() + compared2 + Color.RESET);

        int compared3 = compare(Arrays.asList(4, 5), lambdaCompare);
        System.out.println(Color.getRandom() + "Is 50 > 500? " + Color.getRandom() + compared3 + Color.RESET);

        int appendedNum = append(1111, lambdaAppend);
        System.out.println(Color.getRandom() + "Appended Number " + Color.getRandom() + appendedNum + Color.RESET);

    }

    static boolean isOdd(int i, Function <Integer, Boolean> lambdaIsOdd) {
        //determine if the value at the index i is odd.  return true if yes, return false if  no.
        return lambdaIsOdd.apply(i);
    }

    static boolean isEven(int i, Function <Integer, Boolean> lambdaIsEven) {
        //determine if the value at the index i is even.  return true if yes, return false if  no.
        return lambdaIsEven.apply(i);
    }

    static boolean isPrime(int i, Function <Integer, Boolean> lambdaIsPrime) {
         //determine if the value at the index i is a prime number.  return true if yes, return false if no.
            return lambdaIsPrime.apply(i);
    }

    static int added(Function <Integer, Integer> lambdaAdded) {
        //add all the elements in the list.  return the sum.
        int added = 0;
        return lambdaAdded.apply(added);
    }

    static int subtracted(Function <Integer, Integer> lambdaSubtracted) {
        //subtract all the elements in the list. return the remainder.
        int subtracted = 0;

        return lambdaSubtracted.apply(subtracted);
    }

    static BigInteger multiplied(Function <BigInteger, BigInteger> lambdaMultiplied) {
        //multiply all the elements in the list. and return the product.
        BigInteger product = BigInteger.valueOf(0);
        return lambdaMultiplied.apply(product);

    }

    static Double divided(Function <Double, Double> lambdaDivided) {
        //Divide all the elements in the list. and return the product.
        Double quotient = 0.00d;
        return lambdaDivided.apply(quotient);
    }

    static int findMax(Function <Integer, Integer> lambdaFindMax) {
         //return the maximum value in the list.
        int max = 0;
        return lambdaFindMax.apply(max);
    }

    static int findMin(Function <Integer, Integer> lambdaFindMin) {
        //return the minimum value in the list.
        int min = 0;
        return lambdaFindMin.apply(min);
    }

    static int compare(List<Integer> l, Function <List<Integer>, Integer> lambdaCompare) {
        //compare the values stored in the array at index position i and j.  
        //if the value at i is greater, return 1.  if the value at j is greater, return -1.  if the two values are equal, return 0.

        return lambdaCompare.apply(l);
    }

    static int append(int n, Function <Integer, Integer> lambdaAppend) {
        //add a new value to the values list. return that value after adding it to the list.

        return lambdaAppend.apply(n);
    }
}
