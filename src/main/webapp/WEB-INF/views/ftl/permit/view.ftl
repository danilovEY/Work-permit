<#-- @ftlvariable name="permitPage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.ShortPermitEntity>" -->
<#-- @ftlvariable name="typeSort" type="java.lang.Integer" -->


<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<#assign EDIT_PERMIT = "Редактирование"/>
<#assign NEED_ACCEPT_PERMIT = "Запрос на согласование"/>
<#assign ACCEPT_PERMIT = "Согласовано"/>
<#assign PERMIT = "Допуск"/>
<#assign CANCELED = "Отменен"/>

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
                                <td class="center">${(permit.endWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${permit.writer.initials!""}</td>
                                <#if permit.responsibleSupervisor?has_content>
                                    <td class="center">${permit.responsibleSupervisor.initials!""}</td>
                                <#else>
                                    <td class="center"></td>
                                </#if>

                                <#if permit.status == NEED_ACCEPT_PERMIT>
                                    <td class="center"><span class="label label-warning">${permit.status}</span></td>
                                <#elseif permit.status == CANCELED>
                                    <td class="center"><span class="label label-important">${permit.status}</span></td>
                                <#elseif permit.status == ACCEPT_PERMIT>
                                    <td class="center"><span class="label label-info">${permit.status}</span></td>
                                <#elseif permit.status == PERMIT>
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

                                    <#if permit.status == ACCEPT_PERMIT>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Допустить" href="<@spring.url relativeUrl="/permit/action/permit?id=${permit.id}"/>">
                                            <i class="halflings-icon white ok"></i>
                                        </a>
                                    </#if>

                                    <#if permit.status == EDIT_PERMIT>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Запрос на согласование" href="<@spring.url relativeUrl="/permit/action/need/accept?id=${permit.id}"/>">
                                            <i class="halflings-icon white question-sign"></i>
                                        </a>

                                        <a class="btn btn-success" style="margin-bottom: 4px;" title="Редактировать" href="<@spring.url relativeUrl="/permit/edit/work?id=${permit.id}"/>">
                                            <i class="halflings-icon white edit"></i>
                                        </a>
                                        <a class="btn btn-danger" style="margin-bottom: 4px;" title="Удалить" href="<@spring.url relativeUrl="/permit/delete?id=${permit.id}"/>">
                                            <i class="halflings-icon white trash"></i>
                                        </a>
                                    </#if>

                                    <#if permit.status == NEED_ACCEPT_PERMIT>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Согласовать" href="<@spring.url relativeUrl="/permit/action/accept?id=${permit.id}"/>">
                                            <i class="halflings-icon white ok-circle"></i>
                                        </a>
                                        <a class="btn btn-danger" style="margin-bottom: 4px;" title="Отменить" href="<@spring.url relativeUrl="/permit/cancel?id=${permit.id}"/>">
                                            <i class="halflings-icon white ban-circle"></i>
                                        </a>
                                    </#if>

                                </td>
                            </tr>
                            <#else>
                            <tr class="even">
                                <td class="center">${permit.serialNumber!""}</td>
                                <td class="center">${permit.name!""}</td>
                                <td class="center">${(permit.startWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${(permit.endWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${permit.writer.initials!""}</td>

                                <#if permit.responsibleSupervisor?has_content>
                                    <td class="center">${permit.responsibleSupervisor.initials!""}</td>
                                <#else>
                                    <td class="center"></td>
                                </#if>

                                <#if permit.status == NEED_ACCEPT_PERMIT>
                                    <td class="center"><span class="label label-warning">${permit.status}</span></td>
                                <#elseif permit.status == CANCELED>
                                    <td class="center"><span class="label label-important">${permit.status}</span></td>
                                <#elseif permit.status == ACCEPT_PERMIT>
                                    <td class="center"><span class="label label-info">${permit.status}</span></td>
                                <#elseif permit.status == PERMIT>
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

                                    <#if permit.status == ACCEPT_PERMIT>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Допустить" href="<@spring.url relativeUrl="/permit/action/permit?id=${permit.id}"/>">
                                            <i class="halflings-icon white ok"></i>
                                        </a>
                                    </#if>

                                    <#if permit.status == EDIT_PERMIT>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Запрос на согласование" href="<@spring.url relativeUrl="/permit/action/need/accept?id=${permit.id}"/>">
                                            <i class="halflings-icon white question-sign"></i>
                                        </a>

                                        <a class="btn btn-success" style="margin-bottom: 4px;" title="Редактировать" href="<@spring.url relativeUrl="/permit/edit/work?id=${permit.id}"/>">
                                            <i class="halflings-icon white edit"></i>
                                        </a>
                                        <a class="btn btn-danger" style="margin-bottom: 4px;" title="Удалить" href="<@spring.url relativeUrl="/permit/delete?id=${permit.id}"/>">
                                            <i class="halflings-icon white trash"></i>
                                        </a>
                                    </#if>

                                    <#if permit.status == NEED_ACCEPT_PERMIT>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Согласовать" href="<@spring.url relativeUrl="/permit/action/accept?id=${permit.id}"/>">
                                            <i class="halflings-icon white ok-circle"></i>
                                        </a>
                                        <a class="btn btn-danger" style="margin-bottom: 4px;" title="Отменить" href="<@spring.url relativeUrl="/permit/cancel?id=${permit.id}"/>">
                                            <i class="halflings-icon white ban-circle"></i>
                                        </a>
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

</@base.override>

<@base.template/>