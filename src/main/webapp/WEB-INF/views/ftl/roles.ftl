<#-- @ftlvariable name="rolePage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.FullRoleEntity>" -->
<#-- @ftlvariable name="roleNameMap" type="java.util.Map<java.lang.String, java.lang.String>" -->


<#import "layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "body">
<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-group white"></i><span class="break"></span>Список должностей</h2>
        </div>
        <div class="box-content" style="display: block;">
            <div id="role_table_wrapper" class="dataTables_wrapper" role="grid">
                <div class="row-fluid">
                    <div class="span6">
                        <div id="role_table_length" class="dataTables_length">
                            <label>
                                <select size="1" name="role_table_length" aria-controls="role_table">
                                    <option value="${rolePage.pageSize}" selected="selected">${rolePage.pageSize}</option>
                                </select> records per page
                            </label>
                        </div>
                    </div>
                    <div class="span6">
                        <div class="dataTables_filter" id="role_table_filter">
                            <label>Search: <input type="text" aria-controls="role_table"></label>
                        </div>
                    </div>
                </div>
                <table class="table table-striped table-bordered bootstrap-datatable datatable dataTable" id="role_table"
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
                                </tr>
                            <#else>
                                <tr class="even">
                                    <td class="center" id="row-id-${role.id}">${role.id}</td>
                                    <td class="center" id="row-pn-${role.id}">${role.employee.personnelNumber}</td>
                                    <td class="center" id="row-in-${role.id}">${role.employee.initials}</td>
                                    <td class="center" id="row-role-${role.id}">${roleNameMap[role.role]}</td>
                                </tr>
                            </#if>
                        </#list>
                    </tbody>
                </table>

                <div class="row-fluid">
                    <div class="span12">
                        <div class="dataTables_info" id="role_table_info">
                            Страница ${rolePage.number} из ${rolePage.total} по ${rolePage.pageSize} ролей.
                        </div>
                    </div>
                    <div class="span12 center">
                        <div class="dataTables_paginate paging_bootstrap pagination">
                            <ul>
                                <#if rolePage.number != 1 >
                                    <li class="prev"><a href="<@spring.url relativeUrl="/roles?page=${rolePage.number-1}"/>">← Previous</a></li>
                                <#else>
                                    <li class="prev disabled"><a href="#">← Previous</a></li>
                                </#if>
                                <#list 1..rolePage.total as i>
                                    <#if i == rolePage.number>
                                        <li class="active"><a href="#">${i}</a></li>
                                    <#else>
                                        <li><a href="<@spring.url relativeUrl="/roles?page=${i}"/>">${i}</a></li>
                                    </#if>
                                </#list>
                                <#if rolePage.number != rolePage.total >
                                    <li class="next"><a href="<@spring.url relativeUrl="/roles?page=${rolePage.number+1}"/>">Next → </a></li>
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
</div>

<div class="row-fluid">
    <div class="box span4">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white plus"></i><span class="break"></span>Добавить роль</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/roles/add"/>">
                <div class="control-group">
                    <label class="control-label" for="selectRoles">Роль: </label>
                    <div class="controls">
                        <select id="selectRoles" name="role" data-rel="chosen">
                            <option value="-1">Роли...</option>
                            <#if roleNameMap?has_content>
                                <#list roleNameMap?values as role>
                                    <option>${role}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="personnelNumber">Табельный номер: </label>
                    <div class="controls">
                        <input type="text" class="span12 personnelNumber" id="personnelNumber" name="employee.personnelNumber">
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="box span4">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white refresh"></i><span class="break"></span>Обновить роль</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/roles/update"/>">
                <div class="control-group">
                    <label class="control-label" for="selectRowRoles">Роль: </label>
                    <div class="controls">
                        <select id="selectRowRoles" name="roles" data-rel="chosen" onchange="run(this.value)">
                            <option value="-1">Роли...</option>
                            <#list rolePage.data as role>
                                <option value="${role.id}">(${role.employee.personnelNumber}) ${role.employee.initials} - ${roleNameMap[role.role]}</option>
                            </#list>
                        </select>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="idUpdate">ID: </label>
                    <div class="controls">
                        <input type="text" class="span12 idUpdate" id="idUpdate" name="idUpdate" readonly="true">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="updatePersonnelNumber">Табельный номер: </label>
                    <div class="controls">
                        <input type="text" class="span12 updatePersonnelNumber" id="updatePersonnelNumber" name="personnelNumber">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="updateRole">Роль: </label>
                    <div class="controls">
                        <select id="updateRole" name="roles" data-rel="chosen">
                            <option value="-1">Роли...</option>
                            <#if roleNameMap?has_content>
                                <#list roleNameMap?keys as key>
                                    <option value="${roleNameMap[key]}">${roleNameMap[key]}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Обновить</button>
                </div>
            </form>
        </div>
    </div>

    <#-- <div class="box span4">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white trash"></i><span class="break"></span>Удалить роль</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/roles/delete"/>">
                <div class="control-group">
                    <label class="control-label" for="name">Роль: </label>
                    <div class="controls">
                        <select id="selectRoles" name="roles" data-rel="chosen" onchange="run(this.value)">
                            <option value="-1">Роли...</option>
                            <#list rolePage.data as role>
                                <option value="${role.id}">(${role.employee.personnelNumber}) ${role.employee.initials} - ${role.role}</option>
                            </#list>
                        </select>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Удалить</button>
                </div>
            </form>
        </div>
    </div> -->
</div>

<script>
    function run(value) {
        if(value != -1) {
            document.getElementById("idUpdate").value = document.getElementById("row-id-" + value).innerHTML;
            document.getElementById("updateRole").value = document.getElementById("row-role-" + value).innerHTML;
            document.getElementById("updatePersonnelNumber").value = document.getElementById("row-pn-" + value).innerHTML.replace("&nbsp;", "");
        } else {
            document.getElementById("idUpdate").value = "";
            document.getElementById("updateRole").value = "-1";
            document.getElementById("updatePersonnelNumber").value = "";
        }
    }
</script>

</@base.override>

<@base.template/>