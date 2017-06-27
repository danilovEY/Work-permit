<#-- @ftlvariable name="rolePage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.RoleEntity>" -->
<#-- @ftlvariable name="roleNameMap" type="java.util.Map<java.lang.String, java.lang.String>" -->


<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "title">
<title>Роли</title>
</@base.override>

<@base.override "body">
<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-group white"></i><span class="break"></span>Список должностей</h2>
        </div>
        <div class="box-content" style="display: block;">

            <div class="control-group">
                <div class="pull-right">
                    <a class="btn btn-info" style="margin-bottom: 10px;" href="<@spring.url relativeUrl="/role/add"/>">
                        <i class="halflings-icon plus white"></i> Добавить роль
                    </a>
                </div>
            </div>

            <#--<div id="role_table_wrapper" class="dataTables_wrapper" role="grid">-->
                <#--<div class="row-fluid">-->
                    <#--<div class="span6">-->
                        <#--<div id="role_table_length" class="dataTables_length">-->
                            <#--<label>-->
                                <#--<select size="1" name="role_table_length" aria-controls="role_table">-->
                                    <#--<option value="${rolePage.pageSize}" selected="selected">${rolePage.pageSize}</option>-->
                                <#--</select> records per page-->
                            <#--</label>-->
                        <#--</div>-->
                    <#--</div>-->
                    <#--<div class="span6">-->
                        <#--<div class="dataTables_filter" id="role_table_filter">-->
                            <#--<label>Search: <input type="text" aria-controls="role_table"></label>-->
                        <#--</div>-->
                    <#--</div>-->
                <#--</div>-->

            <table class="table table-striped table-bordered bootstrap-datatable" id="role_table"
                   aria-describedby="role_table_info">
                <thead>
                <tr role="row">
                    <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="role_table" rowspan="1"
                        colspan="1" aria-sort="ascending" aria-label="Username: activate to sort column descending"
                        style="width: 157px;">ID
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="role_table" rowspan="1"
                        colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Табельный номер
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="role_table" rowspan="1"
                        colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Ф.И.О.
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="role_table" rowspan="1"
                        colspan="1" aria-label="Actions: activate to sort column ascending" style="width: 268px;">Роль
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                        colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Действие
                    </th>
                </tr>
                </thead>
                <tbody role="alert" aria-live="polite" aria-relevant="all">
                    <#list rolePage.data as role>
                        <#if role_index % 2 == 0>
                            <tr class="odd">
                                <td class="center" id="row-id-${role.id}">${role.id}</td>
                                <td class="center" id="row-pn-${role.id}">${role.employee.personnelNumber}</td>
                                <td class="center" id="row-in-${role.id}">${role.employee.initials}</td>
                                <td class="center" id="row-role-${role.id}">${roleNameMap[role.role]}</td>
                                <td class="center">
                                    <a class="btn btn-success" title="Редактировать" href="<@spring.url relativeUrl="/role/edit?id=${role.id}"/>">
                                        <i class="halflings-icon white edit"></i>
                                    </a>
                                    <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/role/delete?id=${role.id}"/>">
                                        <i class="halflings-icon white trash"></i>
                                    </a>
                                </td>
                            </tr>
                        <#else>
                            <tr class="even">
                                <td class="center" id="row-id-${role.id}">${role.id}</td>
                                <td class="center" id="row-pn-${role.id}">${role.employee.personnelNumber}</td>
                                <td class="center" id="row-in-${role.id}">${role.employee.initials}</td>
                                <td class="center" id="row-role-${role.id}">${roleNameMap[role.role]}</td>
                                <td class="center">
                                    <a class="btn btn-success" title="Редактировать" href="<@spring.url relativeUrl="/role/edit?id=${role.id}"/>">
                                        <i class="halflings-icon white edit"></i>
                                    </a>
                                    <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/role/delete?id=${role.id}"/>">
                                        <i class="halflings-icon white trash"></i>
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
                        <form class="form-horizontal">
                            <div class="control-group">
                                <label class="control-label" for="page-size">Страница ${rolePage.number} из ${rolePage.total} по</label>
                                <div class="controls">
                                    <select size="1" id="page-size" name="page-size" onchange="self.location='<@spring.url relativeUrl="/role"/>?page=1&pagesize='+this.value">
                                        <option value="15" <#if rolePage.pageSize == 15>selected="selected"</#if>>15</option>
                                        <option value="30" <#if rolePage.pageSize == 30>selected="selected"</#if>>30</option>
                                        <option value="100" <#if rolePage.pageSize == 100>selected="selected"</#if>>100</option>
                                    </select>
                                    <span class="help-inline">ролей.</span>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="span12 center">
                    <div class="dataTables_paginate paging_bootstrap pagination">
                        <ul>
                            <#if rolePage.number != 1 >
                                <li class="prev"><a href="<@spring.url relativeUrl="/role?page=${rolePage.number-1}"/>">← Previous</a></li>
                            <#else>
                                <li class="prev disabled"><a href="#">← Previous</a></li>
                            </#if>
                            <#list 1..rolePage.total as i>
                                <#if i == rolePage.number>
                                    <li class="active"><a href="#">${i}</a></li>
                                <#else>
                                    <li><a href="<@spring.url relativeUrl="/role?page=${i}"/>">${i}</a></li>
                                </#if>
                            </#list>
                            <#if rolePage.number != rolePage.total >
                                <li class="next"><a href="<@spring.url relativeUrl="/role?page=${rolePage.number+1}"/>">Next → </a></li>
                            <#else>
                                <li class="next disabled"><a href="#">Next → </a></li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</@base.override>

<@base.template/>