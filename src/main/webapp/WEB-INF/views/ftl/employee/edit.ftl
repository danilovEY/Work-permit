<#-- @ftlvariable name="employee" type="ru.kolaer.permit.entity.EmployeeEntity" -->
<#-- @ftlvariable name="departments" type="java.util.List<ru.kolaer.permit.entity.DepartmentEntity>" -->
<#-- @ftlvariable name="posts" type="java.util.List<ru.kolaer.permit.entity.PostEntity>" -->


<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<#assign dateNow = .now>
<#assign MALE = "Мужчина">
<#assign FEMALE = "Женщина">

<@base.override "title">
<title>Редактировать сотрудника: ${employee.personnelNumber}</title>
</@base.override>

<@base.override "body">
<script src="<@spring.url relativeUrl="/resources/js/bootstrap-datetimepicker.js"/>"></script>
<script src="<@spring.url relativeUrl="/resources/js/bootstrap-datetimepicker.ru.js"/>"></script>

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white refresh"></i><span class="break"></span>Обновить сотрудника</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/employee/update"/>">
                <div class="control-group">
                    <label class="control-label" for="id">ID: </label>
                    <div class="controls">
                        <input type="text" class="span12" id="idUpdate" name="id" value="${employee.id!""}" readonly/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="personnelNumber">Табельный номер: </label>
                    <div class="controls">
                        <input type="number" class="span12" id="personnelNumber" name="personnelNumber" min="0" data-bind="value:replyNumber" value="${(employee.personnelNumber!0)?c}"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="name">Ф.И.О.: </label>
                    <div class="controls">
                        <input type="text" class="span12" id="nameUpdate" name="initials" value="${employee.initials!""}"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="birthdayDatePicker">Дата рождения:</label>
                    <div class="controls">
                        <div id="birthdayDatePicker" class="input-append date span12">
                            <input class="span11" data-format="dd.MM.yyyy" type="text" name="birthday" value="${(employee.birthday!dateNow)?string["dd.MM.yyyy"]}"/>
                            <span class="add-on">
                                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="gender">Пол: </label>
                    <div class="controls">
                        <select class="span12" id="gender" name="gender">
                            <option value="${MALE}" <#if employee.gender == MALE> selected="selected" </#if>>${MALE}</option>
                            <option value="${FEMALE}" <#if employee.gender == MALE> selected="selected" </#if>>${FEMALE}</option>
                        </select>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="selectDep">Подразделение: </label>
                    <div class="controls">
                        <select id="selectDep" name="department.id" data-rel="chosen">
                            <option disabled selected value> Подразделения... </option>
                            <#if departments?has_content>
                                <#list departments as dep>
                                    <#if dep.id == employee.department.id>
                                        <option selected="selected" value="${dep.id}">${dep.name}</option>
                                    <#else>
                                        <option value="${dep.id}">${dep.name}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="selectPost">Должность: </label>
                    <div class="controls">
                        <select id="selectPost" name="post.id" data-rel="chosen">
                            <option disabled selected value> Должности... </option>
                            <#if posts?has_content>
                                <#list posts as post>
                                    <#if post.id == employee.post.id>
                                        <option selected="selected" value="${post.id}">${post.name} ${post.rang!""} ${post.typeRang!""}</option>
                                    <#else>
                                        <option value="${post.id}">${post.name} ${post.rang!""} ${post.typeRang!""}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="name">Рабочие телефоны: </label>
                    <div class="controls">
                        <input type="text" class="span12" name="workPhone" value="${employee.workPhone!""}"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="name">E-mail: </label>
                    <div class="controls">
                        <input type="text" class="span12" name="email" value="${employee.email!""}"/>
                    </div>
                </div>

                <div class="row-fluid" style="margin-top: 10px;">
                    <div class="box span12">
                        <div class="box-header" data-original-title="">
                            <h2><i class="halflings-icon white refresh"></i><span class="break"></span>Учетная запись</h2>
                        </div>
                        <div class="box-content">
                            <div class="control-group">
                                <label class="checkbox">
                                    <div class="checker" id="uniform-inlineCheckbox1">
                                        <span class="">
                                            <input type="checkbox" id="defaultAccount" value="option1">
                                        </span>
                                    </div> Задать табельный номер как логин и пароль
                                </label>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="username">Логин: </label>
                                <div class="controls">
                                    <input type="text" class="span12" id="username" name="username" value="${employee.username!""}"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="password">Пароль: </label>
                                <div class="controls">
                                    <input type="text" class="span12" id="password" name="password" value="${employee.password!""}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Обновить</button>
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/employee"/>'">Отмена</button>
                </div>
            </form>
        </div>
    </div>

</div>

<script type="text/javascript">
    var personnelNumber = document.getElementById('personnelNumber').value;
    var userElement = document.getElementById('username');
    var passElement = document.getElementById('password');

    document.getElementById('defaultAccount').onchange = function() {
        userElement.disabled = this.checked;
        passElement.disabled = this.checked;
        userElement.value = "";
        passElement.value = "";
    };

    var username = userElement.value;
    var password = passElement.value;

    if(personnelNumber == username && personnelNumber == password) {
        document.getElementById('defaultAccount').checked = true;
        userElement.disabled = true;
        passElement.disabled = true;
        userElement.value = "";
        passElement.value = "";
    } else {
        document.getElementById('defaultAccount').checked = false;
    }

</script>

<script type="text/javascript">
    $(function() {
        $('#birthdayDatePicker').datetimepicker({
            language: 'ru',
            format: 'dd.MM.yyyy'
        });
    });



</script>

</@base.override>

<@base.template/>