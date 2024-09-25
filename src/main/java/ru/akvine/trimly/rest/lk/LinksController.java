package ru.akvine.trimly.rest.lk;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.akvine.trimly.rest.common.Response;
import ru.akvine.trimly.rest.lk.converters.LinkConverter;
import ru.akvine.trimly.rest.lk.dto.links.TrimUrlRequest;
import ru.akvine.trimly.rest.lk.meta.LinksControllerMeta;
import ru.akvine.trimly.rest.lk.validator.LKLinkValidator;
import ru.akvine.trimly.services.LinkService;
import ru.akvine.trimly.services.domain.LinkModel;
import ru.akvine.trimly.services.dto.CreateLink;

@RestController
@RequiredArgsConstructor
public class LinksController implements LinksControllerMeta {
    private final LKLinkValidator lkLinkValidator;
    private final LinkService linkService;
    private final LinkConverter linkConverter;

    @Override
    public Response toShort(@RequestBody @Valid TrimUrlRequest request) {
        lkLinkValidator.verifyTrimlyUrlRequest(request);
        CreateLink createLink = linkConverter.convertToCreateLink(request);
        LinkModel createdLink = linkService.createLink(createLink);
        return linkConverter.convertToLinkResponse(createdLink.getShortLink());
    }
}
