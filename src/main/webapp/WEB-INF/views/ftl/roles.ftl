<#-- @ftlvariable name="rolePage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.FullRoleEntity>" -->


<#import "layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "body">
<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="icon-group white"></i><span class="break"></span>Список должностей</h2>
        </div>
        <div class="box-content" style="display: block;">
            <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">
                <div class="row-fluid">
                    <div class="span6">
                        <div id="DataTables_Table_0_length" class="dataTables_length">
                            <label>
                                <select size="1" name="DataTables_Table_0_length" aria-controls="DataTables_Table_0">
                                    <option value="${rolePage.pageSize}" selected="selected">${rolePage.pageSize}</option>
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
                <table class="table table-striped table-bordered bootstrap-datatable datatable dataTable" id="DataTables_Table_0"
                       aria-describedby="DataTables_Table_0_info">
                    <thead>
                    <tr role="row">
                        <th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-sort="ascending" aria-label="Username: activate to sort column descending"
                            style="width: 157px;">ID
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Табельный номер
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Ф.И.О.
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
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
                                    <td class="center" id="row-role-${role.id}">${role.role}</td>
                                </tr>
                            <#else>
                                <tr class="even">
                                    <td class="center" id="row-id-${role.id}">${role.id}</td>
                                    <td class="center" id="row-pn-${role.id}">${role.employee.personnelNumber}</td>
                                    <td class="center" id="row-in-${role.id}">${role.employee.initials}</td>
                                    <td class="center" id="row-role-${role.id}">${role.role}</td>
                                </tr>
                            </#if>
                        </#list>
                    </tbody>
                </table>

                <div class="row-fluid">
                    <div class="span12">
                        <div class="dataTables_info" id="DataTables_Table_0_info">
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

<#-- <div class="row-fluid">
    <div class="box span4">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white plus"></i><span class="break"></span>Добавить роль</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/roles/add"/>">
                <div class="control-group">
                    <label class="control-label" for="name">Наименование: </label>
                    <div class="controls">
                        <input type="text" class="span12 name" id="name" name="name">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="abbreviatedName">Сокращенное наименование: </label>
                    <div class="controls">
                        <input type="text" class="span12 abbreviatedName" id="abbreviatedName" name="abbreviatedName">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="rang">Номер группы: </label>
                    <div class="controls">
                        <input type="text" class="span12 rang" id="rang" name="rang">
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="typeRang">Наименование группы: </label>
                    <div class="controls">
                        <input type="text" class="span12 typeRang" id="typeRang" name="typeRang">
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
            <h2><i class="halflings-icon white refresh"></i><span class="break"></span>Обновить должность</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/posts/update"/>">
                <div class="control-group">
                    <label class="control-label" for="selectError">Наименование</label>
                    <div class="controls">
                        <select id="selectError" name="id" data-rel="chosen" onchange="run(this.value)">
                            <option value="-1">Подразделения...</option>
                            <#list rolePage.data as post>
                                <option value="${post.id}">${post.role}</option>
                            </#list>
                        </select>
                    </div>
                </div>

                <label class="control-label" for="id">ID: </label>
                <div class="controls">
                    <input type="text" class="span12" id="idUpdate" name="id" readonly/>
                </div>

                <label class="control-label" for="name">Наименование: </label>
                <div class="controls">
                    <input type="text" class="span12" id="nameUpdate" name="name"/>
                </div>

                <label class="control-label" for="abbreviatedName">Сокращенное наименование: </label>
                <div class="controls">
                    <input type="text" class="span12" id="abbreviatedNameUpdate" name="abbreviatedName"/>
                </div>

                <label class="control-label" for="rang">Номер группы: </label>
                <div class="controls">
                    <input type="text" class="span12 rang" id="rangUpdate" name="rang">
                </div>

                <label class="control-label" for="typeRang">Наименование группы: </label>
                <div class="controls">
                    <input type="text" class="span12 typeRang" id="typeRangUpdate" name="typeRang">
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Обновить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="box span4">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white trash"></i><span class="break"></span>Удалить должность</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/posts/delete"/>">
                <div class="control-group">
                    <label class="control-label" for="selectError">Наименование</label>
                    <div class="controls">
                        <select id="selectError" name="id" data-rel="chosen">
                            <#list rolePage.data as post>
                                <option value="${post.id}">${post.role}</option>
                            </#list>
                        </select>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Удалить</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function run(value) {
        if(value != -1) {
            document.getElementById("idUpdate").value = document.getElementById("row-id-" + value).innerHTML;
            document.getElementById("nameUpdate").value = document.getElementById("row-name-" + value).innerHTML;
            document.getElementById("abbreviatedNameUpdate").value = document.getElementById("row-abb-" + value).innerHTML;
            document.getElementById("rangUpdate").value = document.getElementById("row-rang-" + value).innerHTML;
            document.getElementById("typeRangUpdate").value = document.getElementById("row-type-" + value).innerHTML;
        } else {
            document.getElementById("idUpdate").value = "";
            document.getElementById("nameUpdate").value = "";
            document.getElementById("abbreviatedNameUpdate").value = "";
            document.getElementById("rangUpdate").value = "";
            document.getElementById("typeRangUpdate").value = "";
        }
    }
</script> -->

</@base.override>

<@base.template/>