package Entity.Parking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingBill {

    private final LocalDateTime start;
    private LocalDateTime end;
    private final String plate;
    private BigDecimal amount;

    public ParkingBill(LocalDateTime start, String plate) {
        this.start = start;
        this.plate = plate;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPlate() {
        return plate;
    }
}
