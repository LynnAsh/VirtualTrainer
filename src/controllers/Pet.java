package controllers;

public class Pet {
    private String name;
    private int points = 0;
    private int ppc = 1;

    private int mppc1 = 0;
    private int mppc2 = 0;
    private int mppc1Mul = 2;
    private int mppc2Mul = 5;

    private boolean hasPiercing = false;
    private boolean hasBow = false;
    private boolean hasNecklace = false;
    private boolean hasCrown = false;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setmppc1(int num) {
        if (num > 0) {
            mppc1 = num;
        } else {
            if (points >= 1000) {
                points -= 1000;
                mppc1 += 200;
            }
        }
    }

    public int getmppc1() {
        return mppc1;
    }

    public void setmppc2(int num) {
        if (num > 0) {
            mppc2 = num;
        } else {
            if (points > 2000) {
                points -= 2000;
                mppc2 += 80;
            }
        }
    }

    public int getmppc2() {
        return mppc2;
    }

    public void onClick() {
        if (mppc1 > 0 && mppc2 > 0) {
            points += ppc * (mppc1Mul + mppc2Mul);
            mppc1--;
            mppc2--;
        } else if (mppc1 > 0) {
            points += ppc * mppc1Mul;
            mppc1--;
        } else if (mppc2 > 0) {
            points += ppc * mppc2Mul;
            mppc2--;
        } else {
            points += ppc;
        }
    }

    public void setPoints(int amount) {
        points += amount;
    }

    public int getPoints() {
        return points;
    }

    public void setppc(int add) {
        ppc += add;
    }

    public int getppc() {
        return ppc;
    }

    public void setCosmetic(String item) {
        if (item.equals("bow")) {
            hasBow = true;
        } else if (item.equals("necklace")) {
            hasNecklace = true;
        } else if (item.equals("crown")) {
            hasCrown = true;
        } else {
            hasPiercing = true;
        }
    }

    public boolean getCosmetic(String item) {
        if (item.equals("bow")) {
            return hasBow;
        } else if (item.equals("necklace")) {
            return hasNecklace;
        } else if (item.equals("crown")) {
            return hasCrown;
        } else {
            return hasPiercing;
        }
    }
}
