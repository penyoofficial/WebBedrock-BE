package net.penyo.webbedrock.controllor;

import net.penyo.webbedrock.service.BaseService;
import net.penyo.webbedrock.util.ActionProcessor;
import net.penyo.webbedrock.util.Body;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * An entrance where user posts his request.
 *
 * <p>According to RESTful API, I personally decide that parameters can be
 * delivered in following 3 ways:
 * <ul>
 * <li>For key data (such as {@code id} and {@code loginName}): as {@code
 * PathVariable};
 * <li>For private data (such as {@code password} and {@code token}): in {@code
 * RequestHeader};
 * <li>For others: as {@code RequestBody}.
 * </ul>
 *
 * @author Penyo
 */
@CrossOrigin("*")
public interface BaseController<PO, SV extends BaseService<PO>> {

    SV getService();

    default ResponseEntity<Body> insert(PO po) {
        return ActionProcessor.ifResult(getService().insert(po));
    }

    default ResponseEntity<Body> delete(int id) {
        return ActionProcessor.ifResult(getService().delete(id));
    }

    default ResponseEntity<Body> update(PO po) {
        return ActionProcessor.ifResult(getService().update(po));
    }

    default ResponseEntity<Body> query(PO po, String msg) {
        return ResponseEntity.ok(new Body(msg, getService().query(po)));
    }
}
