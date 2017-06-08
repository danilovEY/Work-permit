<#-- @ftlvariable name="role" type="ru.kolaer.permit.entity.FullRoleEntity" -->
<#-- @ftlvariable name="employees" type="java.util.List<ru.kolaer.permit.entity.EmployeeEntity>" -->
<#-- @ftlvariable name="roleNameMap" type="java.util.Map<java.lang.String, java.lang.String>" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "title">
<title>Добавить роль</title>
</@base.override>

<@base.override "body">
<div class="box span12">
    <div class="box-header" data-original-title="">
        <h2><i class="halflings-icon white refresh"></i><span class="break"></span>Добавить роль</h2>
    </div>
    <div class="box-content">
        <form class="form-inline" method="post" action="<@spring.url relativeUrl="/role/add"/>">
            <div class="control-group">
                <label class="control-label" for="selectEmp">Сотрудник: </label>
                <div class="controls">
                    <select id="selectEmp" name="employee.id" data-rel="chosen">
                        <option disabled selected value> Сотрудник... </option>
                        <#if employees?has_content>
                            <#list employees as emp>
                                <option value="${emp.id}">(${emp.personnelNumber}) ${emp.initials}</option>
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
                                <option value="${key}">${roleNameMap[key]}</option>
                            </#list>
                        </#if>
                    </select>
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Добавить</button>
                <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/role"/>'">Отмена</button>
            </div>
        </form>
    </div>
</div>

</@base.override>

<@base.template/>