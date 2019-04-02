jQuery(document).ready(function(){
	/*页面蒙层*/
    // 获取对象
	 var $ = function (id){
         return document.getElementById(id);
	 };
	
	 // 遍历
	 var each = function(a, b) {
	         for (var i = 0, len = a.length; i < len; i++) b(a[i], i);
	 };
	
	 // 事件绑定
	 var bind = function (obj, type, fn) {
	         if (obj.attachEvent) {
	                 obj['e' + type + fn] = fn;
	                 obj[type + fn] = function(){obj['e' + type + fn](window.event);};
	                 obj.attachEvent('on' + type, obj[type + fn]);
	         } else {
	                 obj.addEventListener(type, fn, false);
	         };
	 };
	 
	 // 移除事件
	 var unbind = function (obj, type, fn) {
	         if (obj.detachEvent) {
	                 try {
	                         obj.detachEvent('on' + type, obj[type + fn]);
	                         obj[type + fn] = null;
	                 } catch(_) {};
	         } else {
	                 obj.removeEventListener(type, fn, false);
	         };
	 };
	 
	 // 阻止浏览器默认行为
	 var stopDefault = function(e){
	         e.preventDefault ? e.preventDefault() : e.returnValue = false;
	 };
	
	 // 获取页面滚动条位置
	 var getPage = function(){
	         var dd = document.documentElement,
	                 db = document.body;
	         return {
	                 left: Math.max(dd.scrollLeft, db.scrollLeft),
	                 top: Math.max(dd.scrollTop, db.scrollTop)
	         };
	 };
	 
	 // 锁屏
	 var lock = {
	         show: function(){
	                 $('pageOverlaymain').style.visibility = 'visible';
	
	                 var p = getPage(),
	                         left = p.left,
	                         top = p.top;
	                 
	                 // 页面鼠标操作限制
	                 this.mouse = function(evt){
	                         var e = evt || window.event;
	                         stopDefault(e);
	                         scroll(left, top);
	                 };
	
	                 each(['DOMMouseScroll', 'mousewheel', 'scroll', 'contextmenu'], function(o, i) {
	                                 bind(document, o, lock.mouse);
	                 });
	
	                 // 屏蔽特定按键: F5, Ctrl + R, Ctrl + A, Tab, Up, Down
	                 this.key = function(evt){
	                         var e = evt || window.event,
	                                 key = e.keyCode;
	
	                         if((key == 116) || (e.ctrlKey && key == 82) || (e.ctrlKey && key == 65) || (key == 9) || (key == 38) || (key == 40)) {
	                                 try{
	                                         e.keyCode = 0;
	                                 }catch(_){};
	                                 stopDefault(e);
	                         };
	                 };
	                 bind(document, 'keydown', lock.key);
	         },
	
	         close: function(){
	         	$('pageOverlaymain').style.visibility = 'hidden';
	         	each(['DOMMouseScroll', 'mousewheel', 'scroll', 'contextmenu'], function(o, i) {
	         		unbind(document, o, lock.mouse);
	         	});
	         	unbind(document, 'keydown', lock.key);
	         }
	 };

 	bind(window, 'load', function(){
 		showMongoliaLayer = function(){
	 		lock.show();
	 	};
	
	 	hideMongoliaLayer = function(){
	 		lock.close();
	     };
    });
    /*页面蒙层*/
    
	/*页面动作控制开始*/
	jQuery('#overviewselect, input:checkbox').uniform();
	///// DATE PICKER /////
	jQuery( "#datepickfrom, #datepickto" ).datepicker();
	
	///// SLIM SCROLL /////
	jQuery('#scroll1').slimscroll({
		color: '#666',
		size: '10px',
		width: 'auto',
		height: '175px'                  
	});
	
	///// ACCORDION /////
	jQuery('#accordion').accordion({autoHeight:  false});

	///// SIMPLE CHART /////
	var flash = [[0, 2], [1, 6], [2,3], [3, 8], [4, 5], [5, 13], [6, 8]];
	var html5 = [[0, 5], [1, 4], [2,4], [3, 1], [4, 9], [5, 10], [6, 13]];
		
	function showTooltip(x, y, contents) {
		jQuery('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css( {
			position: 'absolute',
			display: 'none',
			top: y + 5,
			left: x + 5
		}).appendTo("body").fadeIn(200);
	}
	
	
	var previousPoint = null;
	jQuery("#chartplace").bind("plothover", function (event, pos, item) {
		jQuery("#x").text(pos.x.toFixed(2));
		jQuery("#y").text(pos.y.toFixed(2));
		
		if(item) {
			if (previousPoint != item.dataIndex) {
				previousPoint = item.dataIndex;
					
				jQuery("#tooltip").remove();
				var x = item.datapoint[0].toFixed(2),
				y = item.datapoint[1].toFixed(2);
					
				showTooltip(item.pageX, item.pageY,
								item.series.label + " of " + x + " = " + y);
			}
		
		} else {
		   jQuery("#tooltip").remove();
		   previousPoint = null;            
		}
	
	});
	
	jQuery("#chartplace").bind("plotclick", function (event, pos, item) {
		if (item) {
			jQuery("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
			plot.highlight(item.series, item.datapoint);
		}
	});
	
	
	///// SWITCHING LIST FROM 3 COLUMNS TO 2 COLUMN LIST /////
	function rearrangeShortcuts() {
		if(jQuery(window).width() < 430) {
			if(jQuery('.shortcuts li.one_half').length == 0) {
				var count = 0;
				jQuery('.shortcuts li').removeAttr('class');
				jQuery('.shortcuts li').each(function(){
					jQuery(this).addClass('one_half');
					if(count%2 != 0) jQuery(this).addClass('last');
					count++;
				});	
			}
		} else {
			if(jQuery('.shortcuts li.one_half').length > 0) {
				jQuery('.shortcuts li').removeAttr('class');
			}
		}
	}
	///// ON RESIZE WINDOW /////
	jQuery(window).resize(function(){
		rearrangeShortcuts();
	});
	
	/*页面动作控制结束*/
});


