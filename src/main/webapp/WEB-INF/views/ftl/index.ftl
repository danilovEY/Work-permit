<#import "layout/baseTemplate.ftl" as base>

<@base.override "title">
    <title>Домашняя страница</title>
</@base.override>

<@base.override "body">
    <h1>Домашняя страница</h1>
<button class="btn btn-primary noty" data-noty-options='{"text":"This is an alert","layout":"top","type":"error","closeButton":"true"}'><i class="halflings-icon white white bell"></i> Bottom Full Width with Close Button</button>
</@base.override>

<@base.template/>