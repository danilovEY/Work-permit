<#-- @ftlvariable name="authEmployee" type="ru.kolaer.permit.entity.EmployeeEntity" -->
<#-- @ftlvariable name="notificationContents" type="ru.kolaer.permit.dto.NotificationContents" -->

<#import "/spring.ftl" as spring>

<#assign NEED_APPROVE_STATUS = "NEED_APPROVE_STATUS"/>
<#assign APPROVE_STATUS = "APPROVE_STATUS"/>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>

            <a class="brand" href="">
                <div id="image-1" class="masonry-thumb masonry-brick" style="width: 40px;">
                    <img class="grayscale" src="<@spring.url relativeUrl="/resources/img/aerIcon.png"/>" alt="Sample Image 1"/>
                </div>
                <span>Наряд-допуск</span>
            </a>

            <!-- start: Header Menu -->
            <div class="nav-no-collapse header-nav">
                <ul class="nav pull-right">
                    <li class="dropdown hidden-phone">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <#if notificationContents.count != 0>
                                    <i class="icon-bell-alt"></i>
                                    <span class="badge red">${notificationContents.count}</span>
                                <#else>
                                    <i class="icon-bell"></i>
                                </#if>
                            </a>
                            <#if notificationContents.count != 0>
                                <ul class="dropdown-menu notifications">
                                    <li class="dropdown-menu-title">
                                        <span>Непрочитанных уведомлений (${notificationContents.count})</span>
                                    <#--<a href="#refresh"><i class="icon-repeat"></i></a>-->
                                    </li>

                                    <#list notificationContents.notifications as notify>
                                        <li>
                                            <#if notify.type == NEED_APPROVE_STATUS>
                                                <a href="<@spring.url relativeUrl="/notification/redirect?id=${notify.id}"/>">
                                                    <span class="icon yellow" style="padding: 10px;"><i class="icon-question-sign"></i></span>
                                                    <span class="message">${notify.message}</span>
                                                    <span class="time">${notify.dateString}</span>
                                                </a>
                                            </#if>

                                            <#if notify.type == APPROVE_STATUS>
                                                <a href="<@spring.url relativeUrl="/notification/redirect?id=${notify.id}"/>">
                                                    <span class="icon green" style="padding: 10px;"><i class="icon-thumbs-up"></i></span>
                                                    <span class="message">${notify.message}</span>
                                                    <span class="time">${notify.dateString}</span>
                                                </a>
                                            </#if>
                                        </li>
                                    </#list>
                                    <li class="dropdown-menu-sub-footer">
                                        <a href="<@spring.url relativeUrl="/notification"/>">Показать все уведомления</a>
                                    </li>
                                </ul>
                            </#if>
                    </li>

                    <li class="dropdown">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="halflings-icon white user"></i> ${authEmployee.initials!"Ваш аккаунт"}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-menu-title">
                                <span>Ваш аккаунт</span>
                            </li>
                            <#--<li><a href="#"><i class="halflings-icon user"></i> Profile</a></li>-->
                            <li><a href="<@spring.url relativeUrl="/logout"/>"><i class="halflings-icon off"></i> Выход</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>