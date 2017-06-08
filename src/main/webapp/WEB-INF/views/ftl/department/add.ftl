<#-- @ftlvariable name="department" type="ru.kolaer.permit.entity.DepartmentEntity" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "title">
    <title>Добавить подразделение</title>
</@base.override>

<@base.override "body">

<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white plus"></i><span class="break"></span>Добавить подразделение</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/department/add"/>">
                <div class="control-group">
                    <label class="control-label" for="name">Наименование: </label>
                    <div class="controls">
                        <input type="text" class="span12" id="nameUpdate" name="name" value="${department.name!""}"/>
                    </div>

                    <label class="control-label" for="abbreviatedName">Сокращенное наименование: </label>
                    <div class="controls">
                        <input type="text" class="span12" id="abbreviatedNameUpdate" name="abbreviatedName" value="${department.abbreviatedName!""}"/>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/department"/>'">Отмена</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@base.override>

<@base.template/>