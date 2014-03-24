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
    private final int total;
    private final double rate;

    public StatisticTableRow(int complexity, int point, double multiplier, int total, double rate) {
        this.complexity = complexity;
        this.point = point;
        this.multiplier = multiplier;
        this.total = total;
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

    public int getTotal() {
        return total;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format("StatisticTableRow{" +
                "complexity=" + complexity +
                ", point=" + point +
                ", multiplier= %.1f"  +
                ", total=" + total +
                ", rate= %.1f" +
                '}', multiplier, rate);
    }
}
