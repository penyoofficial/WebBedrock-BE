package net.penyo.webbedrock.controllor;

import jakarta.validation.constraints.Pattern;
import net.penyo.webbedrock.permission.Filter;
import net.penyo.webbedrock.po.Neko;
import net.penyo.webbedrock.service.NekoService;
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
    public ResponseEntity<Body> insert(@RequestHeader String token, @RequestBody Neko neko) {
        ResponseEntity<Body> barrier = Filter.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return insert(neko);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Body> delete(@RequestHeader String token, @PathVariable @Pattern(regexp = "^\\d+$") String id) {
        ResponseEntity<Body> barrier = Filter.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return delete(Integer.parseInt(id));
    }

    @PutMapping("/")
    public ResponseEntity<Body> update(@RequestHeader String token, @RequestBody Neko neko) {
        ResponseEntity<Body> barrier = Filter.onlyAdminCanDo(token);
        if (barrier != null) return barrier;

        return update(neko);
    }

    @GetMapping("/")
    public ResponseEntity<Body> query(@RequestHeader String token, @RequestBody Optional<Neko> neko) {
        ResponseEntity<Body> barrier = Filter.onlyLoggedInCanDo(token);
        if (barrier != null) return barrier;

        return query(neko.orElseGet(Neko::new), "查询成功");
    }
}
