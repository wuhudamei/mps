window.VueMixin = {};
window.VueMixin.select = {
  data:function(){
    return {
      selectItems:null,
      isError:false,
      message:'',
    }
  },
  methods:{
    //获取地址信息
    getAddress: function() {
      var self = this;
      self.$http.get(baseUrl + '/app/home/score/getCustomerOrder').then(function(res) {
        if (res.data.code == 200) {
          self.selectItems = res.data.data;
          if(self.selectItems && self.selectItems.length !== 0){
            self.selectId = self.selectItems[0].id;
            self.selectText = self.selectItems[0].detail_address;
          }
        }
      })
    }
  },
  watch:{
	  isError:function(val){
		  var self = this;
		  if(val){
			  setTimeout(function(){self.isError = false; self.message = ''},2000)
		  }
	  }
  }
 
}
