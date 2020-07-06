package lambda_streams;

import concurrency.Color;

import javax.sound.midi.SysexMessage;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Animals {
    //This class will be using streams.
    static List<String> animals = Arrays.asList("peacoCK","rabbit","chiwawa","OranguTAN","vipeR","cobra","paNDa","bUffalo","DeeR","maLLard");

    public static void main(String[] args) {

    //Refactored Lambda functions
        Function <List<String>, List> capsLambda = list -> { return list
            .stream()
            .map(a -> { return a.substring(0, 1).toUpperCase() + a.substring(1).toLowerCase();
            }).collect(Collectors.toList());};

        Function <List<String>, List> lowerLambda = list -> { return list
            .stream()
            .map(a -> { return a.substring(0, 1).toLowerCase() + a.substring(1).toUpperCase();
            }).collect(Collectors.toList());};

        Function <List<String>, List> flipLambdaTrue = list -> {  list
            .stream().collect(Collectors
                .collectingAndThen(Collectors.toList(),
                    flist -> {
                        Collections.reverse(flist);
                        return flist.stream();
                             }));
                    return list;
                    };

        Function <List<String>, List> flipLambdaFalse = list -> {
            list
                .stream();
            Collections.reverse(list);
            return list;};

        //CUSTOM Sorting lambda biFunction and user functions
        BiFunction<List<String>, Function <List<String>, List>, List> sortLambdaCustom = (list, f) -> { return f.apply(list);};

        Function <List<String>, List> sortLambdaNorm = f -> f
            .stream().map(String::toLowerCase).sorted().collect(Collectors.toList());

        Function <List<String>, List> sortLambdaReverse = f -> f
            .stream().map(String::toLowerCase).sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        //Add random animals to list lambda
        Function <List<String>, List> lambdaRandomAnimals = randomList -> {
            List<String> randomAnimals = Arrays.asList("DONKEY", "CHIMPANZEE", "LIZARD", "PUG", "SHEEP", "GOAT", "SNAIL", "STICKBUG");
            Set<String> randomAnimalsSet = new HashSet<String>();
            Random rando = new Random();
            while(randomAnimalsSet.size() < 3) {
                randomAnimalsSet.add(randomAnimals.get(Math.abs(rando.nextInt() % randomAnimals.size())));
            }
            animals.addAll(randomAnimalsSet);

            System.out.println(Color.getRandom() + "Appended List with Random Animals: " + Color.getRandom() + animals.toString() + Color.RESET);
            return animals;

        };

        //Printed lists
        System.out.println(Color.getRandom() + "original animals : " + animals + Color.RESET);

        //clean up the animals array by using the capsFirst() method. follow instructions in the method definition. 
        List<String> cleaned = capsFirst(animals, false, capsLambda);
        System.out.println(Color.getRandom() + "Camel Case new List: " + Color.getRandom() + cleaned + Color.RESET);
       
        //do not modify these addAnimal() method invocations
        addAnimal("rEIndeeR");
        addAnimal("Platypus");
        addAnimal("frOg");
        addAnimal("lEOpArD");
        //---------------------------------------

        animals = capsFirst(animals,true, capsLambda);
        System.out.println(Color.getRandom() + "Camel Case original list: " + Color.getRandom() + animals + Color.RESET);

        List<String> lowered = lowerFirst(animals,false, lowerLambda);
        System.out.println(Color.getRandom() + "Lower First new List: " + Color.getRandom() + lowered + Color.RESET);

        animals = lowerFirst(animals, true, lowerLambda);
        System.out.println(Color.getRandom() + "Lower First original list: " + Color.getRandom() + animals + Color.RESET);

        List<String> flipped = flipAnimals(false, flipLambdaTrue, flipLambdaFalse);
        System.out.println(Color.getRandom() + "Flipped new List : " + Color.getRandom() + flipped + Color.RESET);

        animals = flipAnimals(true, flipLambdaTrue, flipLambdaFalse);
        System.out.println(Color.getRandom() + "Flipped original List : " + Color.getRandom() + animals + Color.RESET);

        //BONUS: Custom sort default
        animals = sortAnimals(sortLambdaCustom, sortLambdaNorm);
        System.out.println(Color.getRandom() + "Sorted default lambda : " + Color.getRandom() + animals + Color.RESET);

        //BONUS: Custom sort user lambda (reverse alphabetical)
        List<String> sorted = sortAnimals(sortLambdaCustom, sortLambdaReverse);
        System.out.println(Color.getRandom() + "Custom Sort (reverse alphabetic) : " + Color.getRandom() + sorted + Color.RESET);

        //BONUS: add list of random animals to default list
        addRandomAnimal(lambdaRandomAnimals);

    }

    static List<String> capsFirst(List<String> animalList, boolean mutate, Function <List<String>, List> capsLambda) {
        //clean up the animals list so that the first letter is capitalized, and all the other letters are lowercased.
        // Use a stream to accomplish this task.  Also, the 2nd parameter of this function is a boolean.
        // use this boolean 'flag' to determine whether or not to 'mutate' the original animals array stored as a static class field.
        // if the flag is set to 'true', mutate the animals and return the animals out of the function.
        // if it is false, create a copy of the animals, perform your stream operations on the copy,
        // and return the copy of animals out of the function, WITHOUT modifying the original animals array.

        if (mutate) {
            System.out.println("is true");
            return capsLambda.apply(animalList);

        } else {
            System.out.println("is false");
            List<String> upperList = capsLambda.apply(animalList);
            return upperList;
        }
    }
    static String addAnimal(String animal) {
        //add an animal to the animal list.
        List<String> listOfAnimals = new ArrayList<>(animals);
        listOfAnimals.add(animal);
        animals = listOfAnimals;
        return animal;
    };

    static List<String> addRandomAnimal(Function <List<String>, List> lambdaRandomAnimals) {
        //add a list of random animals to the animal list.

        return lambdaRandomAnimals.apply(animals);
    };

    static List<String> lowerFirst(List<String> animalList, boolean mutate, Function <List<String>, List> lowerLambda) {
        //lowercase the first letter, and uppercase the rest of the letters, using streams.
        // Also, depending on the value of the boolean flag 'mutate', mutate the original animals list,
        // or perform your stream operations on a 'copy' of the animals list.  return the list out of the function in both cases.
        if (mutate) {
            System.out.println("is true");
            return lowerLambda.apply(animalList);

        } else {
            System.out.println("is false");
            List<String> lowerList = lowerLambda.apply(animalList);
            return lowerList;
        }
    }

    static List<String> flipAnimals(boolean mutate, Function <List<String>, List> flipLambdaTrue, Function <List<String>, List> flipLambdaFalse) {
        //reverse the order of the animals in the animal list.
        // If the boolean parameter is true, reverse the static animals array list by mutating the array.
        // if the mutate boolean is false, flip a 'copy' of the animals list, then return that list of flipped animals,
        // WITHOUT mutating the static animals array. return the flipped list in both cases.

//        List<String> instructions = Arrays.asList("Flip","the","animals","list","."," ","Mutate","the","animals","only","if","flag","true");
//        return instructions;
        if (mutate) {
            System.out.println("is true");
            return flipLambdaTrue.apply(animals);

        } else {
            System.out.println("is false");
            List<String> flippedList = flipLambdaFalse.apply(animals);
            return flippedList;
        }
    }

    static List<String> sortAnimals(BiFunction<List<String>, Function <List<String>, List>, List> sortLambdaCustom, Function <List<String>, List> customSearchLambda) {
    //sort the animals in alphabetical order.  If the boolean parameter is true, mutating the animals list.
        // if the mutate boolean is false, sort a 'copy' of the animals list, then return that list of sorted animals,
        // WITHOUT mutating the static animals array. return the sorted list in both cases.

            return  sortLambdaCustom.apply(animals, customSearchLambda);
        }
    }
