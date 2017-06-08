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
    <#include "head.ftl"/>
    <@block "title"/>
</head>

<body>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

    <#include "navbar.ftl"/>

    <div class="container-fluid-full">
        <div class="row-fluid">

            <!-- start: Main Menu -->
            <div id="sidebar-left" class="span2">
                <div class="nav-collapse sidebar-nav">
                    <ul class="nav nav-tabs nav-stacked main-menu">
                        <li><a href="<@spring.url relativeUrl="/"/>"><i class="icon-home"></i><span class="hidden-tablet"> Главная</span></a></li>
                        <li><a href="<@spring.url relativeUrl="/permit"/>"><i class="icon-list-alt"></i><span class="hidden-tablet"> Наряды</span></a></li>
                        <li><a href="<@spring.url relativeUrl="/department"/>"><i class="icon-th-large"></i><span class="hidden-tablet"> Подразделения</span></a></li>
                        <li><a href="<@spring.url relativeUrl="/post"/>"><i class="icon-th"></i><span class="hidden-tablet"> Дожности</span></a></li>
                        <li><a href="<@spring.url relativeUrl="/employee"/>"><i class="icon-user"></i><span class="hidden-tablet"> Сотрудники</span></a></li>
                        <@security.authorize access="hasRole('ROLE_DB_ADMIN')">
                            <li><a href="<@spring.url relativeUrl="/role"/>"><i class="icon-group"></i><span class="hidden-tablet"> Роли</span></a></li>
                        </@security.authorize>
                    </ul>
                </div>
            </div>
            <!-- end: Main Menu -->

            <noscript>
                &lt;div class="alert alert-block span10"&gt;
                &lt;h4 class="alert-heading"&gt;Warning!&lt;/h4&gt;
                &lt;p&gt;You need to have &lt;a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank"&gt;JavaScript&lt;/a&gt; enabled to use this site.&lt;/p&gt;
                &lt;/div&gt;
            </noscript>

            <!-- start: Content -->
            <div id="content" class="span10" style="min-height: 1024px;">

                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home"></i>
                        <a href="<@spring.url relativeUrl="/"/>">Главная</a>
                    </li>
                </ul>

                <@block "body"/>

            </div><!--/.fluid-container-->

            <!-- end: Content -->
        </div><!--/#content.span10-->
    </div>

    <div class="clearfix"></div>

    <#include "footer.ftl">

    <#include "scripts.ftl">

</body>

</html>

</#macro>