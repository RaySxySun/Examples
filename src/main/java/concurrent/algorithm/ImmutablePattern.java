package concurrent.algorithm;

/**
 * Immutable Pattern is different from read-only.
 * Read-only class may have independent variables, like "keeplive" - it will change
 *
 * Please note below 4 points:
 * 1. remove all setter method
 * 2. mark all member variables as private & final
 * 3. make sure no sub class (final class) can chang it.
 * 4. have a constructor to initiate all member variables.
 */
public final class ImmutablePattern {
    private final String no;
    private final String name;
    private final double price;

    public ImmutablePattern(String no, String name, double price) {
        this.no = no;
        this.name = name;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
