package Entity.PricingPolicies;

import java.math.BigDecimal;

public class PaymentPerHour implements PricingPolicies {

    private final BigDecimal price;

    public PaymentPerHour(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice(long hours) {
        return price.multiply(BigDecimal.valueOf(hours));
    }
}
