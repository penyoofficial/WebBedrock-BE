package net.penyo.webbedrock.controllor;

import net.penyo.webbedrock.po.Neko;
import net.penyo.webbedrock.service.NekoService;
import net.penyo.webbedrock.util.ResponseJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * An entrance where user posts his request about cat.
 *
 * @author Penyo
 */
@RestController
@RequestMapping("/neko")
public class NekoController extends BaseController {

    private final NekoService nekoService;

    @Autowired
    private NekoController(NekoService nekoService) {
        this.nekoService = nekoService;
    }

    @PutMapping("/insert")
    public ResponseEntity<Boolean> insert(@RequestBody Neko neko) {
        return ResponseEntity.ok(nekoService.insert(neko));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        return ResponseEntity.ok(nekoService.delete(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody Neko neko) {
        return ResponseEntity.ok(nekoService.update(neko));
    }

    @GetMapping("/query")
    public ResponseEntity<String> query(@RequestBody Optional<Neko> neko) {
        return ResponseJson.ok(nekoService.query(neko.orElseGet(Neko::new)));
    }
}
