<#-- @ftlvariable name="permit" type="ru.kolaer.permit.entity.ShortPermitEntity" -->
<#-- @ftlvariable name="statuses" type="java.util.List<ru.kolaer.permit.entity.PermitStatusHistoryEntity>" -->

<#import "../../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<#assign EDIT_PERMIT_STATUS = "Редактирование"/>
<#assign NEED_APPROVE_PERMIT_STATUS = "Запрос на согласование"/>
<#assign APPROVE_STATUS = "Согласовано"/>
<#assign PERMIT_STATUS = "Допуск"/>
<#assign CANCELED_STATUS = "Отменен"/>
<#assign WORKING_STATUS = "В работе"/>
<#assign OVERDUE_STATUS = "Просрочен"/>
<#assign DELETED_STATUS = "Удален"/>
<#assign END_STATUS = "Завершен"/>

<#assign ROLE_DB_ADMIN = "hasRole('ROLE_DB_ADMIN')"/>
<#assign ROLE_USER = "hasRole('ROLE_USER')"/>
<#assign ROLE_APPROVE = "hasRole('ROLE_APPROVE')"/>
<#assign ROLE_PERMIT = "hasRole('ROLE_PERMIT')"/>

<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />

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
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/work?id=${permit.id}"/>">Работа</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/event?id=${permit.id}"/>">Условия и мероприятия</a></li>
                <li class=""><a href="<@spring.url relativeUrl="/permit/edit/people?id=${permit.id}"/>">Люди</a></li>
                <li class="active"><a href="<@spring.url relativeUrl="/permit/edit/history?id=${permit.id}"/>">История и действия</a></li>
            </ul>
            <form class="form-inline">
                <div id="myTabContent" class="tab-content">
                    <#-- FIRST_TAB -->
                    <div class="tab-pane active" id="history_tab">

                        <div class="box span6">
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
                                            <#if status.status == NEED_APPROVE_PERMIT_STATUS>
                                                <span class="label label-warning">${status.status}</span>
                                            <#elseif status.status == CANCELED_STATUS>
                                                <span class="label label-important">${status.status}</span>
                                            <#elseif status.status == APPROVE_STATUS>
                                                <span class="label label-info">${status.status}</span>
                                            <#elseif status.status == PERMIT_STATUS>
                                                <span class="label label-success">${status.status}</span>
                                            <#elseif status.status == WORKING_STATUS>
                                               <span class="label label-success">${status.status}</span>
                                            <#elseif status.status == OVERDUE_STATUS>
                                                <span class="label label-important">${status.status}</span>
                                            <#elseif status.status == DELETED_STATUS>
                                                <span class="label label-inverse">${status.status}</span>
                                            <#elseif status.status == END_STATUS>
                                                <span class="label label-success">${status.status}</span>
                                            <#else>
                                                <span class="label">${status.status}</span>
                                            </#if>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                        </div><!--/span-->

                        <div class="box span6">
                            <div class="box-header">
                                <h2><i class="halflings-icon white user"></i><span class="break"></span>Действия</h2>
                            </div>
                            <div class="box-content">
                                    <a class="btn btn-success" style="margin-bottom: 4px;" title="Получить бланк" href="<@spring.url relativeUrl="/permit/download/excel?id=${permit.id}"/>">
                                        <i class="halflings-icon white print"></i> Получить бланк
                                    </a>

                                    <#if statuses?first.status == WORKING_STATUS>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Завершить наряд" id="end-but-${permit.id}" href="<@spring.url relativeUrl="/permit/action/end?id=${permit.id}"/>">
                                            <i class="halflings-icon white ok"></i> Завершить наряд
                                        </a>

                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Продлить наряд" id="extend-but-${permit.id}" href="#">
                                            <i class="halflings-icon white time"></i> Продлить наряд
                                        </a>
                                    </#if>

                                    <#if statuses?first.status == APPROVE_STATUS>
                                        <@security.authorize access=ROLE_PERMIT>
                                            <a class="btn btn-info" style="margin-bottom: 4px;" title="Допустить" id="permit-but-${permit.id}" href="#">
                                                <i class="halflings-icon white thumbs-up"></i> Допустить
                                            </a>
                                        </@security.authorize>
                                    </#if>

                                    <#if statuses?first.status == EDIT_PERMIT_STATUS>
                                        <a class="btn btn-info" style="margin-bottom: 4px;" title="Запрос на согласование" id="need-approve-but-${permit.id}" href="#">
                                            <i class="halflings-icon white question-sign"></i> Запрос на согласование
                                        </a>
                                        <a class="btn btn-success" style="margin-bottom: 4px;" title="Редактировать" href="<@spring.url relativeUrl="/permit/edit/work?id=${permit.id}"/>">
                                            <i class="halflings-icon white edit"></i> Редактировать
                                        </a>
                                        <a class="btn btn-danger" style="margin-bottom: 4px;" title="Удалить" id="delete-but-${permit.id}" href="#">
                                            <i class="halflings-icon white trash"></i> Удалить
                                        </a>
                                    </#if>

                                    <#if statuses?first.status == NEED_APPROVE_PERMIT_STATUS>
                                        <@security.authorize access=ROLE_APPROVE>
                                            <a class="btn btn-info" style="margin-bottom: 4px;" title="Согласовать" id="approve-but-${permit.id}" href="#">
                                                <i class="halflings-icon white ok-circle"></i> Согласовать
                                            </a>
                                            <a class="btn btn-danger" style="margin-bottom: 4px;" title="Отменить" id="cancel-but-${permit.id}" href="#">
                                                <i class="halflings-icon white ban-circle"></i> Отменить
                                            </a>
                                        </@security.authorize>
                                    </#if>
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

    <#if permit.status == WORKING_STATUS>
    <#-- Запрос на продление -->
    <div class="modal hide fade" id="extend-${permit.id}">
        <form class="form-horizontal" method="post" action="<@spring.url relativeUrl="/permit/action/extend"/>">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Продление наряда</h3>
            </div>
            <div class="modal-body">
                <input class="hidden" name="id" value="${permit.id}" readonly/>
                <div class="control-group">
                    <label class="control-label" for="extendWorkDate">Начало работ:</label>
                    <div class="controls">
                        <div id="extendWorkDatePicker-${permit.id}" class="input-append date span12">
                            <input data-format="dd.MM.yyyy hh:mm" id="extendWorkDate" type="text" name="extendedPermit" value="${permit.extendedPermit?string["dd.MM.yyyy hh:mm"]}"/>
                            <span class="add-on">
                                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button href="#" class="btn" data-dismiss="modal">Отмена</button>
                <button class="btn btn-primary">Продлить</button>
            </div>
        </form>
    </div>
    <style>
        .bootstrap-datetimepicker-widget{z-index:9999 !important;}
    </style>
    <script type="text/javascript">
        $(function() {
            $('#extendWorkDatePicker-${permit.id}').datetimepicker({
                language: 'ru',
                format: 'dd.MM.yyyy hh:mm'
            });
        });

        $('#extend-but-${permit.id}').click(function(e){
            e.preventDefault();
            $('#extend-${permit.id}').modal('show');

        });
    </script>
    </#if>

    <#if permit.status == EDIT_PERMIT_STATUS>
    <#-- Запрос на удаление -->
    <div class="modal hide fade" id="delete-${permit.id}">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>Запрос на удаление</h3>
        </div>
        <div class="modal-body">
            <p>Вы действительно хотите удалить наряд: "${permit.serialNumber}"?</p>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">Отмена</a>
            <a href="<@spring.url relativeUrl="/permit/delete?id=${permit.id}"/>" class="btn btn-primary">Удалить</a>
        </div>
    </div>
    <script>
        $('#delete-but-${permit.id}').click(function(e){
            e.preventDefault();
            $('#delete-${permit.id}').modal('show');
        });
    </script>

    <#-- Запрос на согласование -->
    <div class="modal hide fade" id="need-approve-${permit.id}">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>Запрос на согласование</h3>
        </div>
        <div class="modal-body">
            <p>Вы действительно хотите отправить наряд "${permit.serialNumber}" на согласование?</p>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">Отмена</a>
            <a href="<@spring.url relativeUrl="/permit/action/need/approve?id=${permit.id}"/>" class="btn btn-primary">Подтвердить</a>
        </div>
    </div>
    <script>
        $('#need-approve-but-${permit.id}').click(function(e){
            e.preventDefault();
            $('#need-approve-${permit.id}').modal('show');
        });
    </script>
    </#if>

    <#if permit.status == NEED_APPROVE_PERMIT_STATUS>
    <#-- Согласование -->
    <div class="modal hide fade" id="approve-${permit.id}">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>Согласование</h3>
        </div>
        <div class="modal-body">
            <p>Вы действительно хотите согласовать наряд: "${permit.serialNumber}"?</p>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">Отмена</a>
            <a href="<@spring.url relativeUrl="/permit/action/approve?id=${permit.id}"/>" class="btn btn-primary">Подтвердить</a>
        </div>
    </div>
    <script>
        $('#approve-but-${permit.id}').click(function(e){
            e.preventDefault();
            $('#approve-${permit.id}').modal('show');
        });
    </script>

    <#-- Отмена -->
    <div class="modal hide fade" id="cancel-${permit.id}">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>Отмена</h3>
        </div>
        <div class="modal-body">
            <p>Вы действительно хотите отменить наряд: "${permit.serialNumber}"?</p>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">Отмена</a>
            <a href="<@spring.url relativeUrl="/permit/action/cancel?id=${permit.id}"/>" class="btn btn-primary">Подтвердить</a>
        </div>
    </div>
    <script>
        $('#cancel-but-${permit.id}').click(function(e){
            e.preventDefault();
            $('#cancel-${permit.id}').modal('show');
        });
    </script>
    </#if>

    <#if permit.status == APPROVE_STATUS>
    <#-- Допуск -->
    <div class="modal hide fade" id="permit-${permit.id}">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>Допуск</h3>
        </div>
        <div class="modal-body">
            <p>Вы действительно хотите допустить наряд: "${permit.serialNumber}"?</p>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">Отмена</a>
            <a href="<@spring.url relativeUrl="/permit/action/permit?id=${permit.id}"/>" class="btn btn-primary">Подтвердить</a>
        </div>
    </div>
    <script>
        $('#permit-but-${permit.id}').click(function(e){
            e.preventDefault();
            $('#permit-${permit.id}').modal('show');
        });
    </script>
    </#if>

</@base.override>

<@base.template/>
