<#-- @ftlvariable name="department" type="ru.kolaer.permit.entity.DepartmentEntity" -->
<#-- @ftlvariable name="nameError" type="java.lang.String" -->
<#-- @ftlvariable name="abbreviatedNameError" type="java.lang.String" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "title">
<title>Редактировать подразделение: ${department.name}</title>
</@base.override>

<@base.override "body">

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white refresh"></i><span class="break"></span>Обновить подразделение</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/department/update"/>">
                <input type="text" class="span12 hidden" id="idUpdate" name="id" value="${department.id!""}" readonly/>

                <#if nameError?has_content>
                    <div class="control-group error">
                        <label class="control-label" for="name">Наименование: </label>
                        <div class="controls">
                            <input type="text" class="span12" id="name" name="name" value="${department.name!""}"/>
                            <span class="help-inline">${nameError}</span>
                        </div>
                    </div>
                <#else>
                    <div class="control-group">
                        <label class="control-label" for="name">Наименование: </label>
                        <div class="controls">
                            <input type="text" class="span12" id="name" name="name" value="${department.name!""}"/>
                        </div>
                    </div>
                </#if>

                <#if abbreviatedNameError?has_content>
                    <div class="control-group error">
                        <label class="control-label" for="abbreviatedName">Сокращенное наименование: </label>
                        <div class="controls">
                            <input type="text" class="span12" id="abbreviatedName" name="abbreviatedName" value="${department.abbreviatedName!""}"/>
                            <span class="help-inline">${abbreviatedNameError}</span>
                        </div>
                    </div>
                <#else>
                    <div class="control-group">
                        <label class="control-label" for="abbreviatedName">Сокращенное наименование: </label>
                        <div class="controls">
                            <input type="text" class="span12" id="abbreviatedName" name="abbreviatedName" value="${department.abbreviatedName!""}"/>
                        </div>
                    </div>
                </#if>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Обновить</button>
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/department"/>'">Отмена</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@base.override>

<@base.template/>