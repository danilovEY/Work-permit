<#-- @ftlvariable name="peoplePermitEntity" type="ru.kolaer.permit.entity.PeoplePermitEntity" -->
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
            <ul class="nav tab-menu nav-tabs" id="myTab">
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/work?id=${peoplePermitEntity.id}"/>">Работа</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/event?id=${peoplePermitEntity.id}"/>">Условия и мероприятия</a></li>
                <li class="active"><a href="<@spring.url relativeUrl="/permit/edit/people?id=${peoplePermitEntity.id}"/>">Люди</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/history?id=${peoplePermitEntity.id}"/>">История</a></li>
            </ul>
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/permit/update/people"/>">
                <input type="hidden" name="id" value="${peoplePermitEntity.id!""}">
                <div id="myTabContent" class="tab-content">
                    <#-- THIRD_TAB -->
                    <div class="tab-pane active" id="employee_tab">
                        <div class="row-fluid">
                        <#-- Наряд BEGIN -->
                            <div class="box span6">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>Наряд</h2>
                                </div>
                                <div class="box-content">
                                    <div class="control-group">
                                        <label class="control-label" for="permitName">Наряд-допуск выдал:</label>
                                        <div class="controls">
                                            <input type="hidden" name="writer.id" value="${peoplePermitEntity.writer.id}" readonly/>
                                            <input class="span12" id="permitName" type="text" name="wr" value="${peoplePermitEntity.writer.initials}" readonly/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="permitName">Наряд-допуск принял:</label>
                                        <div class="controls">
                                            <input class="span12" id="permitName" type="text"/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="permitName">Целевой инструктаж провёл:</label>
                                        <div class="controls">
                                            <input class="span12" id="permitName" type="text"/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="permitName">Целевой инструктаж получил:</label>
                                        <div class="controls">
                                            <input class="span12" id="permitName" type="text"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <#-- Наряд END -->

                        <#-- Ответственные BEGIN -->
                            <div class="box span6">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>Ответственные</h2>
                                </div>
                                <div class="box-content">
                                    <div class="control-group">
                                        <label class="control-label" for="selectSupervisor">Ответственный руководитель работ:</label>
                                        <div class="controls">
                                            <select id="selectSupervisor" class="span12" name="responsibleSupervisor.id" data-rel="chosen">
                                                <option disabled selected value> Ответственный руководитель работ... </option>
                                                <#if employees?has_content>
                                                    <#list employees as emp>
                                                        <#if peoplePermitEntity.responsibleSupervisor?has_content && emp.id == peoplePermitEntity.responsibleSupervisor.id>
                                                            <option selected="selected" value="${emp.id}">(${emp.personnelNumber?c}) ${emp.initials} - ${emp.department.abbreviatedName}</option>
                                                        <#else>
                                                            <option value="${emp.id}">(${emp.personnelNumber?c}) ${emp.initials} - ${emp.department.abbreviatedName}</option>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="selectExecutor">Ответственный исполнитель работ:</label>
                                        <div class="controls">
                                            <select id="selectExecutor" class="span12" name="responsibleExecutor.id" data-rel="chosen">
                                                <option disabled selected value> Ответственный исполнитель работ... </option>
                                                <#if employees?has_content>
                                                    <#list employees as emp>
                                                        <#if peoplePermitEntity.responsibleExecutor?has_content && emp.id == peoplePermitEntity.responsibleExecutor.id>
                                                            <option selected="selected" value="${emp.id}">(${emp.personnelNumber?c}) ${emp.initials} - ${emp.department.abbreviatedName}</option>
                                                        <#else>
                                                            <option value="${emp.id}">(${emp.personnelNumber?c}) ${emp.initials} - ${emp.department.abbreviatedName}</option>
                                                        </#if>
                                                    </#list>
                                                </#if>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <#-- Ответственные END -->

                        <#-- Состав BEGIN -->
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
                                            <#if peoplePermitEntity.executors?has_content>
                                                <#list peoplePermitEntity.executors as emp>
                                                    <#if emp_index % 2 == 0>
                                                        <tr class="odd">
                                                            <td class="center" id="row-emp-id-${emp.id}">${emp_index + 1}</td>
                                                            <td class="center" id="row-emp-name-${emp.id}">${emp.initials!""}</td>
                                                            <td class="center" id="row-emp-dep-${emp.id}">${emp.department.abbreviatedName!""}</td>
                                                            <td class="center" id="row-emp-post-${emp.id}">${emp.post.abbreviatedName!""} ${emp.post.rang!""} ${emp.post.typeRang!""}</td>
                                                            <td class="center">
                                                                <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/permit/delete/executor?id=${peoplePermitEntity.id}&executor=${emp.id}"/>">
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
                                                                <a class="btn btn-danger" title="Удалить" href="<@spring.url relativeUrl="/permit/delete/executor?id=${peoplePermitEntity.id}&executor=${emp.id}"/>">
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
                                            <select id="selectExecutors" class="span12" name="executors[${(peoplePermitEntity.executors![])?size}].id" data-rel="chosen">
                                                <option disabled selected value> Исполнитель работ... </option>
                                                <#if employees?has_content>
                                                    <#list employees as emp>
                                                        <#assign haveExecutor = false>
                                                        <#if peoplePermitEntity.executors?has_content>
                                                            <#list peoplePermitEntity.executors as executor>
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
                        <#-- Состав END -->
                        </div>
                    </div>

                </div>

                <div class="form-actions">
                    <button type="submit" id="saveButton" class="btn btn-primary">Сохранить</button>
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/permit"/>'">Назад</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@base.override>

<@base.template/>
