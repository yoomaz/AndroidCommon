package com.graypn.cmmon.net;

/**
 * Created by graypn on 16/1/13.
 */
public class GlobleNetApi extends BaseNetApi {


    public static String getUpdateUrl() {
        return "http://o8orjkglp.bkt.clouddn.com/szssmartparty.json";
    }

    // 根据thumb的id获取图片信息
    // http://www.szssz.party/index.php?c=image&m=thumb&c=596
    // http://www.szssz.party/index.php?&c=image&m=thumb&id=5191
    public static String getThumbFromId(String id) {
        String start = BASE_URL + "/index.php?c=image&m=thumb&p=";
        return start + id;
    }

    // 获取详细新闻api
    public static String getNewsHtml(String title, String editTime, String content) {
        String html = "<!DOCTYPE HTML>"
                + "<html>"
                + "<head>"
                + "<meta charset='utf-8'>"
                + "<meta content='width=device-width,user-scalable=no' name='viewport'>"
                + "<link href='http://7xqgy0.com1.z0.glb.clouddn.com/photoswipe.css' type='text/css' rel='stylesheet' />"
                + "<script type='text/javascript' src='http://7xqgy0.com1.z0.glb.clouddn.com/klass.min.js'></script>"
                + "<script type='text/javascript' src='http://7xqgy0.com1.z0.glb.clouddn.com/code.photoswipe-3.0.5.min.js'></script>"
                + "<link href='http://7xqgy0.com1.z0.glb.clouddn.com/newspage.css' rel='stylesheet' type='text/css' />"
                + "<script src='http://7xqgy0.com1.z0.glb.clouddn.com/jquery.js' type='text/javascript'></script>"
                + "<script src='http://7xqgy0.com1.z0.glb.clouddn.com/newspage.js?rand=3456677853' type='text/javascript'></script>"
                + "</head>"
                + "<body>" + "<article style='display: block;'>"
                + "<div>" + "<h1 class='subTitle'>" + title + "</h1>"
                + "<h2>" + editTime
                + "来源：石嘴山市直机关工委</h2>" + "</div>"
                + "<div id='content' style='color:#000000'>" + content + "</div>" + "</article>"
                + "<article style='display: block;'>"
                + "</article>" + "<br/>"
                + " </body>"
                + "</html> "
                + "<script type='text/javascript'>"
                + "$(function(){"
                + "$(\"img\").each(function(index){"
                + " $(this).css({width:\"100%\",height:\"auto\"});"
                + "});"
                + "});"
                + "</script>";

        return html;
    }

    // 获取详细新闻api
    public static String getWebHtml(String content) {
        String html = "<!DOCTYPE HTML>"
                + "<html>"
                + "<head>"
                + "<meta charset='utf-8'>"
                + "<meta content='width=device-width,user-scalable=no' name='viewport'>"
                + "<link href='http://7xqgy0.com1.z0.glb.clouddn.com/photoswipe.css' type='text/css' rel='stylesheet' />"
                + "<script type='text/javascript' src='http://7xqgy0.com1.z0.glb.clouddn.com/klass.min.js'></script>"
                + "<script type='text/javascript' src='http://7xqgy0.com1.z0.glb.clouddn.com/code.photoswipe-3.0.5.min.js'></script>"
                + "<link href='http://7xqgy0.com1.z0.glb.clouddn.com/newspage.css' rel='stylesheet' type='text/css' />"
                + "<script src='http://7xqgy0.com1.z0.glb.clouddn.com/jquery.js' type='text/javascript'></script>"
                + "<script src='http://7xqgy0.com1.z0.glb.clouddn.com/newspage.js?rand=3456677853' type='text/javascript'></script>"
                + "</head>"
                + "<body>" + "<article style=\"display: block;color:#000000\"'>"
                + "<div id='content' style='color:#000000'>" + content + "</div>" + "</article>"
                + "<br/>"
                + " </body>"
                + "</html> "
                + "<script type='text/javascript'>"
                + "$(function(){"
                + "$(\"img\").each(function(index){"
                + " $(this).css({width:\"100%\",height:\"auto\"});"
                + "});"
                + "});"
                + "</script>";

        return html;
    }
}
