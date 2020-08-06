package wooteco.subway.maps.map.documentation;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import com.google.common.collect.Lists;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.web.context.WebApplicationContext;
import wooteco.security.core.TokenResponse;
import wooteco.subway.common.documentation.Documentation;
import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.domain.PathType;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.maps.map.ui.MapController;
import wooteco.subway.maps.station.dto.StationResponse;
import wooteco.subway.members.favorite.application.FavoriteService;
import wooteco.subway.members.favorite.dto.FavoriteResponse;
import wooteco.subway.members.favorite.ui.FavoriteController;

@WebMvcTest(controllers = MapController.class)
public class PathDocumentation extends Documentation {

    @Autowired
    private MapController mapController;

    @MockBean
    private MapService mapService;

    protected TokenResponse tokenResponse;

    @BeforeEach
    public void setUp(
        WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        super.setUp(context, restDocumentation);
        tokenResponse = new TokenResponse("token");
    }

    @Test
    void calculatePath() {
        StationResponse 신정역 = new StationResponse(1L, "신정역", LocalDateTime.now(), LocalDateTime.now());
        StationResponse 까치산역 = new StationResponse(2L, "까치산역", LocalDateTime.now(), LocalDateTime.now());
        StationResponse 신정네거리역 = new StationResponse(3L, "신정네거리역", LocalDateTime.now(), LocalDateTime.now());
        PathResponse pathResponse = new PathResponse(Arrays.asList(신정역, 까치산역, 신정네거리역), 8, 500, 1250);
        when(mapService.findPath(any(), any(), any())).thenReturn(pathResponse);

        given().log().all().
            header("Authorization", "Bearer " + tokenResponse.getAccessToken()).
            accept(MediaType.APPLICATION_JSON_VALUE).
        when().
            get("/paths?source={sourceId}&target={targetId}&type={type}", 1L, 2L, "DISTANCE").
            then().
            log().all().
            apply(document("path/findPath",
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization").description("Bearer auth credentials")),
                responseFields(
                    fieldWithPath("stations").type(JsonFieldType.ARRAY).description("경로상의 역 목록"),
                    fieldWithPath("stations.[].id").type(JsonFieldType.NUMBER).description("역 아이디"),
                    fieldWithPath("stations.[].name").type(JsonFieldType.STRING).description("역 이름"),
                    fieldWithPath("duration").type(JsonFieldType.NUMBER).description("소요 시간"),
                    fieldWithPath("distance").type(JsonFieldType.NUMBER).description("이동 거리"),
                    fieldWithPath("fare").type(JsonFieldType.NUMBER).description(
                        "최단거리 경로이면 DISTANCE, 최소시간 경로이면 DURATION")
                )
            )).
            extract();
    }
}
