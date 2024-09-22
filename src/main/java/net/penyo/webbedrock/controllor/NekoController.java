package net.penyo.webbedrock.controllor;

import jakarta.validation.constraints.Pattern;
import net.penyo.webbedrock.po.Neko;
import net.penyo.webbedrock.service.NekoService;
import net.penyo.webbedrock.util.ActionProcessor;
import net.penyo.webbedrock.util.Body;
import net.penyo.webbedrock.util.Pinia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Body> add(@RequestBody @Validated Neko neko) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(Pinia.TOKEN.get());
        if (barrier != null) return barrier;

        return BaseController.super.add(neko);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Body> delete(@PathVariable @Pattern(regexp = "^\\d+$") String id) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(Pinia.TOKEN.get());
        if (barrier != null) return barrier;

        return BaseController.super.delete(Integer.parseInt(id));
    }

    @PutMapping("/")
    public ResponseEntity<Body> update(@RequestBody @Validated Neko neko) {
        ResponseEntity<Body> barrier = ActionProcessor.onlyAdminCanDo(Pinia.TOKEN.get());
        if (barrier != null) return barrier;

        return BaseController.super.update(neko);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Body> query(@PathVariable @Pattern(regexp = "^\\d+$") String id) {
        return BaseController.super.query(new Neko(Integer.parseInt(id)), "查询成功", "查询失败");
    }

    @GetMapping("/batch")
    public ResponseEntity<Body> search(@RequestParam Neko neko) {
        return BaseController.super.search(neko);
    }
}
