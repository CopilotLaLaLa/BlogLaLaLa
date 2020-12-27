/**
 * Created by Administrator on 2020/10/13.
 */
$(function(){
        $('#her').hover(function () {
            if(window.scrollY == 0) {
                $('#her').addClass('sticky');
            }
        }, function() {
            if(window.scrollY == 0) {
                $('#her').removeClass('sticky');
            }
        });
});
