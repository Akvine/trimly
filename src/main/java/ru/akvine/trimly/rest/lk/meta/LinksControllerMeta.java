package ru.akvine.trimly.rest.lk.meta;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.akvine.trimly.rest.common.Response;
import ru.akvine.trimly.rest.lk.dto.links.TrimUrlRequest;

@RequestMapping(value = "/links")
public interface LinksControllerMeta {

    @PostMapping(value = "/trim")
    Response toShort(@RequestBody @Valid TrimUrlRequest request);
}
