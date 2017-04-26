<#-- @ftlvariable name="postPage" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.PostEntity>" -->


<#import "layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "body">
<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white th"></i><span class="break"></span>Список должностей</h2>
        </div>
        <div class="box-content" style="display: block;">
            <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">
                <div class="row-fluid">
                    <div class="span6">
                        <div id="DataTables_Table_0_length" class="dataTables_length">
                            <label>
                                <select size="1" name="DataTables_Table_0_length" aria-controls="DataTables_Table_0">
                                    <option value="${postPage.pageSize}" selected="selected">${postPage.pageSize}</option>
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
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Actions: activate to sort column ascending" style="width: 268px;">Номер группы
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Role: activate to sort column ascending" style="width: 126px;">Наименование группы
                        </th>
                    </tr>
                    </thead>
                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <#list postPage.data as post>
                            <#if post_index % 2 == 0>
                                <tr class="odd">
                                    <td class="center">${post.id}</td>
                                    <td class="center">${post.name}</td>
                                    <td class="center">${post.abbreviatedName}</td>
                                    <td class="center">${post.rang!""}</td>
                                    <td class="center">${post.typeRang!""}</td>
                                </tr>
                            <#else>
                                <tr class="even">
                                    <td class="center">${post.id}</td>
                                    <td class="center">${post.name}</td>
                                    <td class="center">${post.abbreviatedName}</td>
                                    <td class="center">${post.rang!""}</td>
                                    <td class="center">${post.typeRang!""}</td>
                                </tr>
                            </#if>
                        </#list>
                    </tbody>
                </table>

                <div class="row-fluid">
                    <div class="span12">
                        <div class="dataTables_info" id="DataTables_Table_0_info">
                            Страница ${postPage.number} из ${postPage.total} по ${postPage.pageSize} должностей.
                        </div>
                    </div>
                    <div class="span12 center">
                        <div class="dataTables_paginate paging_bootstrap pagination">
                            <ul>
                                <#if postPage.number != 1 >
                                    <li class="prev"><a href="<@spring.url relativeUrl="/posts?page=${postPage.number-1}"/>">← Previous</a></li>
                                <#else>
                                    <li class="prev disabled"><a href="#">← Previous</a></li>
                                </#if>
                                <#list 1..postPage.total as i>
                                    <#if i == postPage.number>
                                        <li class="active"><a href="#">${i}</a></li>
                                    <#else>
                                        <li><a href="<@spring.url relativeUrl="/posts?page=${i}"/>">${i}</a></li>
                                    </#if>
                                </#list>
                                <#if postPage.number != postPage.total >
                                    <li class="next"><a href="<@spring.url relativeUrl="/posts?page=${postPage.number+1}"/>">Next → </a></li>
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
    <div class="box span6">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white plus"></i><span class="break"></span>Добавить должность</h2>
        </div>
        <div class="box-content" style="display: block;">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/posts/add"/>">
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
                    <button type="reset" class="btn">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
</@base.override>

<@base.template/>