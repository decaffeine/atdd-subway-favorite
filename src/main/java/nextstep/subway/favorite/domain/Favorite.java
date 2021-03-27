package nextstep.subway.favorite.domain;

import nextstep.subway.common.BaseEntity;
import nextstep.subway.station.domain.Station;

import javax.persistence.*;

@Entity
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Station source;

    @ManyToOne
    private Station target;

    private Long memberId;

    public Favorite() {}

    public Favorite(Station source, Station target, Long memberId) {
        this.source = source;
        this.target = target;
        this.memberId = memberId;
    }

}
