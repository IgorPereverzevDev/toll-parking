package Entity.PricingPolicies;

import java.math.BigDecimal;

public class PaymentFixAmountPlusPerHour implements PricingPolicies {

    private final BigDecimal fixAmount;
    private final BigDecimal price;

    public PaymentFixAmountPlusPerHour(BigDecimal fixAmount, BigDecimal price) {
        this.fixAmount = fixAmount;
        this.price = price;
    }

    @Override
    public BigDecimal getPrice(long hours) {
        return fixAmount.add(price.multiply(BigDecimal.valueOf(hours)));
    }
}
