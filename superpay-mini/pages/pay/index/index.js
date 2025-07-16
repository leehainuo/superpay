// pages/pay/index/index.js
import {getStoreByIdApi} from "@/api/storeApi";

Page({

  /**
   * 页面的初始数据
   */
  data: {
    merchantId:'',
    storeId:'',
    amount: '' // 双向绑定的金额字段
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const merchantId = options.merchantId; // 获取参数
    const storeId = options.storeId; // 获取参数
    console.log(`merchantId=${merchantId},storeId=${storeId}`);
    getStoreByIdApi(storeId).then(res => {
      console.log(res);
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
 
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },
  onAmountInput(e) {
    const value = e.detail.value; // 获取输入的值
    this.setData({
      amount: value // 更新金额
    });
  }
})