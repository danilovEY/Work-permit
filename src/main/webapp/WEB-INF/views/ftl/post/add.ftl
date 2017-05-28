<#-- @ftlvariable name="post" type="ru.kolaer.permit.entity.PostEntity" -->

<#import "../layout/baseTemplate.ftl" as base>
<#import "/spring.ftl" as spring>

<@base.override "body">
<div class="row-fluid">
    <div class="box span12">
        <div class="box-header" data-original-title="">
            <h2><i class="halflings-icon white plus"></i><span class="break"></span>Добавить должность</h2>
        </div>
        <div class="box-content">
            <form class="form-inline" method="post" action="<@spring.url relativeUrl="/post/add"/>">
                <label class="control-label" for="name">Наименование: </label>
                <div class="controls">
                    <input type="text" class="span12" id="nameUpdate" name="name" value="${post.name!""}"/>
                </div>

                <label class="control-label" for="abbreviatedName">Сокращенное наименование: </label>
                <div class="controls">
                    <input type="text" class="span12" id="abbreviatedNameUpdate" name="abbreviatedName" value="${post.abbreviatedName!""}"/>
                </div>

                <label class="control-label" for="rang">Номер группы: </label>
                <div class="controls">
                    <input type="number" class="span12 rang" id="rangUpdate" name="rang" min="0" data-bind="value:replyNumber" value="${post.rang!""}"/>
                </div>

                <label class="control-label" for="typeRang">Наименование группы: </label>
                <div class="controls">
                    <input type="text" class="span12 typeRang" id="typeRangUpdate" name="typeRang" value="${post.typeRang!""}">
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                    <button type="reset" class="btn" onclick="window.location.href='<@spring.url relativeUrl="/post"/>'">Отмена</button>
                </div>
            </form>
        </div>
    </div>
</div>

</@base.override>

<@base.template/>