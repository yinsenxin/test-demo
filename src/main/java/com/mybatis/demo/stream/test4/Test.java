package com.mybatis.demo.stream.test4;


import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions =
                Stream.of(
                        new Transaction(brian, 2011, 300),
                        new Transaction(raoul, 2012, 1000),
                        new Transaction(raoul, 2011, 400),
                        new Transaction(mario, 2012, 710),
                        new Transaction(mario, 2012, 700),
                        new Transaction(alan, 2012, 950)
                ).collect(Collectors.toList());

        // 找出2011年发生的所有交易，并按交易额排序（从低到高）。

        List<Transaction> collect = transactions
                .stream()
                .filter(item -> item.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        // 交易员都在哪些不同的城市工作过
        List<String> collect1 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        // 查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> cambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        // 返回所有交易员的姓名字符串，按字母顺序排序
        String reduce = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(Comparator.comparing(a -> a))
                .collect(Collectors.joining());

        // 有没有交易员是在米兰工作的(anyMatch)
        boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        // 打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 所有交易中，最高的交易额是多少
        Optional<Integer> reduce1 = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        // 找到交易额最小的交易
        //Optional<Integer> reduce2 = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
        transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        // 交易额 求和
        transactions.stream().map(Transaction::getValue).reduce(0, Integer::sum);
        // 使用流特化 减少装箱拆箱消耗
        int sum = transactions.stream().mapToInt(Transaction::getValue).sum();

        long un = 0;

        try(Stream<String> lines = Files.lines(Paths.get("D:\\111.txt"), Charset.defaultCharset())){
            lines.forEach(System.out::println);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

class Trader{
    private final String name;
    private final String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

class Transaction{
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
