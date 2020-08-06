package wooteco.subway.maps.map.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FareCalculatorTest {

    @Test
    @DisplayName("라인에 따른 추가요금이 없는 경우 요금계산")
    void calculate() {
        assertThat(FareCalculator.calculate(11, 0))
            .isEqualTo(1_350);
    }
}
