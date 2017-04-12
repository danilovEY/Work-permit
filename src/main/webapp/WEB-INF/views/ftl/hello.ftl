<#-- @ftlvariable name="test" type="java.lang.String"-->
<#import "baseTemplate.ftl" as base>

<@base.override "body">
    <h1>${test}</h1>
</@base.override>

<@base.template/>