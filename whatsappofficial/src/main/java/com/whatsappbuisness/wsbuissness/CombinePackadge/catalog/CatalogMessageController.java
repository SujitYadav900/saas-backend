package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;


import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("api/catalogues")
public class CatalogMessageController {

    private final CatalogMessageService catalogMessageService;
    private static final Logger logger= LoggerFactory.getLogger(CatalogMessageController.class);

    public CatalogMessageController(CatalogMessageService catalogMessageService) {
        this.catalogMessageService = catalogMessageService;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> save(@RequestBody CatalogMessageDao catalogMessageDao, HttpServletRequest httpServletRequest) throws Exception {
        return catalogMessageService.save(catalogMessageDao, httpServletRequest);
    }

    @PostMapping("/save")
    public ResponseEntity<?> panel(@RequestBody CatalogMessageDao catalogMessageDao, Authentication authentication) {
        return catalogMessageService.saveFromPanel(catalogMessageDao, authentication);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody CatalogMessageDao catalogMessageDao, Authentication authentication) {
        return catalogMessageService.update(catalogMessageDao, authentication);
    }

    @GetMapping("/get")
    public PaginationDao<?> get(@RequestParam("offset") int offset,
                                @RequestParam("limit") int limit, Authentication authentication) {
        return catalogMessageService.get(authentication, offset, limit);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam("id") String id) {
        logger.info("ID {}",id);
        return catalogMessageService.deleteById(id);
    }


    @GetMapping("/getById")
    public CatalogMessageDao getById(@RequestParam("id") String id) throws Exception {
        logger.info("CatalogMessageController getById {}",id);
        return catalogMessageService.getById(id);
    }


}
