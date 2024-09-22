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
 * <li>For search parameters: as {@code RequestParam};
 * <li>For others: as {@code RequestBody}.
 * </ul>
 *
 * @author Penyo
 */
@CrossOrigin("*")
public interface BaseController<PO, SV extends BaseService<PO>> {

    SV getService();

    default ResponseEntity<Body> add(PO po) {
        return ActionProcessor.ifResultOk(getService().add(po));
    }

    default ResponseEntity<Body> delete(int id) {
        return ActionProcessor.ifResultOk(getService().delete(id));
    }

    default ResponseEntity<Body> update(PO po) {
        return ActionProcessor.ifResultOk(getService().update(po));
    }

    default ResponseEntity<Body> query(PO po, String okMsg, String failMsg) {
        return ActionProcessor.ifResultExist(getService().search(po), okMsg, failMsg);
    }

    default ResponseEntity<Body> search(PO po) {
        return ResponseEntity.ok(new Body("查询成功", getService().search(po)));
    }
}
