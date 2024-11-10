$(document).ready(function(){

    createTableItem();

    $(".btn-circle").click(function() {
        createTableItem();
    });

    function createTableItem() {
        let addedTableHtml = '';
        let equipmentIndex = $("#equipment-index").val();

        addedTableHtml += '<table align="center" class="bordered buy-equipment-table" id="equipment-' + equipmentIndex + '">'
        addedTableHtml += '<tr>';
        addedTableHtml += '<td bgcolor="#0ccff2" colspan="2" class="text-left">';
        addedTableHtml += '  <label style="font-size: 18px">设备信息</label>';
        addedTableHtml += '  <img src="' + path + '/static/css/images/delete.svg" class="delete-img pull-right" onclick="removeTableItem(\'equipment-'+ equipmentIndex +'\')"></img>';
        addedTableHtml += '</td>';
        addedTableHtml += '</tr>';
        addedTableHtml += '<tr>';
        addedTableHtml += '<td bgcolor="#fff"><label>设备名</label></td>';
        addedTableHtml += '  <td bgcolor="#fff" class="td-input">';
        addedTableHtml += '  <input type="text" data-field="equipment_name" class="input-text" />';
        addedTableHtml += '</td>';
        addedTableHtml += '</tr>';
        addedTableHtml += '<tr>';
        addedTableHtml += '<td bgcolor="#fff"><label>购进数量</label></td>';
        addedTableHtml += '  <td bgcolor="#fff" class="td-input">';
        addedTableHtml += '  <input type="text" class="input-text" data-field="equipment_count" />';
        addedTableHtml += '</td>';
        addedTableHtml += '</tr>';
        addedTableHtml += '<tr>';
        addedTableHtml += '  <td bgcolor="#fff"><label>设备单价</label></td>';
        addedTableHtml += '  <td bgcolor="#fff" class="td-input"><input type="text" class="input-text" align="center" data-field="equipment_price" /></td>';
        addedTableHtml += '</tr>';
        addedTableHtml += '<tr>';
        addedTableHtml += '  <td bgcolor="#fff"><label>供货商地址</label></td>';
        addedTableHtml += '  <td bgcolor="#fff" class="td-input"><input type="text" class="input-text" align="center" data-field="equipment_shop_address" /></td>';
        addedTableHtml += '</tr>';
        addedTableHtml += '<tr>';
        addedTableHtml += '  <td bgcolor="#fff"><label>实验室名称</label></td>';
        addedTableHtml += '  <td bgcolor="#fff" class="td-input">';
        addedTableHtml += '  <select data-field="lab_name" class="input-text">';

        let labs = JSON.parse(labJson);

        for (let i = 0; i < labs.length; i++) {
            addedTableHtml += '  <option value="' + labs[i].id + '">' + labs[i].name + '</option>';
        }

        addedTableHtml += '</tr>';

        $(".equipment-table").append(addedTableHtml);
        $("#equipment-index").val(++equipmentIndex)
    }

    $(".btn-commit").on("click", function(){

        let equipmentNameElementes = $("table[id^='equipment-']").find("input[data-field='equipment_name']");
        let equipmentCountElementes = $("table[id^='equipment-']").find("input[data-field='equipment_count']");
        let equipmentPriceElementes = $("table[id^='equipment-']").find("input[data-field='equipment_price']");
        let equipmentShopAddressElementes = $("table[id^='equipment-']").find("input[data-field='equipment_shop_address']");
        let equipmentLabNameElementes = $("table[id^='equipment-']").find("select[data-field='lab_name']");
        let equimentJson = {};
        equimentJson.equipments = [];
        let isFilledTrue = true;

        if (equipmentNameElementes.length == 0) {
            $(".alert").attr("class", "alert alert-danger");
            $(".alert").html("设备不得少于1个。");
            isFilledTrue = false;
        } else {

            for (let i = 0; i < equipmentNameElementes.length; i++) {
                let equipment = {};
                equipment.name = equipmentNameElementes[i].value;
                equipment.count = equipmentCountElementes[i].value;
                equipment.price = equipmentPriceElementes[i].value;
                equipment.shop = equipmentShopAddressElementes[i].value;
                equipment.lab = equipmentLabNameElementes[i].value;
                equimentJson.equipments.push(equipment);

                let countReg = /^\d+$/;
                let priceReg = /^\d+(\.\d+)?$/;

                if (!equipment.name || !equipment.count || !equipment.price || !equipment.shop || !equipment.lab) {
                    $(".alert").attr("class", "alert alert-danger");
                    $(".alert").html("设备的各项信息不能为空。");
                    isFilledTrue = false;

                    break;
                } else if (!countReg.test(equipment.count)){
                    $(".alert").attr("class", "alert alert-danger");
                    $(".alert").html("设备的数量需为整数。");

                    isFilledTrue = false;
                    break;
                } else if (!priceReg.test(equipment.price)) {
                    $(".alert").attr("class", "alert alert-danger");
                    $(".alert").html("设备的价格需为正整数或浮点数。");
                    isFilledTrue = false;

                    break;
                } else if (equipment.count <= 0) {
                    $(".alert").attr("class", "alert alert-danger");
                    $(".alert").html("设备的数量最少为1。");
                    isFilledTrue = false;

                    break;
                }
            }
        }

        if ($("#title").val() && $.trim($("#title").val())) {
            equimentJson.title = $("#title").val();
        } else {
            $(".alert").attr("class", "alert alert-danger");
            $(".alert").html("申请简要不能为空。");
            isFilledTrue = false;
        }

        if (isFilledTrue) {
             console.log(JSON.stringify(equimentJson));
            // send ajax request
            $.ajax({
                url: path + "/equipment/request-buy",
                type: "post",
                data: JSON.stringify(equimentJson),
                dataType: "json",
                contentType : "application/json;charset=UTF-8",
                success: function (rdata) {
                    console.log(rdata);
                },
                error: function () {
                    $(".alert").attr("class", "alert alert-danger");
                    $(".alert").html("输入信息有误，请检查后再提交。");
                }
            });
        }
    });

    $(":text").on("focus", function(){
        $(".alert").attr("class", "alert hidden");
    });
});

function removeTableItem(removeId) {
    $("#" + removeId).remove();
}