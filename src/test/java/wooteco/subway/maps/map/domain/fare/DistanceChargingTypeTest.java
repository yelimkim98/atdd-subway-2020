package wooteco.subway.maps.map.domain.fare;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DistanceChargingTypeTest {

    @Test
    @DisplayName("이동거리에 따른 추가요금 타입 분류")
    void from() {
        assertThat(DistanceChargingType.from(10)).isEqualTo(DistanceChargingType.NONE);

        assertThat(DistanceChargingType.from(11)).isEqualTo(DistanceChargingType.LEVEL_1);
        assertThat(DistanceChargingType.from(45)).isEqualTo(DistanceChargingType.LEVEL_1);
        assertThat(DistanceChargingType.from(50)).isEqualTo(DistanceChargingType.LEVEL_1);

        assertThat(DistanceChargingType.from(51)).isEqualTo(DistanceChargingType.LEVEL_2);
    }
}
