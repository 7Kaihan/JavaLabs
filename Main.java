package main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

interface Preform<T> {
    T preform(ArrayList<T> toPreform);
}

public class Main {
    public static void main(String[] args) {
        System.out.print(Color.CYAN_BOLD + "Enter how many numbers you want to preform summation on : " + Color.RESET + "\n");
        int n = new Scanner(System.in).nextInt();
        ArrayList arrayList = null;
        while (true) {
            System.out.print(Color.CYAN_BOLD + "Enter operation (sub for Subtraction, sum for Summation) : ");
            String operation = new Scanner(System.in).nextLine();
            switch (operation) {
                case ("sum") -> {
                    while (true) {
                        System.out.print(Color.CYAN_BOLD + "Enter their type (int,double)" + Color.RESET + "\n");
                        String type = new Scanner(System.in).nextLine();
                        switch (type) {
                            case ("int") -> {
                                arrayList = new ArrayList<Integer>();
                                for (int i = 0; i < n; i++) {
                                    System.out.print(Color.CYAN_BOLD + "Enter number : " + Color.RESET + "\n");
                                    arrayList.add(new Scanner(System.in).nextInt());
                                }
                                Preform<Integer> integerPreform = new Helper<Integer>(Integer.class)::summer;
                                System.out.println(integerPreform.preform(arrayList));
                            }
                            case ("double") -> {
                                arrayList = new ArrayList<Double>();
                                for (int i = 0; i < n; i++) {
                                    System.out.print(Color.CYAN_BOLD + "Enter number : " + Color.RESET + "\n");
                                    arrayList.add(new Scanner(System.in).nextDouble());
                                }
                                Preform<Double> doublePreform = new Helper<Double>(Double.class)::summer;
                                System.out.println(doublePreform.preform(arrayList));
                            }
                            default -> System.err.println("invalid!!!!");
                        }
                        if (type.equals("int") || type.equals("double")) break;
                    }
                }
                default -> System.err.println("invalid");
            }
            if (operation.equals("sum") || operation.equals("sub")) break;
        }
    }
}

class Helper<T> {
    private final Class<T> type;

    public Helper(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return this.type;
    }

    public T summer(ArrayList<T> toPreform) {
        AtomicReference returner = null;
        if (getType() == Integer.class) {
            AtomicInteger summ = new AtomicInteger();
            toPreform.forEach(sum -> {
                summ.set(summ.addAndGet((Integer) sum));
            });
            returner = new AtomicReference<>(summ.get());
        }
        if (getType() == Double.class) {
            AtomicReference<Double> summ = new AtomicReference<>();
            toPreform.forEach(sum -> {
                summ.set(summ.get() + ((Double) sum));
            });
            returner = new AtomicReference<>(summ.get());
        }
        return (T) returner.get();
    }

}

enum Color {
    RESET("\033[0m"),
    CYAN_BOLD("\033[1;36m");
    private final String code;

    Color(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
