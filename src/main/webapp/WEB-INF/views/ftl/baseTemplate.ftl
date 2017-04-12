<#ftl encoding="UTF-8">
<#setting locale="ru">
<#global html_block = {}>
<#import "/spring.ftl" as spring>

<#macro override name>
    <#local  html_tmp><#nested></#local>
    <#local  html_tmp_inherited=html_block[name]!""/>
    <#global html_block = html_block + {name:(html_tmp+html_tmp_inherited)}>
</#macro>

<#macro block name>
    ${html_block[name]!""}
</#macro>

<#macro template>
<!DOCTYPE html>
<html lang="ru">

<head>
    <link rel="stylesheet" type="text/css" href="<@spring.url relativeUrl="/resources/css/bootstrap.min.css"/>"/>
    <script src="<@spring.url relativeUrl="/resources/js/jquery-3.2.1.min.js"/>"></script>
    <script src="<@spring.url relativeUrl="/resources/js/bootstrap.min.js"/>"></script>
</head>

<body>
    <div class="main">
        <div class="header">
            <#include "header.ftl"/>
        </div>

        <div class="content">
            <@block "body" />
        </div>

        <div class="footer">
            <#include "footer.ftl">
        </div>
    </div>
</body>

</html>

</#macro>