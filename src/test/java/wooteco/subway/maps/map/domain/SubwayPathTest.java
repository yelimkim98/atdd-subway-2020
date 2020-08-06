package wooteco.subway.maps.map.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.line.domain.LineStation;
import wooteco.subway.maps.station.domain.Station;

class SubwayPathTest {

    private static final Line lineNumberTwo = mock(Line.class);
    private static final Line lineNumberFive = mock(Line.class);

    private static final Station 신정역 = mock(Station.class);
    private static final Station 까치산역 = mock(Station.class);
    private static final Station 신정네거리역 = mock(Station.class);

    private static final Long 신정역_Id = 1L;
    private static final Long 까치산역_id = 2L;
    private static final Long 신정네거리역_id = 3L;

    private SubwayPath subwayPath;

    static {
        when(신정역.getId()).thenReturn(신정역_Id);
        when(까치산역.getId()).thenReturn(까치산역_id);
        when(신정네거리역.getId()).thenReturn(신정네거리역_id);

        when(신정역.getName()).thenReturn("신정역");
        when(까치산역.getName()).thenReturn("까치산역");
        when(신정네거리역.getName()).thenReturn("신정네거리역");

        when(lineNumberFive.getId()).thenReturn(1L);
        when(lineNumberTwo.getId()).thenReturn(2L);
    }

    @BeforeEach
    void setUp() {
        LineStationEdge lineStationEdgeOfLineFive = new LineStationEdge(
            new LineStation(까치산역_id, 신정역_Id, 5, 2),
            lineNumberFive.getId()
        );
        LineStationEdge lineStationEdgeOfLineTwo = new LineStationEdge(
            new LineStation(까치산역_id, 신정네거리역_id, 6, 3),
            lineNumberTwo.getId()
        );
        subwayPath = new SubwayPath(
            Arrays.asList(lineStationEdgeOfLineFive, lineStationEdgeOfLineTwo));
    }

    @Test
    @DisplayName("추가요금 계산 - 노선별 추가요금이 없는 노선을 11km 이용")
    void calculateFare_IfNotExistFareByLine() {
        when(lineNumberTwo.getFare()).thenReturn(0);
        when(lineNumberFive.getFare()).thenReturn(0);

        assertThat(subwayPath.calculateFare()).isEqualTo(1350);
    }
}