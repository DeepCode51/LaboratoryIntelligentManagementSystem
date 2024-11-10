$(document).ready(function() {

    let option = {
        tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['直达','营销广告','搜索引擎','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
        },
        series: [
            {
                type:'pie',
                radius: ['40%', '55%'],
                label: {
                    normal: {
                        formatter: '\n  {b|{b}：}{c}  {per|{d}%}  ',
                        backgroundColor: '#eee',
                        borderColor: '#aaa',
                        borderWidth: 1,
                        borderRadius: 4,
                        rich: {
                            a: {
                                color: '#999',
                                lineHeight: 22,
                                align: 'center'
                            },
                            hr: {
                                borderColor: '#aaa',
                                width: '100%',
                                borderWidth: 0.5,
                                height: 0
                            },
                            b: {
                                fontSize: 16,
                                lineHeight: 33
                            },
                            per: {
                                color: '#eee',
                                backgroundColor: '#334455',
                                padding: [2, 4],
                                borderRadius: 2
                            }
                        }
                    }
                },
                data:[
                ]
            }
        ]
    };

    var labEcharts = echarts.init(document.getElementById("lab-date-body"));
    labEcharts.setOption(option);


    $.ajax({
        url: path + "/lab/ajax/info",
        type: "get",
        data: {
          labId: currentLabId
        },
        success: function (rdata) {

            let labEquipCounts = rdata.counts;
            let labEArray = new Array();

            for (let i = 0; i < labEquipCounts.length; i++) {
                let equipCount = {};
                equipCount.value = labEquipCounts[i].count;
                equipCount.name = labEquipCounts[i].equipmentName;
                labEArray.unshift(equipCount);
            }

            labEcharts.setOption({
//                xAxis:{
//                    data:data.name
//                },
                series:[
                    {
                        data: labEArray
                    }
                ]
            })
        },
        error: function () {
        }
    });
});

function changeUrlById(id) {
    document.location.href = path + "/lab/info?labId=" + id;
}

function toEquipmentUrl() {
    document.location.href = path + "/equipment/list";
}