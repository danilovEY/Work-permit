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
    <#include "navbar.ftl"/>

    <div class="container-fluid-full">
        <div class="row-fluid">

            <!-- start: Main Menu -->
            <div id="sidebar-left" class="span2">
                <div class="nav-collapse sidebar-nav">
                    <ul class="nav nav-tabs nav-stacked main-menu">
                        <li class="active">
                            <a href="/"><i class="icon-home"></i><span class="hidden-tablet"> Главная</span></a>
                        </li>
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