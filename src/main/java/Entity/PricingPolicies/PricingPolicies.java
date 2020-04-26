package Entity.PricingPolicies;

import java.math.BigDecimal;

public interface PricingPolicies {

    BigDecimal getPrice(long hours);
}
