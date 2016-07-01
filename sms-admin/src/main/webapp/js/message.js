var msgview = {
    main:"",
    dev:function(option){
        main = option.main;
        c = "";
        if(main){
            c = main.split("?");
            $("#main").attr("src",c[0]);
        }
    }
}

$(function(){
    msgview.dev({
        "main":"/message/sms"
    })
    $(".content-right").on("click",function(){
        $("#main").attr("src",$(this).attr("href"));
        return false;
    })
    $("#modal").on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var recipient = button.data('whatever')
        var qo = button.data("name");
        var action = button.data("action");
        var modal = $(this)
        modal.find('.modal-title').text('修改 ' + recipient)
        modal.find("#message-text").text(qo.split("|")[0]);
        modal.find("#message-ch").text(recipient);
        modal.find('.modal-body input').val(qo.split("|")[1]);
        modal.find("form").attr("action",action);
    })
})