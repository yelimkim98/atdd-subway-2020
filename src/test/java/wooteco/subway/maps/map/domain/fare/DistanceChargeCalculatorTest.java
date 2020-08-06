package wooteco.subway.maps.map.domain.fare;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DistanceChargeCalculatorTest {

    @Test
    @DisplayName("거리에 의한 추가요금 계산 - 기본요금 거리")
    void calculateDistanceCharge_IfDistanceIsLessThenOrEqualTo10_ReturnBasicCharge() {
        assertThat(DistanceChargeCalculator.calculateDistanceCharge(1)).isEqualTo(0);
        assertThat(DistanceChargeCalculator.calculateDistanceCharge(10)).isEqualTo(0);
    }

    @Test
    @DisplayName("거리에 의한 운임 계산 - 기본요금 거리")
    void calculateDistanceCharge_IfDistanceIsMoreThen10AndLessThenOrEqualTo20_ReturnRightCharge() {
        assertThat(DistanceChargeCalculator.calculateDistanceCharge(11)).isEqualTo(100);
        assertThat(DistanceChargeCalculator.calculateDistanceCharge(15)).isEqualTo(100);

        assertThat(DistanceChargeCalculator.calculateDistanceCharge(16)).isEqualTo(200);
        assertThat(DistanceChargeCalculator.calculateDistanceCharge(20)).isEqualTo(200);

        assertThat(DistanceChargeCalculator.calculateDistanceCharge(21)).isEqualTo(300);
        assertThat(DistanceChargeCalculator.calculateDistanceCharge(25)).isEqualTo(300);

        assertThat(DistanceChargeCalculator.calculateDistanceCharge(50)).isEqualTo(800);
    }
}
