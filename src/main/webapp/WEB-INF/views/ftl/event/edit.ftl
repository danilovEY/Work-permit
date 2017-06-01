<#-- @ftlvariable name="workEvent" type="ru.kolaer.permit.entity.WorkEvent" -->
<#-- @ftlvariable name="employees" type="java.util.List<ru.kolaer.permit.entity.EmployeeEntity>" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "body">
<script src="<@spring.url relativeUrl="/resources/js/bootstrap-datetimepicker.js"/>"></script>
<script src="<@spring.url relativeUrl="/resources/js/bootstrap-datetimepicker.ru.js"/>"></script>

<div class="row-fluid">
    <form class="form-inline" method="post" action="<@spring.url relativeUrl="/event/update"/>">
    <div class="box span6">
        <div class="box-header">
            <h2><i class="halflings-icon white th"></i><span class="break"></span>Добавить мероприятие</h2>
        </div>
        <div class="box-content">
                <input type="hidden" name="id" value="${workEvent.id}">
                <input type="hidden" name="permit.id" value="${workEvent.permitId}">

                <div class="control-group">
                    <label class="control-label" for="selectType">Тип мероприятия:</label>
                    <div class="controls">
                        <select id="selectType" class="span12" name="typeEvent" data-rel="chosen">
                            <#if workEvent.typeEvent?has_content>
                                <#if workEvent.typeEvent == "BEGIN">
                                    <option value="BEGIN" selected="selected">До начала производства работ</option>
                                    <option value="PROCESS">Во время производства работ</option>
                                    <option value="SPECIAL">Особые условия производства работ</option>
                                <#elseif workEvent.typeEvent == "PROCESS">
                                    <option value="BEGIN">До начала производства работ</option>
                                    <option value="PROCESS" selected="selected">Во время производства работ</option>
                                    <option value="SPECIAL">Особые условия производства работ</option>
                                <#elseif workEvent.typeEvent == "SPECIAL">
                                    <option value="BEGIN">До начала производства работ</option>
                                    <option value="PROCESS">Во время производства работ</option>
                                    <option value="SPECIAL" selected="selected">Особые условия производства работ</option>
                                </#if>
                            <#else>
                                <option disabled selected value> Тип мероприятия... </option>
                                <option value="BEGIN">До начала производства работ</option>
                                <option value="PROCESS">Во время производства работ</option>
                                <option value="SPECIAL">Особые условия производства работ</option>
                            </#if>
                        </select>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="eventName">Наименование/Описание мероприятий:</label>
                    <div class="controls">
                        <input id="eventName" class="span12" type="text" name="name" value="${workEvent.name}"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="beginLimitDatePicker">Срок выполнения:</label>
                    <div class="controls">
                        <div id="beginLimitDatePicker" class="input-append date span12">
                            <input class="span11" data-format="dd.MM.yyyy hh:mm" type="text" name="limitDate" value="${(workEvent.limitDate!.now)?string["dd.MM.yyyy hh:mm"]}"/>
                            <span class="add-on">
                                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                                </span>
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>

        </div>
    </div>

    <div class="box span6">
        <div class="box-header">
            <h2><i class="halflings-icon white th"></i><span class="break"></span>Состав исполнителей работ</h2>
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
                        colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Фамилия, имя, отчество
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                        colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Цех/Отдел
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                        colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Должность
                    </th>
                    <th class="sorting" role="columnheader" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                        colspan="1" aria-label="Date registered: activate to sort column ascending" style="width: 229px;">Действие
                    </th>
                </tr>
                </thead>
                <tbody role="alert" aria-live="polite" aria-relevant="all">
                    <#if workEvent.employeesEntity?has_content>
                        <#list workEvent.employeesEntity as emp>
                            <#if emp_index % 2 == 0>
                            <tr class="odd">
                                <td class="center" id="row-emp-id-${emp.id}">${emp_index + 1}</td>
                                <td class="center" id="row-emp-name-${emp.id}">${emp.initials!""}</td>
                                <td class="center" id="row-emp-dep-${emp.id}">${emp.department.abbreviatedName!""}</td>
                                <td class="center" id="row-emp-post-${emp.id}">${emp.post.abbreviatedName!""} ${emp.post.rang!""} ${emp.post.typeRang!""}</td>
                                <td class="center">
                                    <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/event/delete/employee?id=${workEvent.id}&employee=${emp.id}"/>">
                                        <i class="halflings-icon white trash"></i>
                                    </a>
                                </td>
                            </tr>
                            <#else>
                            <tr class="even">
                                <td class="center" id="row-emp-id-${emp.id}">${emp_index + 1}</td>
                                <td class="center" id="row-emp-name-${emp.id}">${emp.initials!""}</td>
                                <td class="center" id="row-emp-dep-${emp.id}">${emp.department.abbreviatedName!""}</td>
                                <td class="center" id="row-emp-post-${emp.id}">${emp.post.abbreviatedName!""} ${emp.post.rang!""} ${emp.post.typeRang!""}</td>
                                <td class="center">
                                    <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/event/delete/employee?id=${workEvent.id}&employee=${emp.id}"/>">
                                        <i class="halflings-icon white trash"></i>
                                    </a>
                                </td>
                            </tr>
                            </#if>
                        </#list>
                    </#if>
                </tbody>
            </table>

            <div class="control-group">
                <label class="control-label" for="selectExecutors">Исполнитель работ:</label>
                <div class="controls">
                    <select id="selectExecutors" class="span12" name="employeesEntity[${(workEvent.employeesEntity![])?size}].id" data-rel="chosen">
                        <option disabled selected value> Исполнитель работ... </option>

                        <#if employees?has_content>
                            <#list employees as emp>
                                <#assign haveExecutor = false>
                                <#if workEvent.employeesEntity?has_content>
                                    <#list workEvent.employeesEntity as executor>
                                        <#if emp.id == executor.id>
                                            <#assign haveExecutor = true>
                                            <#break>
                                        </#if>
                                    </#list>
                                </#if>

                                <#if !haveExecutor>
                                    <option value="${emp.id}">(${emp.personnelNumber?c}) ${emp.initials} - ${emp.department.abbreviatedName}</option>
                                </#if>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <div class="pull-right">
                    <button type="submit" id="saveButton" class="btn btn-info" style="margin-bottom: 10px;">
                        <i class="halflings-icon plus white"></i>
                        Добавить исполнителя
                    </button>

                </div>
            </div>
        </div>
    </div>

    </form>
</div>

<script type="text/javascript">
    $(function() {
        $('#beginLimitDatePicker').datetimepicker({
            language: 'ru',
            format: 'dd.MM.yyyy hh:mm'
        });
    });

</script>

</@base.override>

<@base.template/>