//下拉菜单展示
function fnShowTree(id){
	var obj = document.getElementById("ul_"+id);
	if(!obj){
		return;
	}
	var display = document.getElementById("ul_"+id).style.display;
	if(display =="none"){
		jQuery("#ul_"+id).slideDown(400);
	}else{
		jQuery("#ul_"+id).slideUp(400);
		
	}
}

function getMainMenu() {
    //获取页面菜单
    jQuery.ajax({
        type: "post",
        dataType: "json",
        url: "login/getMenu.do",
        success: function (data) {
            var mainMenu = data.mainMenu;
            var submenus = data.submenu;
            var threeMenu = data.threeMenu;
            //主菜单
            if(mainMenu.length > 0){
                jQuery("#headermenu").html("");
                for(var i = 0; i<mainMenu.length; i++){
                    var menu = mainMenu[i];
                    var li = " <li id='"+menu.authorityid+"'><a onclick='fnGetChildMenu("+menu.authorityid+");'><span class='icon "+menu.icon+" '></span>"+menu.authorityname+"</a></li>";
                    jQuery("#headermenu").append(li);
                    if(i == 0){ //只加载一次即可
                    	showSubMenus(menu.authorityid,submenus,threeMenu);
					}
                }
            }
        }
    });
}

//查询子节点菜单及三级菜单
function fnGetChildMenu(fatherid){
    jQuery.ajax({
        type: "post",
        dataType: "json",
        data: {"fatherid":fatherid},
        url: "login/getSubMenu.do",
        success: function (data) {
            var submenus = data.submenu;
            var threeMenu = data.threeMenu;
            //二级菜单
            showSubMenus(fatherid,submenus,threeMenu);
        }
    });
}

