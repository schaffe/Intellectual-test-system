package com.company.Model.session.logic;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 3/23/14
 */
class StatisticTableRow {
    private final int complexity;
    private final int point;
    private final double multiplier;
    private final double rate;

    public StatisticTableRow(int complexity, int point, double multiplier,double rate) {
        this.complexity = complexity;
        this.point = point;
        this.multiplier = multiplier;
        this.rate = rate;
    }

    public int getComplexity() {
        return complexity;
    }

    public int getPoint() {
        return point;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format("StatisticTableRow{" +
                "complexity= " + complexity +
                ", point= " + point +
                ", minus= %.1f"  +
                ", rate= %.1f" +
                '}', multiplier, rate);
    }

    public String toString2() {
        return String.format(
                " " + complexity +
                " " + point +
                " %.1f"  +
                " %.1f"
                , multiplier, rate);
    }
}
