<#-- @ftlvariable name="peoplePermitEntity" type="ru.kolaer.permit.entity.PeoplePermitEntity" -->

<#import "../../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "title">
<title>Люди</title>
</@base.override>

<@base.override "body">

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header">
            <h2><i class="halflings-icon white th"></i><span class="break"></span>Допуск-наряд на высоту</h2>
        </div>
        <div class="box-content">
            <ul class="nav tab-menu nav-tabs" id="myTab">
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/work?id=${peoplePermitEntity.id}"/>">Работа</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/event?id=${peoplePermitEntity.id}"/>">Условия и мероприятия</a></li>
                <li class="active"><a href="<@spring.url relativeUrl="/permit/view/people?id=${peoplePermitEntity.id}"/>">Люди</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/history?id=${peoplePermitEntity.id}"/>">История и действия</a></li>
            </ul>
            <form class="form-inline">
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
                                            <input class="span12" id="permitName" type="text" name="wr" value="${peoplePermitEntity.writer.initials}" readonly/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="permitName">Наряд-допуск принял:</label>
                                        <div class="controls">
                                            <input class="span12" id="permitName" type="text" readonly/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="permitName">Целевой инструктаж провёл:</label>
                                        <div class="controls">
                                            <input class="span12" id="permitName" type="text" readonly/>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="permitName">Целевой инструктаж получил:</label>
                                        <div class="controls">
                                            <input class="span12" id="permitName" type="text" readonly/>
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
                                            <#if peoplePermitEntity.responsibleSupervisor?has_content>
                                                <input class="span12" id="selectSupervisor" type="text" name="wr" value="(${peoplePermitEntity.responsibleSupervisor.personnelNumber?c}) ${peoplePermitEntity.responsibleSupervisor.initials} - ${peoplePermitEntity.responsibleSupervisor.department.abbreviatedName}" readonly/>
                                            <#else>
                                                <input class="span12" id="selectSupervisor" type="text" name="wr" readonly/>
                                            </#if>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="selectExecutor">Ответственный исполнитель работ:</label>
                                        <div class="controls">
                                            <#if peoplePermitEntity.responsibleExecutor?has_content>
                                                <input class="span12" id="selectExecutor" type="text" name="wr" value="(${peoplePermitEntity.responsibleExecutor.personnelNumber?c}) ${peoplePermitEntity.responsibleExecutor.initials} - ${peoplePermitEntity.responsibleExecutor.department.abbreviatedName}" readonly/>
                                            <#else>
                                                <input class="span12" id="selectExecutor" type="text" name="wr" readonly/>
                                            </#if>
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
                                                        </tr>
                                                    <#else>
                                                        <tr class="even">
                                                            <td class="center" id="row-emp-id-${emp.id}">${emp_index + 1}</td>
                                                            <td class="center" id="row-emp-name-${emp.id}">${emp.initials!""}</td>
                                                            <td class="center" id="row-emp-dep-${emp.id}">${emp.department.abbreviatedName!""}</td>
                                                            <td class="center" id="row-emp-post-${emp.id}">${emp.post.abbreviatedName!""} ${emp.post.rang!""} ${emp.post.typeRang!""}</td>
                                                        </tr>
                                                    </#if>
                                                </#list>
                                            </#if>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        <#-- Состав END -->
                        </div>
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
