<#-- @ftlvariable name="departmentPage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.DepartmentEntity>" -->


<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign ROLE_DB_ADMIN = "hasRole('ROLE_DB_ADMIN')"/>

<@base.override "title">
<title>Подразделения</title>
</@base.override>

<@base.override "body">

<div class="row-fluid" xmlns="http://www.w3.org/1999/html">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white th-large"></i><span class="break"></span>Список подразделений</h2>
        </div>
        <div class="box-content" style="display: block;">
            <@security.authorize access=ROLE_DB_ADMIN>
                <div class="control-group">
                    <div class="pull-right">
                        <a class="btn btn-info" style="margin-bottom: 10px;" href="<@spring.url relativeUrl="/department/add"/>">
                            <i class="halflings-icon plus white"></i> Добавить подразделение
                        </a>
                    </div>
                </div>
            </@security.authorize>

            <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">
                <#--<div class="row-fluid">-->
                    <#--<div class="span6">-->
                        <#--<div id="DataTables_Table_0_length" class="dataTables_length">-->
                            <#--<label>-->
                                <#--<select size="1" name="DataTables_Table_0_length" aria-controls="DataTables_Table_0">-->
                                    <#--<option value="${departmentPage.pageSize}" selected="selected">${departmentPage.pageSize}</option>-->
                                <#--</select> records per page-->
                            <#--</label>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="span6">-->
                        <#--<div class="dataTables_filter" id="DataTables_Table_0_filter">-->
                            <#--<label>Search: <input type="text" aria-controls="DataTables_Table_0"></label>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->
                <table class="table table-striped table-bordered bootstrap-datatable" id="DataTables_Table_0"
                       aria-describedby="DataTables_Table_0_info">
                    <thead>
                    <tr role="row">
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Наименование
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Сокращенное наименование
                        </th>
                        <@security.authorize access=ROLE_DB_ADMIN>
                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Действие
                            </th>
                        </@security.authorize>
                    </tr>
                    </thead>
                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <#list departmentPage.data as dep>
                            <#if dep_index % 2 == 0>
                            <tr class="odd">
                                <td class="center" id="row-name-${dep.id}">${dep.name}</td>
                                <td class="center" id="row-abb-${dep.id}">${dep.abbreviatedName}</td>
                                <@security.authorize access=ROLE_DB_ADMIN>
                                    <td class="center">
                                        <a class="btn btn-success" title="Редактировать" href="<@spring.url relativeUrl="/department/edit?id=${dep.id}"/>">
                                            <i class="halflings-icon white edit"></i>
                                        </a>
                                        <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/department/delete?id=${dep.id}"/>">
                                            <i class="halflings-icon white trash"></i>
                                        </a>
                                    </td>
                                </@security.authorize>
                            </tr>
                            <#else>
                            <tr class="even">
                                <td class="center" id="row-name-${dep.id}">${dep.name}</td>
                                <td class="center" id="row-abb-${dep.id}">${dep.abbreviatedName}</td>
                                <@security.authorize access=ROLE_DB_ADMIN>
                                    <td class="center">
                                        <a class="btn btn-success" title="Редактировать" href="<@spring.url relativeUrl="/department/edit?id=${dep.id}"/>">
                                            <i class="halflings-icon white edit"></i>
                                        </a>
                                        <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/department/delete?id=${dep.id}"/>">
                                            <i class="halflings-icon white trash"></i>
                                        </a>
                                    </td>
                                </@security.authorize>
                            </tr>
                            </#if>
                        </#list>
                    </tbody>
                </table>

                <div class="row-fluid">
                    <div class="span6">
                        <div class="dataTables_info" id="DataTables_Table_0_info">
                            Страница ${departmentPage.number} из ${departmentPage.total} по ${departmentPage.pageSize} подразделений.
                        </div>
                    </div>

                    <div class="span12 center">
                        <div class="dataTables_paginate paging_bootstrap pagination">
                            <ul>
                                <#if departmentPage.number != 1 >
                                    <li class="prev"><a href="<@spring.url relativeUrl="/department?page=${departmentPage.number-1}"/>">← Previous</a></li>
                                <#else>
                                    <li class="prev disabled"><a href="#">← Previous</a></li>
                                </#if>
                                <#list 1..departmentPage.total as i>
                                    <#if i == departmentPage.number>
                                        <li class="active"><a href="#">${i}</a></li>
                                    <#else>
                                        <li><a href="<@spring.url relativeUrl="/department?page=${i}"/>">${i}</a></li>
                                    </#if>
                                </#list>
                                <#if departmentPage.number != departmentPage.total >
                                    <li class="next"><a href="<@spring.url relativeUrl="/department?page=${departmentPage.number+1}"/>">Next → </a></li>
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