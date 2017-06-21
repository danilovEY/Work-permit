<#-- @ftlvariable name="eventPermitEntity" type="ru.kolaer.permit.entity.EventPermitEntity" -->

<#import "../../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "title">
<title>Условия и мероприятия</title>
</@base.override>

<@base.override "body">

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header">
            <h2><i class="halflings-icon white th"></i><span class="break"></span>Допуск-наряд на высоту</h2>
        </div>
        <div class="box-content">
            <ul class="nav tab-menu nav-tabs">
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/work?id=${eventPermitEntity.id}"/>">Работа</a></li>
                <li class="active"><a href="<@spring.url relativeUrl="/permit/view/event?id=${eventPermitEntity.id}"/>">Условия и мероприятия</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/people?id=${eventPermitEntity.id}"/>">Люди</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/history?id=${eventPermitEntity.id}"/>">История и действия</a></li>
            </ul>
                <div id="myTabContent" class="tab-content">
                    <#-- SECOND TAB -->
                    <div class="tab-pane active" id="event_tab">
                        <div class="row-fluid">
                        <#-- Мероприятия ДО BEGIN -->
                            <div class="box span12">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>До начала производства работ необходимо выполнить следующие мероприятия:</h2>
                                </div>
                                <div class="box-content">
                                    <table class="table table-striped table-bordered bootstrap-datatable" id="DataTables_Table_0"
                                           aria-describedby="DataTables_Table_0_info">
                                        <thead>
                                        <tr role="row">
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Номер
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Наименование мероприятий или ссылка на пункт ППР или технологических карт
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Срок выполнения
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Ответственные исполнители
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                                            <#if eventPermitEntity.workEvents?has_content>
                                                <#list eventPermitEntity.workEvents as eventBegin>
                                                    <#if eventBegin.typeEvent == "BEGIN">
                                                        <#if eventBegin_index % 2 == 0>
                                                            <tr class="odd">
                                                                <td class="center" id="row-event-id-${eventBegin.id}">${eventBegin_index + 1}</td>
                                                                <td class="center" id="row-event-name-${eventBegin.id}">${eventBegin.name!""}</td>
                                                                <td class="center" id="row-event-date-${eventBegin.id}">${eventBegin.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                                <#if eventBegin.employeesEntity?has_content>
                                                                    <td class="center" id="row-event-name-${eventBegin.id}">
                                                                        <#list eventBegin.employeesEntity as eventEmp>
                                                                            ${eventEmp.initials!""}<br/>
                                                                        </#list>
                                                                    </td>
                                                                <#else>
                                                                    <td class="center" id="row-event-name-${eventBegin.id}"></td>
                                                                </#if>
                                                            </tr>
                                                        <#else>
                                                            <tr class="even">
                                                                <td class="center" id="row-event-id-${eventBegin.id}">${eventBegin_index + 1}</td>
                                                                <td class="center" id="row-event-name-${eventBegin.id}">${eventBegin.name!""}</td>
                                                                <td class="center" id="row-event-date-${eventBegin.id}">${eventBegin.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                                <#if eventBegin.employeesEntity?has_content>
                                                                    <td class="center" id="row-event-name-${eventBegin.id}">
                                                                        <#list eventBegin.employeesEntity as eventEmp>
                                                                        ${eventEmp.initials!""}<br/>
                                                                        </#list>
                                                                    </td>
                                                                <#else>
                                                                    <td class="center" id="row-event-name-${eventBegin.id}"></td>
                                                                </#if>
                                                            </tr>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        <#-- Мероприятия ДО END -->
                        </div>

                        <div class="row-fluid">
                        <#-- Мероприятия в процессе BEGIN -->
                            <div class="box span12">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>В процессе производства работ необходимо выполнить следующие мероприятия:</h2>
                                </div>
                                <div class="box-content">
                                    <table class="table table-striped table-bordered bootstrap-datatable" id="DataTables_Table_0"
                                           aria-describedby="DataTables_Table_0_info">
                                        <thead>
                                        <tr role="row">
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Номер
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Наименование мероприятий
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Срок выполнения
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Ответственные исполнители
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                                            <#if eventPermitEntity.workEvents?has_content>
                                                <#list eventPermitEntity.workEvents as eventProcess>
                                                    <#if eventProcess.typeEvent == "PROCESS">
                                                        <#if eventProcess_index % 2 == 0>
                                                        <tr class="odd">
                                                            <td class="center" id="row-event-id-${eventProcess.id}">${eventProcess_index + 1}</td>
                                                            <td class="center" id="row-event-name-${eventProcess.id}">${eventProcess.name!""}</td>
                                                            <td class="center" id="row-event-date-${eventProcess.id}">${eventProcess.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                            <#if eventProcess.employeesEntity?has_content>
                                                                <td class="center" id="row-event-name-${eventProcess.id}">
                                                                    <#list eventProcess.employeesEntity as eventEmp>
                                                                    ${eventEmp.initials!""}<br/>
                                                                    </#list>
                                                                </td>
                                                            <#else>
                                                                <td class="center" id="row-event-name-${eventProcess.id}"></td>
                                                            </#if>
                                                        </tr>
                                                        <#else>
                                                        <tr class="even">
                                                            <td class="center" id="row-event-id-${eventProcess.id}">${eventProcess_index + 1}</td>
                                                            <td class="center" id="row-event-name-${eventProcess.id}">${eventProcess.name!""}</td>
                                                            <td class="center" id="row-event-date-${eventProcess.id}">${eventProcess.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                            <#if eventProcess.employeesEntity?has_content>
                                                                <td class="center" id="row-event-name-${eventProcess.id}">
                                                                    <#list eventProcess.employeesEntity as eventEmp>
                                                                    ${eventEmp.initials!""}<br/>
                                                                    </#list>
                                                                </td>
                                                            <#else>
                                                                <td class="center" id="row-event-name-${eventProcess.id}"></td>
                                                            </#if>
                                                        </tr>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        <#-- Мероприятия в процессе END -->
                        </div>
                        <div class="row-fluid">
                        <#-- Мероприятия в процессе BEGIN -->
                            <div class="box span12">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>Особые услови проведения работ:</h2>
                                </div>
                                <div class="box-content">
                                    <table class="table table-striped table-bordered bootstrap-datatable" id="DataTables_Table_0"
                                           aria-describedby="DataTables_Table_0_info">
                                        <thead>
                                        <tr role="row">
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Номер
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Наименование условия
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Срок выполнения
                                            </th>
                                            <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                                colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Ответственные исполнители
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                                            <#if eventPermitEntity.workEvents?has_content>
                                                <#list eventPermitEntity.workEvents as eventSpecial>
                                                    <#if eventSpecial.typeEvent == "SPECIAL">
                                                        <#if eventSpecial_index % 2 == 0>
                                                        <tr class="odd">
                                                            <td class="center" id="row-event-id-${eventSpecial.id}">${eventSpecial_index}</td>
                                                            <td class="center" id="row-event-name-${eventSpecial.id}">${eventSpecial.name!""}</td>
                                                            <td class="center" id="row-event-date-${eventSpecial.id}">${eventSpecial.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                            <#if eventSpecial.employeesEntity?has_content>
                                                                <td class="center" id="row-event-name-${eventSpecial.id}">
                                                                    <#list eventSpecial.employeesEntity as eventEmp>
                                                                    ${eventEmp.initials!""}<br/>
                                                                    </#list>
                                                                </td>
                                                            <#else>
                                                                <td class="center" id="row-event-name-${eventSpecial.id}"></td>
                                                            </#if>
                                                        </tr>
                                                        <#else>
                                                        <tr class="even">
                                                            <td class="center" id="row-event-id-${eventSpecial.id}">${eventSpecial_index}</td>
                                                            <td class="center" id="row-event-name-${eventSpecial.id}">${eventSpecial.name!""}</td>
                                                            <td class="center" id="row-event-date-${eventSpecial.id}">${eventSpecial.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                            <#if eventSpecial.employeesEntity?has_content>
                                                                <td class="center" id="row-event-name-${eventSpecial.id}">
                                                                    <#list eventSpecial.employeesEntity as eventEmp>
                                                                    ${eventEmp.initials!""}<br/>
                                                                    </#list>
                                                                </td>
                                                            <#else>
                                                                <td class="center" id="row-event-name-${eventSpecial.id}"></td>
                                                            </#if>
                                                        </tr>
                                                        </#if>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                            <#-- Мероприятия в процессе END -->
                        </div>
                    </div>
                </div>
                <form>
                    <div class="form-actions">
                        <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/permit"/>'">Назад</button>
                    </div>
                </form>

        </div>
    </div>
</div>

</@base.override>

<@base.template/>
