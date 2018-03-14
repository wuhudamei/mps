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
  <title>项目团队</title>
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/reset.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/common.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/header.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/footer.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/team.css" />
  <link rel="stylesheet" type="text/css" href="${ctxStatic}/mobile/modules/home/css/placeHolder.css" />
  <script>var baseUrl = '${ctx}',baseStatic= "${ctxStatic}"</script>
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

<body id="app" v-cloak>
<div class="mask" v-show="isMask"></div>
  <!--投诉反馈start-->
  <div class="Complaints" v-show="isBad">
    <h4><span class="iconMaskBtn" @click="hiddenEvent"></span>投诉反馈<span class="iconUP" @click="handleSubmit"></span></h4>
    <form action="" @submit.prevent="handleSubmit">
      <textarea v-model="badContent" placeholder="写下您的感受以帮助我们改善我们的服务!" maxlength="500" class="pt20"></textarea>
    </form>
  </div>
  <!--投诉反馈end-->
  <!--差评意见提交start-->
  <div class="badReviews" v-show="isConfirm">
    <h4>您确认没有内容直接提交？</h4>
    <div class="posr clearfix">
      <button class="subSold fl" @click="isConfirm = false">返回填写</button>
      <i class="subLine"></i>
      <button class="subDotted fr" @click="submitEvent(0,badItem)">给他差评</button>
    </div>
  </div>
  <!--差评意见提交end-->
  <div class="">
    <header>
      <a class="back_btn" href="${ctx }/app/home/isLogin" ></a>
      <h2 class="title">施工团队</h2>
    </header>
    <!-- /header -->
    <section class="total">
      <div class="nav_sec">
        <div class="mask" :class="[navBar || isConfirm ||isBad || isError ? '' : 'undis']"  @click="hiddenEvent"></div>
        <div class="nav_box">
          <a class="nav_bar font0" :class="[navBar ? 'index10' : '']" @click="navBar=true">
            <!-- <span class="select font28 col_3" id="showStart">{{selectText}}</span> -->
            <div class="select font28 col_3" id="marquee">{{selectText}}</div>
            <img class="select_btn" src="${ctxStatic}/mobile/modules/home/images/select_btn.png" alt="">
          </a>
          <div class="options bg_f" :class="[navBar ? '' : 'undis']" id="adressDown">
            <a class="col_grey font24" href="javascript:void(0)" v-for="select of selectItems" @click="handleSelect(select)">
              {{select.detail_address}}
            </a>
          </div>
        </div>
      </div>
      <ul class="members" id="teamList">
        <%-- <li class="bg_f shadow mb24 clearfix" v-for="item of items">
          <div class="left"><img :src="[item.headPic ? item.headPic :'${ctxStatic}/mobile/modules/home/images/worker.png']" alt=""></div>
          <div class="right font0">
            <div class="">
              <p class="mt70 font28"><span class="col_grey">工人：</span><span class="col_3">{{item.memberName}}</span></p>
              <p class="mt30 tele_num font22"><span class="col_grey">工种：</span><span class="col_3">{{item.workType}}</span></p>
              <div class="star startG" v-if="!item.initFlag">
                <span  class="bling" v-for="ite of item.currentScore"></span>
                <span v-if="item.initFlag" class="bling" v-for="ite of 5"></span>
              </div>
               <div class="star startG" v-if="item.initFlag">
                <span class="bling" v-for="ite of 5"></span>
              </div>
            </div>
            <div class="likeDifference fr mt65">
              <input type="hidden" value="">
              <p class="fl mr30 goodBtn " @click="handleClick(1,item)"><span :class="[item.clickType === 1 ? 'icon_like' : 'icon_unlike']"></span>赞</p>
              <p class="fl badBtn" @click="handleClick(0,item)"><span :class="[item.clickType === 0 ? 'icon_difference' : 'icon_undifference']"></span>差</p>
            </div>
          </div>
        </li> --%>
        <li class="bg_f shadow mb24 clearfix" v-for="item of items" >
        	<div v-if="item.memberName">
        		<div class="left"><img :name="imgErrorType(item)" :src="imgUrl(item)" onerror="this.src='${ctxStatic}/mobile/modules/home/images/'+this.name" alt=""></div>
          <div class="right font0">
            <div class="dot_line">
              <p class="name font28"><span class="col_grey">{{item.workType}}：</span><span class="col_3">{{item.memberName}}</span></p>
              <p class="tele_num font22" style="min-height: 0.31rem" ><span v-if="item.memberPhone" class="col_grey">电话：</span><span v-if="item.memberPhone" class="col_3">{{item.memberPhone}}</span></p>
              <div class="star startG" >
                <span class="bling" v-for="ite of item.currentScore"></span>
              </div>
            </div>
            <a class="tele" :href="'tel:' + item.memberPhone">
              <img class="phone" src="${ctxStatic}/mobile/modules/home/images/call.png" alt="拨打电话">
            </a>
            <div class="likeDifference fr mt65">
              <input type="hidden" value="">
              <p class="fl mr30 goodBtn " @click="handleClick(1,item)"><span :class="[item.clickType === 1 ? 'icon_like' : 'icon_unlike']"></span>赞</p>
              <p class="fl badBtn" @click="handleClick(0,item)"><span :class="[item.clickType === 0 ? 'icon_difference' : 'icon_undifference']"></span>差</p>
            </div>
          </div>
        	</div>
          
        </li>
      </ul>
    </section>
    <footer>
      <a class="home_btn" href="javascript:void(0)">首页</a>
      <a class="mine_btn active" href="javascript:void(0)">我的</a>
    </footer>
  </div>
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
  <script src="${ctxStatic}/mobile/modules/home/js/lib/jquery-2.1.1.min.js"></script>
  <script src="${ctxStatic}/mobile/modules/home/js/utils/calcRootFontSize.js"></script>
  <script src="${ctxStatic}/mobile/modules/home/js/lib/moment.js"></script>
  <script src="${ctxStatic}/mobile/modules/home/js/team/mixin.js"></script>
  <script src="${ctxStatic}/mobile/modules/home/js/team/myTeam.js"></script>
</body>

</html>
