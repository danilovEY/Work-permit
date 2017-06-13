<#import "layout/baseTemplate.ftl" as base>

<@base.override "title">
    <title>Домашняя страница</title>
</@base.override>

<@base.override "body">
    <h1>Домашняя страница</h1>
<a href="#" class="btn btn-info btn-setting" id="myBut">Click for dialog</a>

<div class="modal hide fade" id="myModal1">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Settings</h3>
    </div>
    <div class="modal-body">
        <p>Here settings can be configured...</p>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">Close</a>
        <a href="#" class="btn btn-primary">Save changes</a>
    </div>
</div>

<script>
    $('#myBut').click(function(e){
        e.preventDefault();
        $('#myModal1').modal('show');
    });
</script>
</@base.override>

<@base.template/>