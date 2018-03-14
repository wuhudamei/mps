$(function () {
    //js设置盒子高度
    var setIndex={
        setHeight:function (pageBox,topBox,bottomBox) {
            var  pageBox=$(pageBox);
            var  topBox=$(topBox);
            var  bottomBox=$(bottomBox);
            var topHeight=topBox.height();
            var bottomHeight=bottomBox.height();
            var heightBox=$(window).height()-topHeight-bottomHeight;
            pageBox.css('height',heightBox)
        },
        showContMask:function (contetMask,clickBox) {
            //页面内容弹层
            var contetMask=$(contetMask);
            var clickBox=$(clickBox);
            clickBox.on('click',function () {
                clickBox.find('i').html('');
                //调用遮罩层
                globalUtil.fn.showMask();
                contetMask.css('display','block');
            })

        },
        quite:function (quiteBtn,chanelBtn,showMask,showBtn) {
            var quiteBtn=$(quiteBtn);
            var showMask=$(showMask);
            var showBtn=$(showBtn);
            var chanelBtn=$(chanelBtn);
            showBtn.on('click',function () {
                showMask.show();
                globalUtil.fn.showMask()
            })
            quiteBtn.on('click',function () {
                    //点击确定跳转到登出页面
                    //window.location.href="/mdn/a/app/home/logout";
                    window.location.href="/a/app/home/logout";
            });
            chanelBtn.on('click',function () {
                showMask.hide();
                globalUtil.fn.hideMask()
            })
        },
        quiteTwo:function (quiteBtn,chanelBtn,showMask,showBtn) {
        	var quiteBtn=$(quiteBtn);
        	var showMask=$(showMask);
        	var showBtn=$(showBtn);
        	var chanelBtn=$(chanelBtn);
        	showBtn.on('click',function () {
        		showMask.show();
        		globalUtil.fn.showMask()
        	})
        	
        	chanelBtn.on('click',function () {
        		showMask.hide();
        		globalUtil.fn.hideMask()
        	})
        }
    }
    //调用设置内容高度
    setIndex.setHeight('.bxy_setIndex_warp_con','.bxy_setIndex_warp_header','.bxy_setIndex_warp_footer');
   //调用设置弹层出现
    /*setIndex.showContMask('.bxy_setIndex_mask','#clearCookie');*/
//调用退出弹层,回到登陆
    setIndex.quite('#changeQuite','.bxy_updata_mask_bottom_right','.bxy_updata_mask','.BxyquiteLogin');
    // 点击不同意弹出窗口 变更详情
    setIndex.quiteTwo('.bxy_updata_mask_bottom_left','.bxy_updata_mask_bottom_right','.bxy_advise_mask','#unagreeChange');
    // 点击不同意弹出窗口 变更详情
    setIndex.quiteTwo('#changeAsked','.bxy_updata_mask_bottom_right','#changeAree','#agreeChange');
})
