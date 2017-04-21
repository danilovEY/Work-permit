<#-- @ftlvariable name="departmentPage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.DepartmentEntity>" -->


<#import "layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "body">
<div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">
    <div class="row-fluid">
        <div class="span6">
            <div id="DataTables_Table_0_length" class="dataTables_length">
                <label>
                    <select size="1" name="DataTables_Table_0_length" aria-controls="DataTables_Table_0">
                        <option value="${departmentPage.pageSize}" selected="selected">${departmentPage.pageSize}</option>
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
                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Наименование
            </th>
            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Сокращенное наименование
            </th>
        </tr>
        </thead>
        <tbody role="alert" aria-live="polite" aria-relevant="all">
            <#list departmentPage.data as post>
                <#if post_index % 2 == 0>
                    <tr class="odd">
                        <td class="center">${post.id}</td>
                        <td class="center">${post.name}</td>
                        <td class="center">${post.abbreviatedName}</td>
                    </tr>
                <#else>
                    <tr class="even">
                        <td class="center">${post.id}</td>
                        <td class="center">${post.name}</td>
                        <td class="center">${post.abbreviatedName}</td>
                    </tr>
                </#if>
            </#list>
        </tbody>
    </table>

    <div class="row-fluid">
        <div class="span12">
            <div class="dataTables_info" id="DataTables_Table_0_info">
                Страница ${departmentPage.number} из ${departmentPage.total} по ${departmentPage.pageSize} подразделений.
            </div>
        </div>
        <div class="span12 center">
            <div class="dataTables_paginate paging_bootstrap pagination">
                <ul>
                    <#if departmentPage.number != 1 >
                        <li class="prev"><a href="<@spring.url relativeUrl="/posts?page=${departmentPage.number-1}"/>">← Previous</a></li>
                    <#else>
                        <li class="prev disabled"><a href="#">← Previous</a></li>
                    </#if>
                    <#list 1..departmentPage.total as i>
                        <#if i == departmentPage.number>
                            <li class="active"><a href="#">${i}</a></li>
                        <#else>
                            <li><a href="<@spring.url relativeUrl="/posts?page=${i}"/>">${i}</a></li>
                        </#if>
                    </#list>
                    <#if departmentPage.number != departmentPage.total >
                        <li class="next"><a href="<@spring.url relativeUrl="/posts?page=${departmentPage.number+1}"/>">Next → </a></li>
                    <#else>
                        <li class="next disabled"><a href="#">Next → </a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
</@base.override>

<@base.template/>