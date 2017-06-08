<#-- @ftlvariable name="role" type="ru.kolaer.permit.entity.FullRoleEntity" -->
<#-- @ftlvariable name="employees" type="java.util.List<ru.kolaer.permit.entity.EmployeeEntity>" -->
<#-- @ftlvariable name="roleNameMap" type="java.util.Map<java.lang.String, java.lang.String>" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "title">
<title>Редактировать роль</title>
</@base.override>

<@base.override "body">
<div class="box span12">
    <div class="box-header" data-original-title="">
        <h2><i class="halflings-icon white refresh"></i><span class="break"></span>Обновить роль</h2>
    </div>
    <div class="box-content">
        <form class="form-inline" method="post" action="<@spring.url relativeUrl="/role/update"/>">

            <div class="control-group">
                <label class="control-label" for="id">ID: </label>
                <div class="controls">
                    <input type="text" class="span12" id="idUpdate" name="id" value="${role.id!""}" readonly/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="selectEmp">Сотрудник: </label>
                <div class="controls">
                    <select id="selectEmp" name="employee.id" data-rel="chosen">
                        <option disabled selected value> Сотрудник... </option>
                        <#if employees?has_content>
                            <#list employees as emp>
                                <#if emp.id == role.employee.id>
                                    <option selected="selected" value="${emp.id}">(${emp.personnelNumber}) ${emp.initials}</option>
                                <#else>
                                    <option value="${emp.id}">(${emp.personnelNumber}) ${emp.initials}</option>
                                </#if>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="updateRole">Роль: </label>
                <div class="controls">
                    <select id="updateRole" name="role" data-rel="chosen">
                        <option disabled selected value> Роли... </option>
                        <#if roleNameMap?has_content>
                            <#list roleNameMap?keys as key>
                                <#if key == role.role>
                                    <option selected="selected" value="${key}">${roleNameMap[key]}</option>
                                <#else>
                                    <option value="${key}">${roleNameMap[key]}</option>
                                </#if>

                            </#list>
                        </#if>
                    </select>
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Обновить</button>
                <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/role"/>'">Назад</button>
            </div>
        </form>
    </div>
</div>

</@base.override>

<@base.template/>