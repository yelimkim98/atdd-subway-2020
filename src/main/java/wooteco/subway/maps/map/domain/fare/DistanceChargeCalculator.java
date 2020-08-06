package wooteco.subway.maps.map.domain.fare;

public class DistanceChargeCalculator {

    private static int DISTANCE_OF_START_CHARGING = 10;

    public static int calculateDistanceCharge(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("이동거리가 0 이하일 수 없습니다.");
        }
        DistanceChargingType distanceChargingType = DistanceChargingType.from(distance);

        return calculateDistanceCharge(distanceChargingType, distance);
    }

    private static int calculateDistanceCharge(DistanceChargingType distanceChargingType, int distance) {
        return calculateNumberOfCharging(distanceChargingType, distance)
            * distanceChargingType.getAmountAtOneInterval();
    }

    private static int calculateNumberOfCharging(
            DistanceChargingType distanceChargingType, int totalDistance) {
        int distanceOverNonChargeRange = totalDistance - DISTANCE_OF_START_CHARGING;
        int chargingInterval = distanceChargingType.getChargingInterval();

        try {
            return 1 + (distanceOverNonChargeRange - 1) / chargingInterval;
        } catch (ArithmeticException e) {    // division by zero
            return 1;
        }
    }
}
