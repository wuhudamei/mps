<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
  <meta content="yes" name="apple-mobile-web-app-capable">
  <meta content="black" name="apple-mobile-web-app-status-bar-style">
  <meta content="telephone=no" name="format-detection">
  <meta content="email=no" name="format-detection">
  <title>施工现场</title>
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/constructionSite.css" />
  <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/Evaluation.css">
  <link rel="stylesheet" href="${ctxStatic}/mobile/modules/home/css/placeHolder.css">
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/team.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/Feedback.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/maskHome.css" />
  <script>
  var baseUrl = '${ctx}'
  </script>
  <script src="${ctxStatic}/mobile/modules/home/js/lib/vue.js"></script>
  <script src="${ctxStatic}/mobile/modules/home/js/lib/vue-resource.min.js"></script>
  <script>
  Vue.config.devtools = true;
  Vue.http.options.headers = {
    'Content-Type': 'application/x-www-form-urlencoded;charset = UTF-8;'
  };
  Vue.http.options.emulateJSON = true;
  </script>
</head>

<body class="bg_f" id="app" v-cloak>
  <div class="">
    <header>
      <a class="back_btn" href="${ctx }/app/home/isLogin" ></a>
      <h2 class="title">投诉反馈</h2>
    </header>
    <!-- /header -->
  </div>
  <section class="pt90 bg_f">
    <div class="nav_sec plr30">
      <div class="mask1" :class="[navBar || isError ? '' : 'undis']" @click="hiddenEvent"></div>
      <div class="nav_box">
        <a class="nav_bar font0" :class="[navBar ? 'index10' : '']" @click="navBar=true">
          <div class="select font28 col_3" id="marquee">{{selectText}}</div>
          <img class="select_btn" src="${ctxStatic}/mobile/modules/home/images/select_btn.png" alt="">
        </a>
        <div class="options bg_f" v-show="navBar" id="adressDown" transition="expand">
          <a class="col_grey font24" href="javascript:void(0)" v-for="address of selectItems" @click="handleSelect(address)">
              {{address.detail_address}}
            </a>
        </div>
      </div>
    </div>
  </section>
  <section class="Feedback Evaluation">
    <img src="${ctxStatic}/mobile/modules/home/images/peopleCustom.png" alt="">
    <div class="posr mt06 selectBox">
      <select name="" id="questionBlue" class="blue_border" v-model="selectCode">
        <option :value="question.dictCode" v-for="question of questionSelect">{{question.dictName}}</option>
      </select>
    </div>
    <textarea v-model="content" name="" placeholder="写下你感受以帮助改善我门的服务！" id="questionText" maxlength="500">
    </textarea>
    <input type="button" value="提交" :disabled="loading" @click="submitEvent" class="submitOne">
  </section>
  <!-- 加载loading -->
  <div class="black" v-show="loading">
    <div class="cen">
      <div class="loding">
        <img src="${ctxStatic}/mobile/modules/home/images/headAdd.png" alt="">
        <span class="getRefash"></span>
      </div>
    </div>
  </div>
  <div class="errorMessage" v-if="isError">{{message}}</div>

  <div  class="g-mask " v-show="isOk" >
    <div class="alertMask">
      <div class="maskWrapper">
        <p class="col_3 maskTit">提示</p>
        <div class="font28 col_6 maskContent" >提交成功</div>
        <div class="maskBtns clearfix">
          <a class="maskBtn font33 col_f"   @click="isKnow">我知道了</a>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/swiper.min.js"></script>
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
  <script src="${ctxStatic}/mobile/modules/home/js/team/mixin.js"></script>
  <script src="${ctxStatic}/mobile/modules/home/js/team/feedBack.js"></script>
</body>

</html>

