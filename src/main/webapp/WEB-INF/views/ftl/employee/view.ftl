<#-- @ftlvariable name="employeesPage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.EmployeeEntity>" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign ROLE_DB_ADMIN = "hasRole('ROLE_DB_ADMIN')"/>

<@base.override "title">
<title>Сотрудники</title>
</@base.override>

<@base.override "body">
<div class="row-fluid">
<div class="box span12">
    <div class="box-header" data-original-title="">
        <h2><i class="halflings-icon white user"></i><span class="break"></span>Список сотрудников</h2>
    </div>
<div class="box-content" style="display: block;">
    <@security.authorize access=ROLE_DB_ADMIN>
        <div class="control-group">
            <div class="pull-right">
                <a class="btn btn-info" style="margin-bottom: 10px;" href="<@spring.url relativeUrl="/employee/add"/>">
                    <i class="halflings-icon plus white"></i> Добавить сотрудника
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
                            <#--<option value="${employeesPage.pageSize}" selected="selected">${employeesPage.pageSize}</option>-->
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
                <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                    colspan="1" aria-sort="ascending" aria-label="Username: activate to sort column descending"
                    style="width: 157px;">Табельный номер
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                    colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Ф.И.О.
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                    colspan="1" aria-label="Actions: activate to sort column ascending" style="width: 268px;">Дата рождения
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                    colspan="1" aria-label="Actions: activate to sort column ascending" style="width: 268px;">Пол
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                    colspan="1" aria-label="Role: activate to sort column ascending" style="width: 126px;">Подразделение
                </th>
                <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                    colspan="1" aria-label="Status: activate to sort column ascending" style="width: 134px;">Должность
                </th>
                <@security.authorize access=ROLE_DB_ADMIN>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                        colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Действие
                    </th>
                </@security.authorize>
            </tr>
            </thead>
            <tbody role="alert" aria-live="polite" aria-relevant="all">
                <#list employeesPage.data as employee>
                    <#if employee_index % 2 == 0>
                        <tr class="odd">
                            <td class="center">${employee.personnelNumber!""}</td>
                            <td class="center">${employee.initials!""}</td>
                            <td class="center">${employee.birthday?string["dd.MM.yyyy"]}</td>
                            <td class="center">${employee.gender!""}</td>
                            <td class="center">${employee.department.abbreviatedName!""}</td>
                            <td class="center">${employee.post.abbreviatedName!""} ${employee.post.rang!""} ${employee.post.typeRang!""}</td>
                            <@security.authorize access=ROLE_DB_ADMIN>
                                <td class="center">
                                    <a class="btn btn-success" title="Редактировать" href="<@spring.url relativeUrl="/employee/edit?id=${employee.id}"/>">
                                        <i class="halflings-icon white edit"></i>
                                    </a>
                                    <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/employee/delete?id=${employee.id}"/>">
                                        <i class="halflings-icon white trash"></i>
                                    </a>
                                </td>
                            </@security.authorize>
                        </tr>
                    <#else>
                        <tr class="even">
                            <td class="center">${employee.personnelNumber!""}</td>
                            <td class="center">${employee.initials!""}</td>
                            <td class="center">${employee.birthday?string["dd.MM.yyyy"]}</td>
                            <td class="center">${employee.gender!""}</td>
                            <td class="center">${employee.department.abbreviatedName!""}</td>
                            <td class="center">${employee.post.abbreviatedName!""} ${employee.post.rang!""} ${employee.post.typeRang!""}</td>
                            <@security.authorize access=ROLE_DB_ADMIN>
                                <td class="center">
                                    <a class="btn btn-success" title="Редактировать" href="<@spring.url relativeUrl="/employee/edit?id=${employee.id}"/>">
                                        <i class="halflings-icon white edit"></i>
                                    </a>
                                    <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/employee/delete?id=${employee.id}"/>">
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
            <div class="span12">
                <div class="dataTables_info" id="DataTables_Table_0_info">
                    <form class="form-horizontal">
                        <div class="control-group">
                            <label class="control-label" for="page-size">Страница ${employeesPage.number} из ${employeesPage.total} по</label>
                            <div class="controls">
                                <select size="1" id="page-size" name="page-size" onchange="self.location='<@spring.url relativeUrl="/permit"/>?page=1&pagesize='+this.value">
                                    <option value="15" <#if employeesPage.pageSize == 15>selected="selected"</#if>>15</option>
                                    <option value="30" <#if employeesPage.pageSize == 30>selected="selected"</#if>>30</option>
                                    <option value="100" <#if employeesPage.pageSize == 100>selected="selected"</#if>>100</option>
                                </select>
                                <span class="help-inline">сотрудников.</span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="span12 center">
                <div class="dataTables_paginate paging_bootstrap pagination">
                    <ul>
                        <#if employeesPage.number != 1 >
                            <li class="prev"><a href="<@spring.url relativeUrl="/employee?page=${employeesPage.number-1}"/>">← Previous</a></li>
                        <#else>
                            <li class="prev disabled"><a href="#">← Previous</a></li>
                        </#if>
                        <#list 1..employeesPage.total as i>
                            <#if i == employeesPage.number>
                                <li class="active"><a href="#">${i}</a></li>
                            <#else>
                                <li><a href="<@spring.url relativeUrl="/employee?page=${i}"/>">${i}</a></li>
                            </#if>
                        </#list>
                        <#if employeesPage.number != employeesPage.total >
                            <li class="next"><a href="<@spring.url relativeUrl="/employee?page=${employeesPage.number+1}"/>">Next → </a></li>
                        <#else>
                            <li class="next disabled"><a href="#">Next → </a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</@base.override>

<@base.template/>