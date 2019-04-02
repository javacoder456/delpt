 /*
  * 创建标签页 调用addTab(id,text,url,innerTab);
  * id:       tab页签的html标签ID属性格式为"tab-"+id，内容容器的html标签ID格式为"tab-content-"+id
  * text:     tab页签的显示文本
  * url:      打开的iframe的url
  * closable: 是否显示关闭；默认undefined/false不显示
  * innerTab: 是否是内部弹出页（打开的tab页触发添加新的tab页），默认为undefined/false
  */

  //切换tab页的显示
    jQuery(document).on('click','#myTab > li',function(e){
      //清除原来显示的tab页
      var oldTab = jQuery("#myTab li.active").removeClass("active").find("a[data-toggle='tab']");
      var oldTabId = jQuery(oldTab).attr("id")
      jQuery(oldTabId).removeClass("active");

      //设置新的显示tab页
      var newTab = jQuery(this).addClass("active").find("a[data-toggle='tab']");
      var newTabId= jQuery(newTab).attr("id");
      jQuery(newTabId).addClass("active");
      if(jQuery(this).attr('id')!=null&&jQuery(this).attr('id').length>4)
      {
        refreshTabHistory(false,jQuery(this).attr('id').substring(4));
      }else{
        refreshTabHistory(false,jQuery(this).attr('id'));
      }
	
  });
  //手动调用切换到要显示的tab页,当前的action只支持show
  //eg:jQuery("#tab-0 a[data-toggle='tab']").tab("show");
  jQuery.fn.tab = function(action){
    if(action == "show"){
    	jQuery(this).parent().click();
    }
  };

  var currentTabId = '';//当前焦点Tab
  //在非左侧菜单栏弹出的tab页也会用到该数据，如common.js中的pageForward函数
  var pageCounter = 0;
  /**
  id:       tab页签的html标签ID属性格式为"tab-"+id，内容容器的html标签ID格式为"tab-content-"+id
  text:     tab页签的显示文本
  url:      打开的iframe的url
  closable: 是否显示关闭；
  innerTab: 是否是内部弹出页（打开的tab页触发添加新的tab页），默认为undefined/false
  */
  function addTab(id,text,url,closable,innerTab) {
    //如果某个页面已经打开，则切换到该页显示即可，不会新添加tab页

    if(jQuery('#myTab #tab-'+id).length > 0){
      jQuery('#myTab  #tab-' + id + ' a').tab('show');
    }else{
      var tab_id = "tab-" + id,
      tab_content_id = "tab-content-"+id;
      //加入是否显示关闭 true:显示 false:不显示
      var closestr = "";
      if(closable==true){
    	 closestr = "<p class='fa fa-times' onclick='deleteTab(\"" 
              + id + "\")'><img src='images/blueline/searchcancel.png' style= 'margin-right: 4px;' /></p>";
      }else{
    	 closestr = "";
      }
      //判断是否为首页
      var astr="";
      if(text =="我的主页"){
    	  astr="' class='active'><a data-toggle='tab' style='text-align:center' id='#";
      }else{
    	  astr="' class='active'><a data-toggle='tab' style='width:120px' id='#";
      }
      //添加tab页签
      if(jQuery("#myTab").length==0){
          jQuery("#myTab" , parent.document)
          jQuery("#myTab > li", parent.document).removeClass("active");
          jQuery("#myTab", parent.document).append("<li id='" + tab_id + astr
              + tab_content_id + "'>" + text + "</a>"
              + (closestr)
              + "</li>");

          //添加新的内容显示
          jQuery(".tab-content > div", parent.document).removeClass("active");
          jQuery(".tab-content", parent.document).append("<div id='"+ tab_content_id +"' class='active'>"
              + "<iframe id='iframepage" + (pageCounter++) + "' name='iframepage" + (pageCounter++)
              + "' width='100%' height='100%' frameborder='0' scrolling='auto'  src='" + url + "'></iframe></div>");

      }else{

        jQuery("#myTab > li").removeClass("active");
        jQuery("#myTab").append("<li id='" + tab_id + astr
          + tab_content_id + "'>" + text + "</a>"
          + (closestr)
          + "</li>");

        //添加新的内容显示
        jQuery(".tab-content > div").removeClass("active");
        jQuery(".tab-content").append("<div id='"+ tab_content_id +"' class='active'>"
          + "<iframe id='iframepage" + (pageCounter++) + "' name='iframepage" + (pageCounter++)
          + "' width='100%' height='100%' frameborder='0' scrolling='auto'  src='" + url + "'></iframe></div>");

      }

    }
    //刷新切换tab的历史记录
    refreshTabHistory(false,id);
    //重新设置tab页签的宽度
    refreshWidth();
  };
  //参数id为tab的标志，但是并不是tab页的id属性，真正的id属性值是"tab-"+id
  function deleteTab(id){
    var tabJQ = jQuery("#tab-"+id),
    tabContentJQ = jQuery("#tab-content-" + id);    
    
    if(!tabJQ.hasClass("active")){
      tabJQ.remove();
      tabContentJQ.remove();
      refreshTabHistory(true,id);
    }else{    
      tabJQ.remove();
      tabContentJQ.remove();
      refreshTabHistory(true,id);
      jQuery('#tab-' + currentTabId + '>a').tab('show');
    }
    refreshWidth();
  };
  //关闭当前tab页的快速方法
  function closeCurrentTab(){
    deleteTab(currentTabId);
  };

  /*
  刷新页签切换历史
  isdelete: 是否是删除tab页签,true:是，false：否
  curTabId：要处理的tab页签的id,tab页签html标签元素的ID属性格式为"tab-"+curTabId
  */
  function refreshTabHistory(isdelete,curTabId){
	if(curTabId == "nav_undefined"){
		return false;
	}
    if(!refreshTabHistory.histoty){
      //用来记录用户点击tab的历史
      refreshTabHistory.histoty = [];
    }
    var index = 0;
    leng = refreshTabHistory.histoty.length;
    //查找传入的tab页签在历史记录中的位置
    for(; index < leng; index++){
      if(refreshTabHistory.histoty[index] == curTabId){
        break;
      }
    }
    
    //如果是删除页签，直接在历史记录中删除即可，历史记录的其他页签的顺序不变
    if(isdelete){
      refreshTabHistory.histoty.splice(index,1);

    //如果是新增页签，先保证历史记录中没有改页签（有就删掉），然后将新增的页签放在历史记录的最后面（即该页签为最新）
    }else{
      if(index < leng) {
        refreshTabHistory.histoty.splice(index,1);
      }
      refreshTabHistory.histoty.push(curTabId);
    }
    currentTabId = refreshTabHistory.histoty[refreshTabHistory.histoty.length - 1];
  };

  //刷新重置tab页签的宽度
  function refreshWidth(){
    var panelWidth = jQuery('#myTab').width() - 20/*可能存在的滚动条宽度*/,
    tabContentAverageWidth = 0 /*tab > a标签的宽度*/,
    minTabAverageWidth = 25  /*margin-left:5,X按钮宽度为20*/,
    zeroContentTabWidth = 35  /*当tab > a标签宽度为0时tab标签对应的宽度是30px，外加上margin-left:5*/,
    aPaddingLeft = 10  /*tab > a标签的padding-left默认是10，当averageWidth< 35需要调整*/;
    var aCount = jQuery('#myTab > li').length;
    var averageWidth = parseInt(panelWidth/aCount,10);//
    if(averageWidth >= zeroContentTabWidth){
      tabContentAverageWidth = averageWidth - zeroContentTabWidth;
      
    /*35 > averageWidth >= 25*/ 
    }else if(averageWidth >= minTabAverageWidth){
      tabContentAverageWidth = 0;
      aPaddingLeft = averageWidth - minTabAverageWidth;
      
    //averageWidth < 25
    }else{
      tabContentAverageWidth = 0;
      aPaddingLeft = 0;
    }
    //tab页签名称元素a标签的宽度和padding-left。这个是在box-sizing:border-box。的情况下
      jQuery('#myTab > li').find('>a').css({'width':(tabContentAverageWidth + aPaddingLeft),'padding-left':aPaddingLeft});
  };
