<#-- @ftlvariable name="workEvent" type="ru.kolaer.permit.entity.WorkEvent" -->
<#-- @ftlvariable name="employees" type="java.util.List<ru.kolaer.permit.entity.EmployeeEntity>" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "title">
<title>Добавить мероприятие</title>
</@base.override>

<@base.override "body">
<script src="<@spring.url relativeUrl="/resources/js/bootstrap-datetimepicker.js"/>"></script>
<script src="<@spring.url relativeUrl="/resources/js/bootstrap-datetimepicker.ru.js"/>"></script>

<div class="row-fluid">

    <div class="box span12">
        <div class="box-header">
            <h2><i class="halflings-icon white th"></i><span class="break"></span>Добавить мероприятие</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/event/add"/>">

                <input type="hidden" name="permit.id" value="${workEvent.permitId}">

                <div class="control-group hidden">
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
                    <label class="control-label" for="beginLimitDatePickerInput">Срок выполнения:</label>
                    <div class="controls">
                        <div id="beginLimitDatePicker" class="input-append date span12">
                            <input class="span11" id="beginLimitDatePickerInput" data-format="dd.MM.yyyy hh:mm" type="text" name="limitDate" value="${(workEvent.limitDate!.now)?string["dd.MM.yyyy hh:mm"]}"/>
                            <span class="add-on">
                                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="eventName">Наименование/Описание мероприятий:</label>
                    <div class="controls">
                        <input id="eventName" class="span12" type="text" name="name" value="${workEvent.name!""}"/>
                    </div>
                </div>


                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/permit/edit/event?id=${workEvent.permitId}"/>'">Отмена</button>
                </div>

            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function() {
        $('#beginLimitDatePicker').datetimepicker({
            language: 'ru',
            format: 'dd.MM.yyyy hh:mm',
            pickSeconds: false
        });
    });

</script>

</@base.override>

<@base.template/>
