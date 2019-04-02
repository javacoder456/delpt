var setting = {
    view: {
        selectedMulti: false
    },
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    }
};

function addMenus(menus) {
    var zNodes = [];
    for(var i = 0; i < menus.length; i++){
        var menu = menus[i];
        var newNode= {};
        if(menu.fatherid==""||menu.fatherid==null){
            newNode ={id:menu.authorityid, pId:menu.authorityid, name:menu.authorityname, open:true};
        }else{
            newNode = {id:menu.authorityid, pId:menu.fatherid, name:menu.authorityname};
        }
        zNodes.push(newNode);
    }
    return zNodes;
}

function addAuths(auths) {
    var zNodes = [];
    for(var i = 0; i < auths.length; i++){
        var auth = auths[i];
        var ftradeserviceid;
        if(auth.tradeservicetype == "query"){
            ftradeserviceid = "R001"
        }else if(auth.tradeservicetype == "service"){
            ftradeserviceid = "R002"
        }else{
            ftradeserviceid = "R003"
        }
        var newNode= {id:auth.tradeserviceid, pId:tradeserviceid, name:uth.tradeservicename, open:true};
        zNodes.push(newNode);
    }
    return zNodes;
}


function getCheckNodeIds() {
    var treeObj = jQuery.fn.zTree.getZTreeObj("zTree");
    var checkNodes = treeObj.getCheckedNodes(true);
    var ids = new Array();
    for(var i = 0;i < checkNodes.length;i++ ){
        ids[i] = checkNodes[i].id;
    }
    return JSON.stringify(ids);
}