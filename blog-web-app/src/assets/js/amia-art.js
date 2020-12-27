/**
 * Created by Administrator on 2020/11/25.
 */

$(function(){
    //页面加载完毕后开始执行的事件
    $(".comment-reply").click(function(){
        $(".comment-urp").remove();
        $(this).parent().parent().append("<div class=\"comment-urp\">\n" +
            "                            <img class=\"avatar-s\" src=\"../image/user2.jpg\" alt=\"\">\n" +
            "                            <div class=\"textarea-container\">\n" +
            "                                <textarea name=\"msg\" id=\"1060\" cols=\"80\" rows=\"50\" placeholder=\"发条友善的评论\" class=\"ipt-txt\"></textarea>\n" +
            "                                <button type=\"submit\" class=\"comment-submit\" disabled=\"disabled\">发表评论</button>\n" +
            "                            </div>\n" +
            "                        </div>");
    });
});