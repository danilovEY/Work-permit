<#-- @ftlvariable name="permitId" type="java.lang.Integer" -->
<#-- @ftlvariable name="statuses" type="java.util.List<ru.kolaer.permit.entity.PermitStatusHistoryEntity>" -->

<#import "../../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<#assign EDIT_PERMIT = "Редактирование"/>
<#assign NEED_ACCEPT_PERMIT = "Запрос на согласование"/>
<#assign ACCEPT_PERMIT = "Согласовано"/>
<#assign PERMIT = "Допуск"/>
<#assign CANCELED = "Отменен"/>
<#assign WORKING = "В работе"/>
<#assign OVERDUE = "Просрочен"/>
<#assign DELETED_STATUS = "Удален"/>

<@base.override "title">
<title>История</title>
</@base.override>

<@base.override "body">

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header">
            <h2><i class="halflings-icon white th"></i><span class="break"></span>Допуск-наряд на высоту</h2>
        </div>
        <div class="box-content">
            <ul class="nav tab-menu nav-tabs">
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/work?id=${permitId}"/>">Работа</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/event?id=${permitId}"/>">Условия и мероприятия</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/people?id=${permitId}"/>">Люди</a></li>
                <li class="active"><a href="<@spring.url relativeUrl="/permit/view/history?id=${permitId}"/>">История</a></li>
            </ul>
            <form class="form-inline">
                <div id="myTabContent" class="tab-content">
                    <#-- FIRST_TAB -->
                    <div class="tab-pane active" id="history_tab">

                        <div class="box span12">
                            <div class="box-header">
                                <h2><i class="halflings-icon white user"></i><span class="break"></span>История наряда</h2>
                            </div>
                            <div class="box-content">
                                <ul class="dashboard-list">
                                    <#list statuses as status>
                                        <li>
                                            <#if status.employee?has_content>
                                                <strong>Ф.И.О.:</strong> ${status.employee.initials}<br>
                                            </#if>
                                            <strong>Дата изменения:</strong> ${status.statusDate?string["dd.MM.yyyy hh:mm"]}<br>
                                            <strong>Статус:</strong>
                                            <#if status.status == NEED_ACCEPT_PERMIT>
                                                <span class="label label-warning">${status.status}</span>
                                            <#elseif status.status == CANCELED>
                                                <span class="label label-important">${status.status}</span>
                                            <#elseif status.status == ACCEPT_PERMIT>
                                                <span class="label label-info">${status.status}</span>
                                            <#elseif status.status == PERMIT>
                                                <span class="label label-success">${status.status}</span>
                                            <#elseif status.status == WORKING>
                                               <span class="label label-success">${status.status}</span>
                                            <#elseif status.status == OVERDUE>
                                                <span class="label label-important">${status.status}</span>
                                            <#elseif status.status == DELETED_STATUS>
                                                <span class="label label-inverse">${status.status}</span>
                                            <#else>
                                                <span class="label">${status.status}</span>
                                            </#if>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                        </div><!--/span-->

                    </div>

                </div>

                <div class="form-actions">
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/permit"/>'">Назад</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@base.override>

<@base.template/>
