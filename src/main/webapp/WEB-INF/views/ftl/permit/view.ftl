<#-- @ftlvariable name="permitPage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.ShortPermitEntity>" -->
<#-- @ftlvariable name="typeSort" type="java.lang.Integer" -->


<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

<#assign EDIT_PERMIT_STATUS = "Редактирование"/>
<#assign NEED_APPROVE_PERMIT_STATUS = "Запрос на согласование"/>
<#assign APPROVE_STATUS = "Согласовано"/>
<#assign PERMIT_STATUS = "Допуск"/>
<#assign CANCELED_STATUS = "Отменен"/>
<#assign WORKING_STATUS = "В работе"/>
<#assign OVERDUE_STATUS = "Просрочен"/>
<#assign DELETED_STATUS = "Удален"/>
<#assign END_STATUS = "Завершен"/>

<#assign ROLE_DB_ADMIN = "hasRole('ROLE_DB_ADMIN')"/>
<#assign ROLE_USER = "hasRole('ROLE_USER')"/>
<#assign ROLE_APPROVE = "hasRole('ROLE_APPROVE')"/>
<#assign ROLE_PERMIT = "hasRole('ROLE_PERMIT')"/>

<@base.override "title">
<title>Наряды</title>
</@base.override>

<@base.override "body">
<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white th-large"></i><span class="break"></span>Список нарядов</h2>
        </div>
        <div class="box-content" style="display: block;">

            <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">
                <div class="row-fluid">
                    <div class="span4">
                        <div id="sort">
                            <div class="control-group">
                                <label class="control-label" for="sort">Сотрировать по:</label>
                                <div class="controls">
                                    <select size="1" class="span12" id="sort" name="sort" onchange="self.location='<@spring.url relativeUrl="/permit"/>?sort='+this.selectedIndex">
                                        <option value="0" <#if typeSort == 0>selected="selected"</#if>>Дате выдачи наряда</option>
                                        <option value="1" <#if typeSort == 1>selected="selected"</#if>>Дате начала работы</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="span6">
                        <div id="search">
                            <div class="control-group">
                                <label class="control-label" for="search">Поиск:</label>
                                <div class="controls">
                                    <div class="input-append span8">
                                        <input id="search" type="text" class="span12" name="search"><button class="btn btn-primary" style="padding-bottom: 2px;" type="button">Найти</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="span2">
                        <div class="control-group">
                            <a class="btn btn-info" style="margin-bottom: 10px;" href="<@spring.url relativeUrl="/permit/add/work"/>">
                                <i class="halflings-icon plus white"></i> Создать наряд
                            </a>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-bordered bootstrap-datatable" id="DataTables_Table_0">
                    <thead>
                    <tr role="row">
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">№ наряда
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Наименование
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Начало работ
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Окончание работ
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Выдающий
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Руководитель
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Статус
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Действие
                        </th>
                    </tr>
                    </thead>
                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <#list permitPage.data as permit>
                            <#if permit_index % 2 == 0>
                            <tr class="odd">
                                <td class="center">${permit.serialNumber!""}</td>
                                <td class="center">${permit.name!""}</td>
                                <td class="center">${(permit.startWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${(permit.extendedPermit!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${permit.writer.initials!""}</td>
                                <#if permit.responsibleSupervisor?has_content>
                                    <td class="center">${permit.responsibleSupervisor.initials!""}</td>
                                <#else>
                                    <td class="center"></td>
                                </#if>

                                <#if permit.status == NEED_APPROVE_PERMIT_STATUS>
                                    <td class="center"><span class="label label-warning">${permit.status}</span></td>
                                <#elseif permit.status == CANCELED_STATUS>
                                    <td class="center"><span class="label label-important">${permit.status}</span></td>
                                <#elseif permit.status == APPROVE_STATUS>
                                    <td class="center"><span class="label label-info">${permit.status}</span></td>
                                <#elseif permit.status == PERMIT_STATUS>
                                    <td class="center"><span class="label label-success">${permit.status}</span></td>
                                <#elseif permit.status == WORKING_STATUS>
                                    <td class="center"><span class="label label-success">${permit.status}</span></td>
                                <#elseif permit.status == OVERDUE_STATUS>
                                    <td class="center"><span class="label label-important">${permit.status}</span></td>
                                <#elseif permit.status == DELETED_STATUS>
                                    <td class="center"><span class="label label-inverse">${permit.status}</span></td>
                                <#elseif permit.status == END_STATUS>
                                    <td class="center"><span class="label label-success">${permit.status}</span></td>
                                <#else>
                                    <td class="center"><span class="label">${permit.status}</span></td>
                                </#if>

                                <td class="center">
                                    <a class="btn btn-success" style="margin-bottom: 4px;" title="Просмотр" href="<@spring.url relativeUrl="/permit/view/work?id=${permit.id}"/>">
                                        <i class="halflings-icon white eye-open"></i>
                                    </a>

                                    <a class="btn btn-success" style="margin-bottom: 4px;" title="Получить бланк" href="<@spring.url relativeUrl="/permit/download/excel?id=${permit.id}"/>">
                                        <i class="halflings-icon white download-alt"></i>
                                    </a>

                                    <#if permit.status == WORKING_STATUS>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Завершить наряд" id="end-but-${permit.id}" href="<@spring.url relativeUrl="/permit/action/end?id=${permit.id}"/>">
                                            <i class="halflings-icon white ok"></i>
                                        </a>
                                    </#if>

                                    <#if permit.status == APPROVE_STATUS>
                                        <@security.authorize access=ROLE_PERMIT>
                                            <a class="btn btn-info" style="margin-bottom: 4px;" title="Допустить" id="permit-but-${permit.id}" href="#">
                                                <i class="halflings-icon white thumbs-up"></i>
                                            </a>
                                        </@security.authorize>
                                    </#if>

                                    <#if permit.status == EDIT_PERMIT_STATUS>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Запрос на согласование" id="need-approve-but-${permit.id}" href="#">
                                            <i class="halflings-icon white question-sign"></i>
                                        </a>
                                        <a class="btn btn-success" style="margin-bottom: 4px;" title="Редактировать" href="<@spring.url relativeUrl="/permit/edit/work?id=${permit.id}"/>">
                                            <i class="halflings-icon white edit"></i>
                                        </a>
                                        <a class="btn btn-danger" style="margin-bottom: 4px;" title="Удалить" id="delete-but-${permit.id}" href="#">
                                            <i class="halflings-icon white trash"></i>
                                        </a>
                                    </#if>

                                    <#if permit.status == NEED_APPROVE_PERMIT_STATUS>
                                        <@security.authorize access=ROLE_APPROVE>
                                            <a class="btn btn-info" style="margin-bottom: 4px;" title="Согласовать" id="approve-but-${permit.id}" href="#">
                                                <i class="halflings-icon white ok-circle"></i>
                                            </a>
                                            <a class="btn btn-danger" style="margin-bottom: 4px;" title="Отменить" id="cancel-but-${permit.id}" href="#">
                                                <i class="halflings-icon white ban-circle"></i>
                                            </a>
                                        </@security.authorize>
                                    </#if>

                                </td>
                            </tr>
                            <#else>
                            <tr class="even">
                                <td class="center">${permit.serialNumber!""}</td>
                                <td class="center">${permit.name!""}</td>
                                <td class="center">${(permit.startWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${(permit.extendedPermit!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${permit.writer.initials!""}</td>

                                <#if permit.responsibleSupervisor?has_content>
                                    <td class="center">${permit.responsibleSupervisor.initials!""}</td>
                                <#else>
                                    <td class="center"></td>
                                </#if>

                                <#if permit.status == NEED_APPROVE_PERMIT_STATUS>
                                    <td class="center"><span class="label label-warning">${permit.status}</span></td>
                                <#elseif permit.status == CANCELED_STATUS>
                                    <td class="center"><span class="label label-important">${permit.status}</span></td>
                                <#elseif permit.status == APPROVE_STATUS>
                                    <td class="center"><span class="label label-info">${permit.status}</span></td>
                                <#elseif permit.status == PERMIT_STATUS>
                                    <td class="center"><span class="label label-success">${permit.status}</span></td>
                                <#elseif permit.status == WORKING_STATUS>
                                    <td class="center"><span class="label label-success">${permit.status}</span></td>
                                <#elseif permit.status == OVERDUE_STATUS>
                                    <td class="center"><span class="label label-important">${permit.status}</span></td>
                                <#elseif permit.status == DELETED_STATUS>
                                    <td class="center"><span class="label label-inverse">${permit.status}</span></td>
                                <#elseif permit.status == END_STATUS>
                                    <td class="center"><span class="label label-success">${permit.status}</span></td>
                                <#else>
                                    <td class="center"><span class="label">${permit.status}</span></td>
                                </#if>

                                <td class="center">
                                    <a class="btn btn-success" style="margin-bottom: 4px;" title="Просмотр" href="<@spring.url relativeUrl="/permit/view/work?id=${permit.id}"/>">
                                        <i class="halflings-icon white eye-open"></i>
                                    </a>

                                    <a class="btn btn-success" style="margin-bottom: 4px;" title="Получить бланк" href="<@spring.url relativeUrl="/permit/download/excel?id=${permit.id}"/>">
                                        <i class="halflings-icon white download-alt"></i>
                                    </a>

                                    <#if permit.status == WORKING_STATUS>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Завершить наряд" id="end-but-${permit.id}" href="<@spring.url relativeUrl="/permit/action/end?id=${permit.id}"/>">
                                            <i class="halflings-icon white ok"></i>
                                        </a>
                                    </#if>

                                    <#if permit.status == APPROVE_STATUS>
                                        <@security.authorize access=ROLE_PERMIT>
                                            <a class="btn btn-info" style="margin-bottom: 4px;" title="Допустить" id="permit-but-${permit.id}" href="#">
                                                <i class="halflings-icon white thumbs-up"></i>
                                            </a>
                                        </@security.authorize>
                                    </#if>

                                    <#if permit.status == EDIT_PERMIT_STATUS>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Запрос на согласование" id="need-approve-but-${permit.id}" href="#">
                                            <i class="halflings-icon white question-sign"></i>
                                        </a>
                                        <a class="btn btn-success" style="margin-bottom: 4px;" title="Редактировать" href="<@spring.url relativeUrl="/permit/edit/work?id=${permit.id}"/>">
                                            <i class="halflings-icon white edit"></i>
                                        </a>
                                        <a class="btn btn-danger" style="margin-bottom: 4px;" title="Удалить" id="delete-but-${permit.id}" href="#">
                                            <i class="halflings-icon white trash"></i>
                                        </a>
                                    </#if>

                                    <#if permit.status == NEED_APPROVE_PERMIT_STATUS>
                                        <@security.authorize access=ROLE_APPROVE>
                                            <a class="btn btn-info" style="margin-bottom: 4px;" title="Согласовать" id="approve-but-${permit.id}" href="#">
                                                <i class="halflings-icon white ok-circle"></i>
                                            </a>
                                            <a class="btn btn-danger" style="margin-bottom: 4px;" title="Отменить" id="cancel-but-${permit.id}" href="#">
                                                <i class="halflings-icon white ban-circle"></i>
                                            </a>
                                        </@security.authorize>
                                    </#if>
                                </td>
                            </tr>
                            </#if>
                        </#list>
                    </tbody>
                </table>

                <div class="row-fluid">
                    <div class="span12">
                        <div class="dataTables_info" id="DataTables_Table_0_info">
                            Страница ${permitPage.number} из ${permitPage.total} по ${permitPage.pageSize} нарядов.
                        </div>
                    </div>
                    <div class="span12 center">
                        <div class="dataTables_paginate paging_bootstrap pagination">
                            <ul>
                                <#if permitPage.number != 1 >
                                    <li class="prev"><a href="<@spring.url relativeUrl="/permit?page=${permitPage.number-1}"/>">← Previous</a></li>
                                <#else>
                                    <li class="prev disabled"><a href="#">← Previous</a></li>
                                </#if>
                                <#list 1..permitPage.total as i>
                                    <#if i == permitPage.number>
                                        <li class="active"><a href="#">${i}</a></li>
                                    <#else>
                                        <li><a href="<@spring.url relativeUrl="/permit?page=${i}"/>">${i}</a></li>
                                    </#if>
                                </#list>
                                <#if permitPage.number != permitPage.total >
                                    <li class="next"><a href="<@spring.url relativeUrl="/permit?page=${permitPage.number+1}"/>">Next → </a></li>
                                <#else>
                                    <li class="next disabled"><a href="#">Next → </a></li>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/span-->
</div>

<#list permitPage.data as permit>
    <#if permit.status == EDIT_PERMIT_STATUS>
        <#-- Запрос на удаление -->
        <div class="modal hide fade" id="delete-${permit.id}">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Запрос на удаление</h3>
            </div>
            <div class="modal-body">
                <p>Вы действительно хотите удалить наряд: "${permit.serialNumber}"?</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn" data-dismiss="modal">Отмена</a>
                <a href="<@spring.url relativeUrl="/permit/delete?id=${permit.id}"/>" class="btn btn-primary">Удалить</a>
            </div>
        </div>
        <script>
            $('#delete-but-${permit.id}').click(function(e){
                e.preventDefault();
                $('#delete-${permit.id}').modal('show');
            });
        </script>

        <#-- Запрос на согласование -->
        <div class="modal hide fade" id="need-approve-${permit.id}">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Запрос на согласование</h3>
            </div>
            <div class="modal-body">
                <p>Вы действительно хотите отправить наряд "${permit.serialNumber}" на согласование?</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn" data-dismiss="modal">Отмена</a>
                <a href="<@spring.url relativeUrl="/permit/action/need/approve?id=${permit.id}"/>" class="btn btn-primary">Подтвердить</a>
            </div>
        </div>
        <script>
            $('#need-approve-but-${permit.id}').click(function(e){
                e.preventDefault();
                $('#need-approve-${permit.id}').modal('show');
            });
        </script>
    </#if>

    <#if permit.status == NEED_APPROVE_PERMIT_STATUS>
        <#-- Согласование -->
        <div class="modal hide fade" id="approve-${permit.id}">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Согласование</h3>
            </div>
            <div class="modal-body">
                <p>Вы действительно хотите согласовать наряд: "${permit.serialNumber}"?</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn" data-dismiss="modal">Отмена</a>
                <a href="<@spring.url relativeUrl="/permit/action/approve?id=${permit.id}"/>" class="btn btn-primary">Подтвердить</a>
            </div>
        </div>
        <script>
            $('#approve-but-${permit.id}').click(function(e){
                e.preventDefault();
                $('#approve-${permit.id}').modal('show');
            });
        </script>

        <#-- Отмена -->
        <div class="modal hide fade" id="cancel-${permit.id}">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Отмена</h3>
            </div>
            <div class="modal-body">
                <p>Вы действительно хотите отменить наряд: "${permit.serialNumber}"?</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn" data-dismiss="modal">Отмена</a>
                <a href="<@spring.url relativeUrl="/permit/action/cancel?id=${permit.id}"/>" class="btn btn-primary">Подтвердить</a>
            </div>
        </div>
        <script>
            $('#cancel-but-${permit.id}').click(function(e){
                e.preventDefault();
                $('#cancel-${permit.id}').modal('show');
            });
        </script>
    </#if>

    <#if permit.status == APPROVE_STATUS>
        <#-- Допуск -->
        <div class="modal hide fade" id="permit-${permit.id}">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Допуск</h3>
            </div>
            <div class="modal-body">
                <p>Вы действительно хотите допустить наряд: "${permit.serialNumber}"?</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn" data-dismiss="modal">Отмена</a>
                <a href="<@spring.url relativeUrl="/permit/action/permit?id=${permit.id}"/>" class="btn btn-primary">Подтвердить</a>
            </div>
        </div>
        <script>
            $('#permit-but-${permit.id}').click(function(e){
                e.preventDefault();
                $('#permit-${permit.id}').modal('show');
            });
        </script>
    </#if>
</#list>

</@base.override>

<@base.template/>