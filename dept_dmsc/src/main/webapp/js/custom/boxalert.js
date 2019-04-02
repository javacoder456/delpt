/**
 * Alert(title,msg) 普通弹框
 * Confirm(title, msg, callback) 提交回调弹框
 * AlertSysMsg(title,msg) 系统弹框
 *
 */
var MsgBox;
jQuery(document).ready(function(){
     MsgBox = {
        Alert: function (title, msg) {  
            GenerateHtml("alert", title, msg);
            btnOk();
            btnNo();
        },  
        Confirm: function (title, msg, callback) {  
            GenerateHtml("confirm", title, msg);
            btnOk(callback);
            btnNo();
        },
         Confirm: function (title, msg, callback,param) {
             GenerateHtml("confirm", title, msg);
             btnOk(callback,param);
             btnNo();
         },
        AlertSysMsg: function (title, msg) {
            GenerateHtml("alertSysMsg", title, msg);
            btnOk();
            btnNo();
        },
    }  
    //生成Html  
    var GenerateHtml = function (type, title, msg) {
        var _html = "";  
        _html += '<div id="mb_box"></div><div id="mb_con"><span id="mb_tit">' + title + '</span>';  
        _html += '<a id="mb_ico"><span><img src="images/blueline/searchcancel.png"/></span></a><div id="mb_msg">' + msg + '</div><div id="mb_btnbox">';
        if (type == "alert") {
            _html += '<input id="mb_btn_ok" type="button" value="确定"/>';
        }
        if (type == "confirm") {
            _html += '<input id="mb_btn_ok" type="button" value="确定" />';
            _html += '<input id="mb_btn_no" type="button" value="取消" />';
        }
        if (type == "alertSysMsg"){
            _html += '<input id="mb_btn_ok" type="button" value="确定" />';
        }
        _html += '</div></div>';
        //必须先将_html添加到body，再设置Css样式
        jQuery("#mainBody").append(_html);
        //生成Css
        GenerateCss(type);
    }  
  
    //生成Css  
    var GenerateCss = function (type) {

        jQuery("#mb_box").css({ width: '100%', height: '100%', zIndex: '99999', position: 'fixed',
            filter: 'Alpha(opacity=60)', backgroundColor: 'black', top: '0', left: '0', opacity: '0.6'});

        jQuery("#mb_con").css({ zIndex: '999999', width: '400px', position: 'fixed',
            backgroundColor: 'White', borderRadius: '15px'
        });
        jQuery("#mb_tit").css({ display: 'block', fontSize: '14px', color: '#444', padding: '10px 15px',
            backgroundColor: '#DDD', borderRadius: '15px 15px 0 0',
            borderBottom: '3px solid #009BFE', fontWeight: 'bold'
        });
        jQuery("#mb_msg").css({ padding: '20px', lineHeight: '20px',
            borderBottom: '1px dashed #DDD', fontSize: '13px'
        });
        jQuery("#mb_ico").css({ display: 'block', position: 'absolute', right: '10px', top: '9px',
            width: '18px', height: '18px', textAlign: 'center', lineHeight: '16px', fontFamily: '微软雅黑',
            cursor: 'pointer', borderRadius: '12px'
        });
        jQuery("#mb_btnbox").css({ margin: '15px 0 10px 0', textAlign: 'center' });  
        jQuery("#mb_btn_ok,#mb_btn_no").css({ width: '85px', height: '30px', color: 'white', border: 'none' });  
        jQuery("#mb_btn_ok").css({ backgroundColor: '#168bbb' });  
        jQuery("#mb_btn_no").css({ backgroundColor: 'gray', marginLeft: '20px' });  
        //右上角关闭按钮hover样式  
        /*jQuery("#mb_ico").hover(function () {  
            jQuery(this).css({ backgroundColor: '#red', color: 'White' });  
        }, function () {  
            jQuery(this).css({ backgroundColor: '#ddd', color: 'black' });  
        });*/  
        var _widht = document.documentElement.clientWidth;  //屏幕宽  
        var _height = document.documentElement.clientHeight; //屏幕高  
        var boxWidth = jQuery("#mb_con").width();  
        var boxHeight = jQuery("#mb_con").height();  
        //让提示框居中
        if(type == "alertSysMsg"){
            jQuery("#mb_con").css({ top: (_height - boxHeight - 10) + "px", left: (_widht - boxWidth - 5 ) + "px" });
        }else{
            jQuery("#mb_con").css({ top: (_height - boxHeight) / 2 + "px", left: (_widht - boxWidth) / 2 + "px" });
        }
    }
    //确定按钮事件  
    var btnOk = function(callback) {
        jQuery('#mb_btn_ok').bind('click', function(){
            jQuery("#mb_box,#mb_con").remove();
            if (typeof (callback) == 'function') {
                callback();
            }
        });

    }
    //确定按钮事件
    var btnOk = function(callback,param) {
        jQuery('#mb_btn_ok').bind('click', function(){
            jQuery("#mb_box,#mb_con").remove();
            if (typeof (callback) == 'function') {
                callback(param);
            }
        });

    }
    //取消按钮事件  
    var btnNo = function() {
        jQuery('#mb_btn_no,#mb_ico').bind('click', function(){
            jQuery("#mb_box,#mb_con").remove();
        });
    }
});