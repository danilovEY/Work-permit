<#-- @ftlvariable name="authEmployee" type="ru.kolaer.permit.entity.EmployeeEntity" -->

<#import "/spring.ftl" as spring>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href=""><span>Наряд-допуск</span></a>

            <!-- start: Header Menu -->
            <div class="nav-no-collapse header-nav">
                <ul class="nav pull-right">
                    <li class="dropdown hidden-phone">
                        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="icon-bell"></i>
                        </a>
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