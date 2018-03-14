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
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/Evaluation.css">
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/placeHolder.css">
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/team.css" />
  <script> var baseUrl = '${ctx}' </script>
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
      <h2 class="title">我的评价</h2>
    </header>
    <!-- /header -->
  </div>
  <section class="pt90 bg_f">
    <div class="nav_sec plr30">
      <div class="mask1" :class="[navBar || isError ? '' : 'undis']" @click="hiddenEvent"></div>
      <div class="nav_box ">
        <a class="nav_bar font0" :class="[navBar ? 'index10' : '']" @click="navBar=true">
          <!-- <span class="select font28 col_3" id="showStart"></span> -->
          <div class="select font28 col_3" id="marquee">{{selectText}}</div>
          <img class="select_btn" src="${ctxStatic}/mobile/modules/home/images/select_btn.png" alt="">
        </a>
        <div class="options bg_f undis" id="adressDown" :class="[navBar ? '' : 'undis']">
          <a class="col_grey font24" href="javascript:void(0)" v-for="select of selectItems" @click="handleSelect(select)">
                {{select.detail_address}}
              </a>
          <!--<a class="col_grey font24" href="javascript:void(0)">-->
          <!--月华小区一里-5-4-403 ( <span class="col_red">1</span> )-->
          <!--</a>-->
          <!--<a class="col_grey font24" href="javascript:void(0)">-->
          <!--月华小区一里-5-4-403 ( <span class="col_red">2</span> )-->
          <!--</a>-->
          <!--<a class="col_grey font24" href="javascript:void(0)">-->
          <!--月华小区一里-5-4-403-->
          <!--</a>-->
        </div>
      </div>
    </div>
    <!-- <validate name="validation"> -->
      <div class="Evaluation posr " v-for="item of items">
        <h2 class="fl mt20 plr30 font28 fontw">{{item.scoreName}}</h2>
        <p class="flow star-p">
          <span class="star1" :class="[$index < item.score ? 'star2' : '']" v-for="star of stars" @click="starClick($index,item)"></span>
          <!-- <span class="star1"></span>
        <span class="star1"></span>
        <span class="star1"></span>
        <span class="star1"></span> -->
        </p>
        <div class="plr30 ">
          <textarea placeholder="写下您的感受以帮助我们改善我们的服务" v-model="item.content" maxlength="200"></textarea>
          <!--<span class="VoiceButton"></span>-->
          <input type="button" value="确认提交" class="submitBtn" @click="handleSubmit(item,$index)">
        </div>
      </div>

  </section>
  <section class="scoreHistory tc bg_f " v-if="historyItems && historyItems.length!==0">
    <p class="plr30 font20 col_6 scoreTip">以下是历史评价 <span class="color-999">(不可进行编辑)</span></p>
    <div class="scoreMark tl plr30 ptb40" v-for="item of historyItems">
      <h4 class="col_3 font28">{{item.scoreTypeCn}}</h4>
      <p class="EvaluationStar clearfix pt10 pb10">
        <span class="start choiceStar" v-for="ite of item.scoreValue"></span>
        <!-- <span class="start choiceStar"></span>
        <span class="start choiceStar"></span>
        <span class="start choiceStar"></span>
        <span class="start choiceStar"></span> -->
      </p>
      <p class="col_6 font22">{{item.scoreContent}}</p>
    </div>
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
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/swiper.min.js"></script>
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/lib/vue-validator.min.js"></script>
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/team/mixin.js"></script>
  <script type="text/javascript" src="${ctxStatic}/mobile/modules/home/js/team/teamtroup.js"></script>
</body>

</html>
