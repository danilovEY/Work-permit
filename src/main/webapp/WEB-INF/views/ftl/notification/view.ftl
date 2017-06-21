<#-- @ftlvariable name="notifications" type="ru.kolaer.permit.dto.Page<ru.kolaer.permit.entity.NotificationEntity>" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<#assign NEED_APPROVE_STATUS = "NEED_APPROVE_STATUS"/>
<#assign APPROVE_STATUS = "APPROVE_STATUS"/>

<@base.override "title">
<title>Уведомления</title>
</@base.override>

<@base.override "body">

<div class="row-fluid" xmlns="http://www.w3.org/1999/html">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white th-large"></i><span class="break"></span>Список подразделений</h2>
        </div>
        <div class="box-content" style="display: block;">
            <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper" role="grid">
                <table class="table table-striped table-bordered bootstrap-datatable" id="DataTables_Table_0"
                       aria-describedby="DataTables_Table_0_info">
                    <thead>
                    <tr role="row">
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Дата
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Сообщение
                        </th>
                        <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                            colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Ссылка
                        </th>
                    </tr>
                    </thead>
                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <#list notifications.data as notify>
                            <#if notify.type == NEED_APPROVE_STATUS>
                                <tr class="warning">
                                    <td class="center">${notify.createDate?string["dd.MM.yyyy HH:mm:ss"]}</td>
                                    <td class="center">${notify.message!""}</td>
                                    <td class="center"><a href="<@spring.url relativeUrl="/notification/redirect?id="/>${notify.id}">Перейти</a></td>
                                </tr>
                            </#if>
                            <#if notify.type == APPROVE_STATUS>
                                <tr class="info">
                                    <td class="center">${notify.createDate?string["dd.MM.yyyy HH:mm:ss"]}</td>
                                    <td class="center">${notify.message!""}</td>
                                    <td class="center"><a href="<@spring.url relativeUrl="/notification/redirect?id="/>${notify.id}">Перейти</a></td>
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
                                    <label class="control-label" for="page-size">Страница ${notifications.number} из ${notifications.total} по</label>
                                    <div class="controls">
                                        <select size="1" id="page-size" name="page-size" onchange="self.location='<@spring.url relativeUrl="/notification"/>?page=1&pagesize='+this.value">
                                            <option value="15" <#if notifications.pageSize == 15>selected="selected"</#if>>15</option>
                                            <option value="30" <#if notifications.pageSize == 30>selected="selected"</#if>>30</option>
                                            <option value="100" <#if notifications.pageSize == 100>selected="selected"</#if>>100</option>
                                        </select>
                                        <span class="help-inline">уведомлений.</span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="span12 center">
                        <div class="dataTables_paginate paging_bootstrap pagination">
                            <ul>
                                <#if notifications.number != 1 >
                                    <li class="prev"><a href="<@spring.url relativeUrl="/notification?page=${notifications.number-1}"/>">← Previous</a></li>
                                <#else>
                                    <li class="prev disabled"><a href="#">← Previous</a></li>
                                </#if>
                                <#list 1..notifications.total as i>
                                    <#if i == notifications.number>
                                        <li class="active"><a href="#">${i}</a></li>
                                    <#else>
                                        <li><a href="<@spring.url relativeUrl="/notification?page=${i}"/>">${i}</a></li>
                                    </#if>
                                </#list>
                                <#if notifications.number != notifications.total >
                                    <li class="next"><a href="<@spring.url relativeUrl="/notification?page=${notifications.number+1}"/>">Next → </a></li>
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