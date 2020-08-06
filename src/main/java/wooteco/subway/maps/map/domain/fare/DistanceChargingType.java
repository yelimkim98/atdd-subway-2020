package wooteco.subway.maps.map.domain.fare;

/**
 * 어떤 DistanceChargingType 에 속하기 위한 조건 :
 * bottomLimitDistance < distance <= topLimitDistance
 */
enum DistanceChargingType {

    NONE(0, 0),
    LEVEL_1(5, 100),
    LEVEL_2(8, 100);

    private static int MAX_DISTANCE_OF_NONE = 10;
    private static int MAX_DISTANCE_OF_LEVEL_1 = 50;

    private int chargingInterval;
    private int amountAtOnce;

    DistanceChargingType(int chargingInterval, int amountAtOnce) {
        this.chargingInterval = chargingInterval;
        this.amountAtOnce = amountAtOnce;
    }

    static DistanceChargingType from(int distance) {
        if (0 < distance && distance <= MAX_DISTANCE_OF_NONE) {
            return NONE;
        }
        if (MAX_DISTANCE_OF_NONE < distance && distance <= MAX_DISTANCE_OF_LEVEL_1) {
            return LEVEL_1;
        }
        if (distance > MAX_DISTANCE_OF_LEVEL_1) {
            return LEVEL_2;
        }
        throw new IllegalArgumentException("distance 가 음수일 수 없습니다.");
    }

    public int getChargingInterval() {
        return chargingInterval;
    }

    public int getAmountAtOneInterval() {
        return amountAtOnce;
    }
}