//根目录下子菜单展示方法
function showSubMenus(rootid,subMenus,threeMenu) {
    if(subMenus.length > 0){
        jQuery("#submenu").html("");
        for(var i = 0; i<subMenus.length; i++){
            var menu = subMenus[i];
            var menu_id = menu.authorityid;
            if (rootid != menu.fatherid) {
                continue;
			}
            var li_1 =" <li id='submenu_"+menu.authorityid+"'><a class='"+menu.icon+"'>"+menu.authorityname+"</a></li>";
            jQuery("#submenu").append(li_1);
            //设置三级菜单
            if(threeMenu.length > 0){
                //先添加span ul元素
                var span_ul="<span onclick=fnShowTree('"+menu_id+"'); class='arrow'></span><ul id='ul_"+menu_id+"' style='display:none;'>";
                var li_2 ="";
                for(var j = 0; j<threeMenu.length; j++){
                    var menu_2= threeMenu[j];
                    //判断是否为父节点下子节点
                    if(menu_id != menu_2.fatherid ){
                    	continue;
                    }
					li_2 = li_2+"<li id='li_"+menu_2.authorityid+"'><a href='javascript:void(0)' onclick=fnGetBusinessMenu('"
						+menu_2.url+"',"+menu_2.authorityid+",'"+menu_2.authorityname+"');>"+menu_2.authorityname+"</a></li>";
                }
                var span_ul_li = span_ul+li_2+"</ul> ";
                jQuery("#submenu_"+menu_id).append(span_ul_li);
            }
        }
    }
}

//业务菜单局部刷新方法(待完善)
function fnGetBusinessMenu(url,id,name){
    //生成页面导航信息
    var divid ="nav_"+id;
    //动态添加标签页
    var url = getRootPath() + url;
    addTab(divid,name,url,true);

}

//获取项目根路径
function getRootPath(){
    //获取当前网址，如： http://localhost:8080/last/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： /last/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/last
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}


/**
 * selectId 下拉选id  dataValue 下拉数据{{id,name},{id,name}}
 * 动态设置页面下拉选
 */
function setSelectOptions(selectId, dataValue){  
    var options = "";
    if(dataValue == null || dataValue ==undefined){
    	return;
	}
    for(var i = 0; i < dataValue.length; i++){
    	var $id = dataValue[i].id;
    	var name = dataValue[i].rname;
    	options = options + "<option id= 'option_"+$id+"' value="+$id+" >"+name+"</option>"; 
    }
    jQuery("#"+selectId).append(options);
}

/**
 * 串联加载指定的脚本
 * 串联加载[异步]逐个加载，每个加载完成后加载下一个
 * 全部加载完成后执行回调
 * @param array|string 指定的脚本们
 * @param function 成功后回调的函数
 * @return array 所有生成的脚本元素对象数组
 */

function seriesLoadScripts(scripts,callback) {
    if(typeof(scripts) != "object") var scripts = [scripts];
    var HEAD = document.getElementsByTagName("head").item(0) || document.documentElement;
    var s = new Array(), last = scripts.length - 1, recursiveLoad = function(i) { //递归
        s[i] = document.createElement("script");
        s[i].setAttribute("type","text/javascript");
        s[i].onload = s[i].onreadystatechange = function() { //Attach handlers for all browsers
            if(!/*@cc_on!@*/0 || this.readyState == "loaded" || this.readyState == "complete") {
                this.onload = this.onreadystatechange = null; this.parentNode.removeChild(this);
                if(i != last) recursiveLoad(i + 1); else if(typeof(callback) == "function") callback();
            }
        }
        s[i].setAttribute("src",scripts[i]);
        HEAD.appendChild(s[i]);
    };
    recursiveLoad(0);
}


/**
 * 并联加载指定的脚本
 * 并联加载[同步]同时加载，不管上个是否加载完成，直接加载全部
 * 全部加载完成后执行回调
 * @param array|string 指定的脚本们
 * @param function 成功后回调的函数
 * @return array 所有生成的脚本元素对象数组
 */

function parallelLoadScripts(scripts,callback) {
    if(typeof(scripts) != "object") var scripts = [scripts];
    var HEAD = document.getElementsByTagName("head").item(0) || document.documentElement, s = new Array(), loaded = 0;
    for(var i=0; i<scripts.length; i++) {
        s[i] = document.createElement("script");
        s[i].setAttribute("type","text/javascript");
        s[i].onload = s[i].onreadystatechange = function() { //Attach handlers for all browsers
            if(!0 || this.readyState == "loaded" || this.readyState == "complete") {
                loaded++;
                this.onload = this.onreadystatechange = null; this.parentNode.removeChild(this);
                if(loaded == scripts.length && typeof(callback) == "function") callback();
            }
        };
        s[i].setAttribute("src",scripts[i]);
        HEAD.appendChild(s[i]);
    }
}