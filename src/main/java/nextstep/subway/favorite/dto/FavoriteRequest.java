package nextstep.subway.favorite.dto;

public class FavoriteRequest {
    private Long source;
    private Long target;
    private Long memberId;

    public FavoriteRequest(Long source, Long target, Long memberId) {
        this.source = source;
        this.target = target;
        this.memberId = memberId;
    }
}
