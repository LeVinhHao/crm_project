$(document).ready(function(){
    $('.btn_xoa').click(function(){
        const id = $(this).attr('id')
        const This = $(this)
        $.ajax({
            method:'GET',
            url: `http://localhost:8080/api/user/delete?id=${id}`,
//            data:
        }).done(function(data){
            if(data.data){
                This.closest('tr').remove()
                console.log("xoa thanh cong");
            }else{
                console.log("xoa that bai");
            }
        })
    })
})