package nextstep.subway.favorite.ui;

import nextstep.subway.auth.application.InvalidAuthenticationException;
import nextstep.subway.auth.domain.AuthenticationPrincipal;
import nextstep.subway.favorite.application.FavoriteService;
import nextstep.subway.favorite.application.UnauthorizedFavoriteAccessException;
import nextstep.subway.favorite.dto.FavoriteRequest;
import nextstep.subway.favorite.dto.FavoriteResponse;
import nextstep.subway.member.domain.LoginMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    public FavoriteController(final FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ResponseEntity createFavorite(@AuthenticationPrincipal LoginMember member, @RequestBody FavoriteRequest favoriteRequest) {
        FavoriteResponse response = favoriteService.saveFavorite(favoriteRequest, member.getId());
        return ResponseEntity.created(URI.create("/favorites/" + response.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<FavoriteResponse>> findFavorites(@AuthenticationPrincipal LoginMember member) {
        System.out.println(member.toString());
        List<FavoriteResponse> responses = favoriteService.findFavoritesByMemberId(member.getId());
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeFavorite(@AuthenticationPrincipal LoginMember member, @PathVariable Long id) {
            favoriteService.removeFavorite(member.getId(), id);
            return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({InvalidAuthenticationException.class, UnauthorizedFavoriteAccessException.class})
    public ResponseEntity handleInvalidAuthenticationException(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

