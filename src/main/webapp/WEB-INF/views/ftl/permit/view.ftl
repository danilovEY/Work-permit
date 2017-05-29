<#-- @ftlvariable name="permitPage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.ShortPermitEntity>" -->
<#-- @ftlvariable name="permitStatus" type="java.util.List<ru.kolaer.permit.entity.PermitStatusHistoryEntity>" -->


<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "body">
<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white th-large"></i><span class="break"></span>Список нарядов</h2>
        </div>
        <div class="box-content" style="display: block;">
            <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">
                <div class="row-fluid">
                    <div class="span6">
                        <div id="DataTables_Table_0_length" class="dataTables_length">
                            <label>
                                <select size="1" name="DataTables_Table_0_length" aria-controls="DataTables_Table_0">
                                    <option value="${permitPage.pageSize}" selected="selected">${permitPage.pageSize}</option>
                                </select> records per page
                            </label>
                        </div>
                    </div>
                    <div class="span6">
                        <div class="dataTables_filter" id="DataTables_Table_0_filter">
                            <label>Search: <input type="text" aria-controls="DataTables_Table_0"></label>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-bordered bootstrap-datatable" id="DataTables_Table_0"
                       aria-describedby="DataTables_Table_0_info">
                    <thead>
                    <tr role="row">
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">ID наряда
                        </th>
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
                                <td class="center" id="row-id-${permit.id}">${permit.id}</td>
                                <td class="center">${permit.serialNumber!""}</td>
                                <td class="center">${permit.name!""}</td>
                                <td class="center">${(permit.startWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${(permit.endWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${permit.writer.initials!""}</td>
                                <td class="center">${permit.responsibleSupervisor.initials!""}</td>
                                <#list permitStatus as val>
                                    <#if val.permitId == permit.id>
                                        <td class="center">${val.status!""}</td>
                                        <#break>
                                    </#if>
                                </#list>
                                <td class="center">
                                    <a class="btn btn-success" title="Редактировать" href="<@spring.url relativeUrl="/permit/edit/work?id=${permit.id}"/>">
                                        <i class="halflings-icon white edit"></i>
                                    </a>
                                </td>
                            </tr>
                            <#else>
                            <tr class="even">
                                <td class="center" id="row-id-${permit.id}">${permit.id}</td>
                                <td class="center">${permit.serialNumber!""}</td>
                                <td class="center">${permit.name!""}</td>
                                <td class="center">${(permit.startWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${(permit.endWork!"")?string["dd.MM.yyyy hh:mm"]}</td>
                                <td class="center">${permit.writer.initials!""}</td>
                                <td class="center">${permit.responsibleSupervisor.initials!""}</td>
                                <#list permitStatus as val>
                                    <#if val.permitId == permit.id>
                                        <td class="center">${val.status!""}</td>
                                        <#break>
                                    </#if>
                                </#list>
                                <td class="center">
                                    <a class="btn btn-success" title="Редактировать" href="<@spring.url relativeUrl="/permit/edit/work?id=${permit.id}"/>">
                                        <i class="halflings-icon white edit"></i>
                                    </a>
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