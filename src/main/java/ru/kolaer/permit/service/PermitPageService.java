package ru.kolaer.permit.service;

import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.dto.ShortPermitDto;
import ru.kolaer.permit.entity.PermitEntity;

/**
 * Created by Danilov on 24.05.2017.
 */
public interface PermitPageService extends BasePageService<PermitEntity> {
    Page<ShortPermitDto> getShortAll(Integer number, Integer pageSize);
}
