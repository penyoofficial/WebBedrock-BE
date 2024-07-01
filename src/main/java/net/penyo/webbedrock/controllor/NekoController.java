package net.penyo.webbedrock.controllor;

import jakarta.validation.constraints.Pattern;
import net.penyo.webbedrock.po.Neko;
import net.penyo.webbedrock.service.NekoService;
import net.penyo.webbedrock.util.ActionProcessor;
import net.penyo.webbedrock.util.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * An entrance where user posts his request about cat.
 *
 * @author Penyo
 */
@RestController
@RequestMapping("/neko")
@Validated
public class NekoController implements BaseController<Neko, NekoService> {

    protected NekoController() {
    }

    private NekoService nekoService;

    @Autowired
    private NekoController(NekoService nekoService) {
        this.nekoService = nekoService;
    }

    @Override
    public NekoService getService() {
        return nekoService;
    }

    @PostMapping("/")
    public ResponseEntity<Body> insert(@RequestHeader("Authorization") String token, @RequestBody Neko neko) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return insert(neko);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Body> delete(@RequestHeader("Authorization") String token, @PathVariable @Pattern(regexp = "^\\d+$") String id) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return delete(Integer.parseInt(id));
    }

    @PutMapping("/")
    public ResponseEntity<Body> update(@RequestHeader("Authorization") String token, @RequestBody Neko neko) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return update(neko);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Body> query(@RequestHeader("Authorization") String token, @PathVariable @Pattern(regexp = "^\\d+$") String id) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyLoggedInCanDo(token);
        if (barrier != null) return barrier;

        return query(new Neko(Integer.parseInt(id)), "查询成功");
    }

    @PostMapping("/q")
    public ResponseEntity<Body> query(@RequestHeader("Authorization") String token, @RequestBody Optional<Neko> neko) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyLoggedInCanDo(token);
        if (barrier != null) return barrier;

        return query(neko.orElseGet(Neko::new), "查询成功");
    }
}
