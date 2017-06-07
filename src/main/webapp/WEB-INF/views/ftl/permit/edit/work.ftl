<#-- @ftlvariable name="workPermitEntity" type="ru.kolaer.permit.entity.WorkPermitEntity" -->

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
                <li class="active"><a href="<@spring.url relativeUrl="/permit/edit/work?id=${workPermitEntity.id}"/>">Работа</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/event?id=${workPermitEntity.id}"/>">Условия и мероприятия</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/people?id=${workPermitEntity.id}"/>">Люди</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/view/history?id=${workPermitEntity.id}"/>">История</a></li>
            </ul>
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/permit/update/work"/>">
                <input type="hidden" name="id" value="${workPermitEntity.id!""}">
                <div id="myTabContent" class="tab-content">
                    <#-- FIRST_TAB -->
                    <div class="tab-pane active" id="work_tab">
                        <div class="row-fluid">
                            <#-- Описание BEGIN -->
                            <div class="box span6">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>Описание</h2>
                                </div>
                                <div class="box-content">
                                    <div class="control-group">
                                        <label class="control-label" for="serialNumber">Уникальный номер наряда:</label>
                                        <div class="controls">
                                            <#if workPermitEntity.serialNumber?has_content>
                                                <input class="span12" id="serialNumber" type="text" name="serialNumber" value="${workPermitEntity.serialNumber}" readonly>
                                            <#else>
                                                <input class="span12" id="serialNumber" type="text" name="serialNumber" value="">
                                            </#if>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="permitName">На выполнение работ:</label>
                                        <div class="controls">
                                            <input class="span12" id="permitName" type="text" placeholder="Наименование работ…" name="name" value="${workPermitEntity.name!""}">
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="placeWork">Место выполнения работ:</label>
                                        <div class="controls">
                                            <input class="span12" id="placeWork" type="text" name="placeWork" value="${workPermitEntity.placeWork!""}">
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="contentWork">Содержание работ:</label>
                                        <div class="controls">
                                            <textarea class="form-control span12" rows="5" id="contentWork" name="contentWork">${workPermitEntity.contentWork!""}</textarea>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="conditionWork">Условия проведения работ:</label>
                                        <div class="controls">
                                            <textarea class="form-control span12" rows="5" id="conditionWork" name="conditionWork">${workPermitEntity.conditionWork!""}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                             <#-- Описание END -->

                            <#-- Время BEGIN -->
                            <div class="box span6">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>Время</h2>
                                </div>
                                <div class="box-content">
                                    <div class="control-group">
                                        <label class="control-label" for="beginWorkDatePicker">Начало работ:</label>
                                        <div class="controls">
                                            <div id="beginWorkDatePicker" class="input-append date span12">
                                                <input class="span11" data-format="dd.MM.yyyy hh:mm" type="text" name="startWork" value="${workPermitEntity.startWork?string["dd.MM.yyyy hh:mm"]!""}"/>
                                                <span class="add-on">
                                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="endWorkDatePicker">Конец работ:</label>
                                        <div class="controls">
                                            <div id="endWorkDatePicker" class="input-append date span12">
                                                <input class="span11" data-format="dd.MM.yyyy hh:mm" type="text" name="endWork" value="${workPermitEntity.endWork?string["dd.MM.yyyy hh:mm"]!""}"/>
                                                <span class="add-on">
                                                    <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <#-- Время END -->

                            <#-- Инструметны BEGIN -->
                            <div class="box span6">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>Необходимые для производства работ</h2>
                                </div>
                                <div class="box-content">
                                    <div class="control-group">
                                        <label class="control-label" for="materials">Материалы:</label>
                                        <div class="controls">
                                            <input class="span12" id="materials" type="text" name="materials" value="${workPermitEntity.materials!""}">
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="instruments">Инструменты:</label>
                                        <div class="controls">
                                            <input class="span12" id="instruments" type="text" name="instruments" value="${workPermitEntity.instruments!""}">
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="adaptations">Приспособления:</label>
                                        <div class="controls">
                                            <input class="span12" id="adaptations" type="text" name="adaptations" value="${workPermitEntity.adaptations!""}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <#-- Инструметны END -->
                        </div>

                        <div class="row-fluid">
                        <#-- Безопасность BEGIN -->
                            <div class="box span6">
                                <div class="box-header">
                                    <h2><i class="halflings-icon white th"></i><span class="break"></span>Безопасность</h2>
                                </div>
                                <div class="box-content">
                                    <div class="control-group">
                                        <label class="control-label" for="retainingSystem">Удерживающие системы:</label>
                                        <div class="controls">
                                            <input class="span12" id="retainingSystem" type="text" name="retaining" value="${workPermitEntity.retaining!""}">
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="positionSystem">Системы позиционирования:</label>
                                        <div class="controls">
                                            <input class="span12" id="positionSystem" type="text" name="position" value="${workPermitEntity.position!""}">
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="safetySystem">Страховочные системы:</label>
                                        <div class="controls">
                                            <input class="span12" id="safetySystem" type="text" name="safety" value="${workPermitEntity.safety!""}">
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label" for="rescueSystem">Эвакуационные и спасательные системы:</label>
                                        <div class="controls">
                                            <input class="span12" id="rescueSystem" type="text" name="rescue" value="${workPermitEntity.rescue!""}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <#-- Безопасность END -->
                        </div>

                    </div>


                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/permit"/>'">Назад</button>
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
