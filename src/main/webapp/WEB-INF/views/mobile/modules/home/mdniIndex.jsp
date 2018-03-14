<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="fnc" uri="/WEB-INF/tlds/fnc.tld" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="act" tagdir="/WEB-INF/tags/act" %>
<%@ taglib prefix="cms" tagdir="/WEB-INF/tags/cms" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getAdminPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
      <meta name="format-detection" content="telephone = no" />
      <title></title>
      <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/footer.css"/>
      <style>
          body{margin:0;}
      </style>
      <script type="text/javascript">
        var baseAppUrl ="${ctx}/app/";
          function onFormSubmit(form){
            $(form).find("select").attr("disabled",false);
          }
          function setSort(value){
            var sort = $("#frontSort").val();
            var isDesc = sort.indexOf("DESC");
            if(isDesc==-1 && sort==value){
              $("#frontSort").val(value+" DESC");
            }else{
              $("#frontSort").val(value);
            }
            $("#btnSubmit").click();
          };
      </script>
  </head>
  <body>
      <iframe src="http://www.mdni.cn/" id="iframepage" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"></iframe>
      <script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/lib/jquery-2.1.1.min.js"></script>
      <script type="text/javascript" src="${ctxStatic}/mobile/modules/pqc/js/utils/calcRootFontSize.js"></script>
      <footer>
        <a class="home_btn active" href="javascript:void(0)">首页</a>
        <%-- <a class="mine_btn" href="${ctx}/app/home/toMyHome">我的</a> --%>
        <a class="mine_btn" onclick="customerLogin()">我的</a>
      </footer>
      <script type="text/javascript">
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端	
          function customerLogin(){
          $.ajax({
            url:"${ctx}/app/home/toMyHome",
            type:"post",
            success:function(data){
              if(data == '2'){
                //调用原生的方法
                if(isAndroid){
                  var data = android.tokenid();
                  //alert(data);
                  var json = eval("("+data+")");
                  //alert(json);
                  //alert(json.tokenid);
                  if(json.tokenid != null){
                    $("#tokenid").val(json.tokenid);
                                  $("#appType").val(json.appType);
                                  $("#sign").val(json.sign);
                                  $("#loginForm").submit();
                  }else{//调用原生打开登录页面
                    android.login();
                  } 
                }
                if(isiOS){
                  window.onerror = function(err) {
                    log('window.onerror: ' + err);
                  }
                    function setupWebViewJavascriptBridge(callback) {
                        if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
                        if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
                        window.WVJBCallbacks = [callback];
                        var WVJBIframe = document.createElement('iframe');
                        WVJBIframe.style.display = 'none';
                        WVJBIframe.src = 'https://__bridge_loaded__';
                        document.documentElement.appendChild(WVJBIframe);
                        setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0);
                    }
                    setupWebViewJavascriptBridge(function(bridge) {
                      var uniqueId = 1
                    function log(message, data) {
                      var log = document.getElementById('log')
                      var el = document.createElement('div')
                      el.className = 'logLine'
                      el.innerHTML = uniqueId++ + '. ' + message + ':<br/>' + JSON.stringify(data)
                      if (log.children.length) { log.insertBefore(el, log.children[0]) }
                      else { log.appendChild(el) }
                    }
                      /* bridge.registerHandler('pageSkip', function(data, responseCallback) {
                      //log('ObjC called testJavascriptHandler with', data)
                      alert("pageSkip");
                      window.location.href="${ctx}/app/home/isLogin";
                    }); */
                    bridge.callHandler('tokenid', function(data,response) {
                                        if(data != null){
                                          $("#tokenid").val(data.tokenid);
                                          $("#appType").val(data.appType);
                                          $("#sign").val(data.sign);
                                          $("#loginForm").submit();
                      }else{//调用原生打开登录页面
                        bridge.callHandler('login', function(response) {
                        });
                      }
                                    })
                  });
                }
              }
              if(data == '1'){
                window.location.href ="${ctx}/app/home/toweichat";
              }
              if(data == '3'){
                window.location.href ="${ctx}/app/home/isLogin";
              }
            }
          });
        } 
      </script>
      <form id ="loginForm" action="/api/api/checkTokenId" method="post">
        <input type="hidden" value="" name="tokenid" id="tokenid">
        <input type="hidden" value="" name="appType" id="appType">
        <input type="hidden" value="" name="sign" id="sign">
      </form>
  </body>


</html>