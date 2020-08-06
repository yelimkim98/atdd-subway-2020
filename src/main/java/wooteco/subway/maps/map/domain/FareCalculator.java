package wooteco.subway.maps.map.domain;

import wooteco.subway.maps.map.domain.fare.DistanceChargeCalculator;

public class FareCalculator {

    private final static int BASIC_FARE = 1_250;

    public static int calculate(int totalDistanceOfPath, int largestLineCharge) {
        return BASIC_FARE
            + DistanceChargeCalculator.calculateDistanceCharge(totalDistanceOfPath)
            + largestLineCharge;
    }
}
