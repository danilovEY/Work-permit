<#-- @ftlvariable name="eventPermitEntity" type="ru.kolaer.permit.entity.EventPermitEntity" -->
<#-- @ftlvariable name="employees" type="java.util.List<ru.kolaer.permit.entity.EmployeeEntity>" -->

<#import "../../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "body">
<script src="<@spring.url relativeUrl="/resources/js/bootstrap-datetimepicker.js"/>"></script>
<script src="<@spring.url relativeUrl="/resources/js/bootstrap-datetimepicker.ru.js"/>"></script>

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header">
            <h2><i class="halflings-icon white th"></i><span class="break"></span>Допуск-наряд на высоту</h2>
        </div>
        <div class="box-content">
            <ul class="nav tab-menu nav-tabs">
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/work?id=${eventPermitEntity.id}"/>">Работа</a></li>
                <li class="active"><a href="<@spring.url relativeUrl="/permit/edit/event?id=${eventPermitEntity.id}"/>">Условия и мероприятия</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/people?id=${eventPermitEntity.id}"/>">Люди</a></li>
            </ul>
            <form class="form-inline">
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
                                                <#list eventPermitEntity.workEvents as event>
                                                    <#if event.typeEvent == "BEGIN">
                                                        <#if event_index % 2 == 0>
                                                            <tr class="odd">
                                                                <td class="center" id="row-event-id-${event.id}">${event_index}</td>
                                                                <td class="center" id="row-event-name-${event.id}">${event.name!""}</td>
                                                                <td class="center" id="row-event-date-${event.id}">${event.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                                <#if event.employeesEntity?has_content>
                                                                    <td class="center" id="row-event-name-${event.id}">
                                                                        <#list event.employeesEntity as eventEmp>
                                                                            ${eventEmp.initials!""}<br/>
                                                                        </#list>
                                                                    </td>
                                                                <#else>
                                                                    <td class="center" id="row-event-name-${event.id}"></td>
                                                                </#if>
                                                            </tr>
                                                        <#else>
                                                            <tr class="even">
                                                                <td class="center" id="row-event-id-${event.id}">${event_index}</td>
                                                                <td class="center" id="row-event-name-${event.id}">${event.name!""}</td>
                                                                <td class="center" id="row-event-date-${event.id}">${event.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                                <#if event.employeesEntity?has_content>
                                                                    <td class="center" id="row-event-name-${event.id}">
                                                                        <#list event.employeesEntity as eventEmp>
                                                                        ${eventEmp.initials!""}<br/>
                                                                        </#list>
                                                                    </td>
                                                                <#else>
                                                                    <td class="center" id="row-event-name-${event.id}"></td>
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
                                                <#list eventPermitEntity.workEvents as event>
                                                    <#if event.typeEvent == "PROCESS">
                                                        <#if event_index % 2 == 0>
                                                        <tr class="odd">
                                                            <td class="center" id="row-event-id-${event.id}">${event_index}</td>
                                                            <td class="center" id="row-event-name-${event.id}">${event.name!""}</td>
                                                            <td class="center" id="row-event-date-${event.id}">${event.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                            <#if event.employeesEntity?has_content>
                                                                <td class="center" id="row-event-name-${event.id}">
                                                                    <#list event.employeesEntity as eventEmp>
                                                                    ${eventEmp.initials!""}<br/>
                                                                    </#list>
                                                                </td>
                                                            <#else>
                                                                <td class="center" id="row-event-name-${event.id}"></td>
                                                            </#if>
                                                        </tr>
                                                        <#else>
                                                        <tr class="even">
                                                            <td class="center" id="row-event-id-${event.id}">${event_index}</td>
                                                            <td class="center" id="row-event-name-${event.id}">${event.name!""}</td>
                                                            <td class="center" id="row-event-date-${event.id}">${event.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                            <#if event.employeesEntity?has_content>
                                                                <td class="center" id="row-event-name-${event.id}">
                                                                    <#list event.employeesEntity as eventEmp>
                                                                    ${eventEmp.initials!""}<br/>
                                                                    </#list>
                                                                </td>
                                                            <#else>
                                                                <td class="center" id="row-event-name-${event.id}"></td>
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
                                                <#list eventPermitEntity.workEvents as event>
                                                    <#if event.typeEvent == "SPECIAL">
                                                        <#if event_index % 2 == 0>
                                                        <tr class="odd">
                                                            <td class="center" id="row-event-id-${event.id}">${event_index}</td>
                                                            <td class="center" id="row-event-name-${event.id}">${event.name!""}</td>
                                                            <td class="center" id="row-event-date-${event.id}">${event.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                            <#if event.employeesEntity?has_content>
                                                                <td class="center" id="row-event-name-${event.id}">
                                                                    <#list event.employeesEntity as eventEmp>
                                                                    ${eventEmp.initials!""}<br/>
                                                                    </#list>
                                                                </td>
                                                            <#else>
                                                                <td class="center" id="row-event-name-${event.id}"></td>
                                                            </#if>
                                                        </tr>
                                                        <#else>
                                                        <tr class="even">
                                                            <td class="center" id="row-event-id-${event.id}">${event_index}</td>
                                                            <td class="center" id="row-event-name-${event.id}">${event.name!""}</td>
                                                            <td class="center" id="row-event-date-${event.id}">${event.limitDate?string["dd.MM.yyyy hh:mm"]!""}</td>
                                                            <#if event.employeesEntity?has_content>
                                                                <td class="center" id="row-event-name-${event.id}">
                                                                    <#list event.employeesEntity as eventEmp>
                                                                    ${eventEmp.initials!""}<br/>
                                                                    </#list>
                                                                </td>
                                                            <#else>
                                                                <td class="center" id="row-event-name-${event.id}"></td>
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

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/permit/view"/>'">Отмена</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        $('#beginWorkDatePicker').datetimepicker({
            language: 'ru',
            format: 'dd.MM.yyyy hh:mm'
        });
    });

    $(function() {
        $('#endWorkDatePicker').datetimepicker({
            language: 'ru',
            format: 'dd.MM.yyyy hh:mm'
        });
    });
</script>

</@base.override>

<@base.template/>
