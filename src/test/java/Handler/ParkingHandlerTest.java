package Handler;

import Entity.Car.Car;
import Entity.Car.Electric;
import Entity.Car.Gasoline;
import Entity.EngineType;
import Entity.Parking.Slot;
import Entity.PricingPolicies.Payment;
import Entity.PricingPolicies.PaymentFixAmountPlusPerHour;
import Entity.PricingPolicies.PaymentPerHour;
import Entity.PricingPolicies.PricingPolicies;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

public class ParkingHandlerTest {

    @org.junit.Test
    public void parkElectric20WithPaymentPerHour() {
        var car = new Car("111", new Electric(EngineType.ELECTRIC, 20));
        var parkingHandler = parkAndLeaveElectricCarWithFixAmountPlusPerHourAndWithPaymentPerHour(
                car, new PaymentPerHour(BigDecimal.TEN));
        assertTrue(parkingHandler.getParking().getBooked().containsKey(car));
        assertNotNull(parkingHandler.getParking().getBooked().get(car));
        assertEquals(20, parkingHandler.getParking().getBooked().get(car).getSlot().getSlotDetails().getPower());
    }

    @org.junit.Test
    public void parkElectric50WithPaymentPerHour() {
        var car = new Car("111", new Electric(EngineType.ELECTRIC, 50));
        var parkingHandler = parkAndLeaveElectricCarWithFixAmountPlusPerHourAndWithPaymentPerHour(
                car, new PaymentPerHour(BigDecimal.TEN));
        assertTrue(parkingHandler.getParking().getBooked().containsKey(car));
        assertNotNull(parkingHandler.getParking().getBooked().get(car));
        assertEquals(50, parkingHandler.getParking().getBooked().get(car).getSlot().getSlotDetails().getPower());
    }

    @org.junit.Test
    public void parkGasolineWithPaymentPerHour() {
        var car = new Car("111", new Gasoline(EngineType.GASOLINE));
        var parkingHandler = parkAndLeaveGasolineCarWithFixAmountPlusPerHourAndWithPaymentPerHour(
                car, new PaymentPerHour(BigDecimal.TEN));
        assertTrue(parkingHandler.getParking().getBooked().containsKey(car));
        assertNotNull(parkingHandler.getParking().getBooked().get(car));
    }


    @org.junit.Test
    public void parkElectric20WithPaymentFixAmountPlusPerHour() {
        var car = new Car("111", new Electric(EngineType.ELECTRIC, 20));
        var parkingHandler = parkAndLeaveElectricCarWithFixAmountPlusPerHourAndWithPaymentPerHour(car,
                new PaymentFixAmountPlusPerHour(BigDecimal.ONE, BigDecimal.TEN));
        assertTrue(parkingHandler.getParking().getBooked().containsKey(car));
        assertNotNull(parkingHandler.getParking().getBooked().get(car));
        assertEquals(20, parkingHandler.getParking().getBooked().get(car).getSlot().getSlotDetails().getPower());
    }

    @org.junit.Test
    public void parkElectric50WithPaymentFixAmountPlusPerHour() {
        var car = new Car("111", new Electric(EngineType.ELECTRIC, 50));
        var parkingHandler = parkAndLeaveElectricCarWithFixAmountPlusPerHourAndWithPaymentPerHour(car,
                new PaymentFixAmountPlusPerHour(BigDecimal.ONE, BigDecimal.TEN));
        assertTrue(parkingHandler.getParking().getBooked().containsKey(car));
        assertNotNull(parkingHandler.getParking().getBooked().get(car));
        assertEquals(50, parkingHandler.getParking().getBooked().get(car).getSlot().getSlotDetails().getPower());
    }

    @org.junit.Test
    public void parkGasolineWithPaymentFixAmountPlusPerHour() {
        var car = new Car("111", new Gasoline(EngineType.GASOLINE));
        var parkingHandler = parkAndLeaveGasolineCarWithFixAmountPlusPerHourAndWithPaymentPerHour(car,
                new PaymentFixAmountPlusPerHour(BigDecimal.ONE, BigDecimal.TEN));
        assertTrue(parkingHandler.getParking().getBooked().containsKey(car));
        assertNotNull(parkingHandler.getParking().getBooked().get(car));
    }


    @org.junit.Test
    public void leavePaymentWithFixAmountPlusPerHour() {
        var car = new Car("111", new Gasoline(EngineType.GASOLINE));
        var parkingHandler = parkAndLeaveGasolineCarWithFixAmountPlusPerHourAndWithPaymentPerHour(car,
                new PaymentFixAmountPlusPerHour(BigDecimal.ONE, BigDecimal.TEN));

        var startTime = parkingHandler.getParking().getBooked().get(car).getParkingBill().getStart();
        var parkingBill = parkingHandler.leave(car);
        var endTime = parkingBill.getEnd();

        var payment = new Payment(new PaymentFixAmountPlusPerHour(BigDecimal.ONE, BigDecimal.TEN));
        var price = payment.getPrice(startTime.until(endTime, ChronoUnit.HOURS));

        assertEquals(price, parkingBill.getAmount());
        assertEquals(car.getPlate(), parkingBill.getPlate());
        assertFalse(parkingHandler.getParking().getBooked().containsKey(car));
    }


    @org.junit.Test
    public void leavePaymentWithPaymentPerHour() {
        var car = new Car("111", new Gasoline(EngineType.GASOLINE));
        var parkingHandler = parkAndLeaveGasolineCarWithFixAmountPlusPerHourAndWithPaymentPerHour(car,
                new PaymentPerHour(BigDecimal.TEN));

        var startTime = parkingHandler.getParking().getBooked().get(car).getParkingBill().getStart();
        var parkingBill = parkingHandler.leave(car);
        var endTime = parkingBill.getEnd();

        var payment = new Payment(new PaymentPerHour(BigDecimal.TEN));
        var price = payment.getPrice(startTime.until(endTime, ChronoUnit.HOURS));

        assertEquals(price, parkingBill.getAmount());
        assertEquals(car.getPlate(), parkingBill.getPlate());
        assertFalse(parkingHandler.getParking().getBooked().containsKey(car));

    }

    private ParkingHandler parkAndLeaveGasolineCarWithFixAmountPlusPerHourAndWithPaymentPerHour(
            Car car, PricingPolicies pricingPolicies) {
        var engine = new Gasoline(EngineType.GASOLINE);
        var parkingHandler = new ParkingHandler(pricingPolicies);
        parkingHandler.getParking().getSlots().add(new Slot(true, engine));
        parkingHandler.park(car);
        return parkingHandler;
    }

    private ParkingHandler parkAndLeaveElectricCarWithFixAmountPlusPerHourAndWithPaymentPerHour(
            Car car, PricingPolicies pricingPolicies) {
        var engine = new Electric(car.getCarEngine().getEngineType(), car.getCarEngine().getPower());
        var parkingHandler = new ParkingHandler(pricingPolicies);
        parkingHandler.getParking().getSlots().add(new Slot(true, engine));
        parkingHandler.park(car);
        return parkingHandler;
    }

}