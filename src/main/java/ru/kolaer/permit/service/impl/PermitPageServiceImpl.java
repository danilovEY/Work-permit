package ru.kolaer.permit.service.impl;

import org.springframework.stereotype.Service;
import ru.kolaer.permit.dao.BasePageDao;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.dto.ShortPermitDto;
import ru.kolaer.permit.entity.PermitEntity;
import ru.kolaer.permit.service.BasePageServiceAbstract;
import ru.kolaer.permit.service.PermitPageService;

import java.util.stream.Collectors;

/**
 * Created by Danilov on 24.05.2017.
 */
@Service
public class PermitPageServiceImpl extends BasePageServiceAbstract<PermitEntity> implements PermitPageService {

    private final PermitPageDao dao;

    public PermitPageServiceImpl(PermitPageDao dao) {
        super(dao);
        this.dao = dao;
    }

    @Override
    public Page<ShortPermitDto> getShortAll(Integer number, Integer pageSize) {
        final Page<PermitEntity> all = this.dao.findAll(number, pageSize, false);

        final Page<ShortPermitDto> shortPermitDtoPage =
                new Page<ShortPermitDto>(all.getNumber(),
                        all.getTotal(),
                        all.getPageSize(),
                        all.getData().stream().map(this::convertToShort).collect(Collectors.toList()));

        return shortPermitDtoPage;
    }

    private ShortPermitDto convertToShort(PermitEntity permitEntity) {
        ShortPermitDto shortPermitDto = new ShortPermitDto();
        shortPermitDto.setId(permitEntity.getId());
        shortPermitDto.setName(permitEntity.getName());
        return shortPermitDto;
    }

}
