/*
js异步提交表单
 */

//后台取到区域信息和分类信息传到前端
$(function() {

    var shopId = getQueryString("shopId");
    var isEdit = !!shopId;

    var initUrl = "/shopadmin/getshopinitinfo";
    var registerShopUrl = "/shopadmin/registershop";
    var shopInfoUrl = "/shopadmin/getshopbyid?shopId=" + shopId;
    var editShopUrl = "/shopadmin/modifyshop";

    if (!isEdit) {
        getShopInitInfo();
    }
    else{
        getShopInfo();
    }

    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {

                var shop = data.shop;
                $("#shopName").val(shop.shopName);
                $("#shopAddr").val(shop.shopAddr);
                $("#phone").val(shop.phone);
                $("#shopDesc").val(shop.shopDesc);

                var tempAreaHtml = "";
                var shopCategoryVar ='<option data-id="' + shop.shopCategory.shopCategoryId + '"selected>' + shop.shopCategory.shopCategoryName + '</option>';

                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
                });
                $("#shopCategory").html(shopCategoryVar);
                $("#shopCategory").attr('disabled', 'disabled');
                $("#area").html(tempAreaHtml);
                $("#area option[data-id='" + shop.area.areaId + "']").attr("selected", "selected");
            }
        });
    }

    //alert(initUrl);
    //getShopInitInfo();
    function getShopInitInfo() {
        $.getJSON(initUrl, function (data) {
            if (data.success) {
                var tempHtml = "";
                var tempAreaHtml = "";
                data.shopCategoryList.map(function (item, index) {
                    tempHtml += '<option data-id="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>';

                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>';
                });
                $("#shopCategory").html(tempHtml);
                $("#area").html(tempAreaHtml);
            }
        });
    }
    $("#submit").click(function () {
            var shop = {};
            if (isEdit) {
                shop.shopId = shopId;
            }
            shop.shopName = $('#shopName').val();
            shop.shopAddr = $('#shopAddr').val();
            shop.phone = $('#phone').val();
            shop.shopDesc = $('#shopDesc').val();

            shop.shopCategory = {
                shopCategoryId: $('#shopCategory').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId: $('#area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };

            var shopImg = $('#shopImg')[0].files[0];

            var formData = new FormData();
            formData.append("shopImg", shopImg);
            formData.append("shopStr", JSON.stringify(shop));
            var verifyCodeActual = $("#j_captcha").val();
            if (!verifyCodeActual) {
                $.toast("请输入验证码");
                return;
            }
            //alert(verifyCodeActual);
            formData.append("verifyCodeActual", verifyCodeActual);
            $.ajax({
                url: (isEdit?editShopUrl:registerShopUrl),
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        $.toast("提交成功");
                    }
                    else {
                        $.toast("提交失败" + data.errMsg);
                    }
                    $("#captcha_img").click();
                }
            });
    })
})