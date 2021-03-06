<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="ru">

<head>
    <#include "layout/head.ftl"/>
    <title>Вход в систему</title>
    <style type="text/css">
        body { background: url(<@spring.url relativeUrl="/resources/img/bg-login.jpg"/>) !important; }
    </style>
    <style type="text/css">.jqstooltip { position: absolute;left: 0px;top: 0px;visibility: hidden;background: rgb(0, 0, 0) transparent;background-color: rgba(0,0,0,0.6);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";color: white;font: 10px arial, san serif;text-align: left;white-space: nowrap;padding: 5px;border: 1px solid white;z-index: 10000;}.jqsfield { color: white;font: 10px arial, san serif;text-align: left;}</style>
</head>
<body>
    <div class="container-fluid-full">
        <div class="row-fluid">
            <div class="login-box">
                <div class="icons">
                    <#if logout>
                        <div class="alert alert-info">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            Успешный выход из системы.
                        </div>
                    </#if>
                    <#if access>
                        <div class="alert alert-block">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            Необходимо авторизоватся в системе.
                        </div>
                    </#if>
                    <#if timeout>
                        <div class="alert alert-block">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            Время сессии истекло.
                        </div>
                    </#if>
                    <#if expired>
                        <div class="alert alert-error">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            Под аккаунтом авторизовались больше <strong>двух</strong> раз.
                        </div>
                    </#if>
                    <#if error>
                        <div class="alert alert-error">
                            <button type="button" class="close" data-dismiss="alert">×</button>
                            Не удалось авторизоватся в системе.
                        </div>
                    </#if>
                </div>
                <h2>Войдите в систему</h2>
                <form class="form-horizontal" action="<@spring.url relativeUrl="/login"/>" method="post">
                    <fieldset>
                        <div class="input-prepend" title="Username">
                            <span class="add-on"><i class="halflings-icon user"></i></span>
                            <input class="input-large span10" name="username" id="username" type="text" placeholder="Ф.И.О.">
                        </div>
                        <div class="clearfix"></div>

                        <div class="input-prepend" title="Password">
                            <span class="add-on"><i class="halflings-icon lock"></i></span>
                            <input class="input-large span10" name="password" id="password" type="password" placeholder="Пароль">
                        </div>
                        <div class="clearfix"></div>

                        <#--<label class="remember" for="remember-me">-->
                            <#--<input type="checkbox" name="remember-me" id="remember-me">Запомнить меня-->
                        <#--</label>-->

                        <div class="button-login">
                            <button type="submit" class="btn btn-primary">Войти</button>
                        </div>
                        <div class="clearfix"></div>
                    <#--<#if error>-->
                        <#--<h3>Забыли пароль?</h3>-->
                        <#--<p>-->
                            <#--No problem, <a href="#">click here</a> to get a new password.-->
                        <#--</p>-->
                    <#--</#if>-->
                    </fieldset>
                    <#--<input type="hidden"-->
                           <#--name="${_csrf.parameterName}"-->
                           <#--value="${_csrf.token}"/>-->
                </form>
            </div><!--/span-->
        </div><!--/row-->
    </div><!--/.fluid-container-->

    <#include "layout/scripts.ftl"/>
</body>

</html>