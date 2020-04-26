package Entity.PricingPolicies;

import java.math.BigDecimal;

public class Payment {

    private final PricingPolicies pricingPolicies;

    public Payment(PricingPolicies pricingPolicies) {
        this.pricingPolicies = pricingPolicies;
    }

    public BigDecimal getPrice(long hours) {
        return pricingPolicies.getPrice(hours);
    }
}
