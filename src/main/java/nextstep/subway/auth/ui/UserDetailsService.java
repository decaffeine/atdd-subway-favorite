package nextstep.subway.auth.ui;

import nextstep.subway.member.domain.LoginMember;

public interface UserDetailsService {
    LoginMember loadUserByUsername(String email);
}
